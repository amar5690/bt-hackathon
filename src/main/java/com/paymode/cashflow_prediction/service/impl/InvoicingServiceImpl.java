package com.paymode.cashflow_prediction.service.impl;

import com.paymode.cashflow_prediction.dto.*;
import com.paymode.cashflow_prediction.model.entity.Customer;
import com.paymode.cashflow_prediction.model.entity.Invoice;
import com.paymode.cashflow_prediction.model.entity.Supplier;
import com.paymode.cashflow_prediction.repository.CustomerRepository;
import com.paymode.cashflow_prediction.repository.InvoicingRepository;
import com.paymode.cashflow_prediction.repository.SupplierRepository;
import com.paymode.cashflow_prediction.service.EmailService;
import com.paymode.cashflow_prediction.service.InvoicingService;
import com.paymode.cashflow_prediction.service.helper.InvoicingHelper;
import com.paymode.cashflow_prediction.service.mapping.CustomerMapper;
import com.paymode.cashflow_prediction.service.mapping.InvoiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@Service
public class InvoicingServiceImpl implements InvoicingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoicingServiceImpl.class);

    private final InvoicingRepository invoicingRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoicingHelper invoicingHelper;
    private final SupplierRepository supplierRepository;
    private final EmailService emailService;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public InvoicingServiceImpl(final InvoicingRepository invoicingRepository,
                                final InvoiceMapper invoiceMapper,
                                final InvoicingHelper invoicingHelper,
                                final SupplierRepository supplierRepository,
                                final EmailService emailService,
                                final CustomerMapper customerMapper,
                                final CustomerRepository customerRepository) {
        this.invoicingRepository = invoicingRepository;
        this.invoiceMapper = invoiceMapper;
        this.invoicingHelper = invoicingHelper;
        this.supplierRepository = supplierRepository;
        this.emailService = emailService;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CreateInvoiceResponseDto createInvoice(final InvoiceDto invoiceDto,
                                                  final Long vendorCompanyId,
                                                  final MultipartFile multipartFile) {
        final Invoice invoice = invoiceMapper.toInvoice(invoiceDto);
        invoice.setCreatedDate(now());
        invoice.setModifiedTimestamp(now());
        invoice.setModifiedById(vendorCompanyId);
        invoice.setStatus("IN_PROGRESS");


        invoicingRepository.save(invoice);
        LOGGER.info("Invoice created: {}", invoice.getId());

        return invoicingHelper.buildCreateInvoiceResponse(invoice);
    }

    @Override
    public List<InvoiceDto> getInvoices(final Long vendorCompanyId) {
        final List<Invoice> invoiceList = invoicingRepository.findBySupplierId(vendorCompanyId);

        if (!CollectionUtils.isEmpty(invoiceList)) {
            final List<InvoiceDto> invoiceDtoList = invoiceMapper.toInvoiceDtoList(invoiceList);

            for (InvoiceDto invoiceDto : invoiceDtoList) {
                final Customer customer = customerRepository.findById(invoiceDto.getCustomerId()).orElse(null);

                if(nonNull(customer) && nonNull(customer.getCustomerIdForPrediction())) {
                    final PredictionResponseDto predictionResponseDtoDto = invoicingHelper.
                            getPaymentPredictionsForInvoices(invoiceDto,customer.getCustomerIdForPrediction());

                    if (nonNull(predictionResponseDtoDto)) {
                        invoiceDto.setPredictionResponseDto(predictionResponseDtoDto);
                    }
                }
            }
            return invoiceDtoList;
        }
        LOGGER.info("Invoices not found for supplierId {}", vendorCompanyId);
        return List.of();
    }

    @Override
    public InvoiceDto getInvoice(final Long id) {

        Optional<Invoice> invoiceOptional = invoicingRepository.findById(id);

        if (invoiceOptional.isPresent()) {
            return invoiceMapper.toInvoiceDto(invoiceOptional.get());
        }

        LOGGER.info("Invoice not found for id {}", id);
        return new InvoiceDto();
    }

    public byte[] downloadFile(final long invoiceId) {
        Optional<Invoice> invoiceOptional = invoicingRepository.findById(invoiceId);

        if (invoiceOptional.isPresent() && nonNull(invoiceOptional.get().getS3Path())) {
            return invoicingHelper.downloadFile(invoiceOptional.get().getS3Path());
        }
        return new byte[0];
    }

    @Override
    public List<CustomerDto> getCustomerList(final Long vendorId) {
        List<Customer> customerList = customerRepository.findBySupplierId(vendorId);
        if(customerList.isEmpty()){
            LOGGER.info("No Customer found for vendor {}", vendorId);
            return emptyList();
        }
        return customerMapper.toCustomerDtoList(customerList);
    }

    @Override
    public Boolean triggerEmail(EmailDto emailDto) {
        Optional<Customer> customerOpt = customerRepository.findById(emailDto.getCustomerId());
        if (customerOpt.isEmpty()) {
            return FALSE;
        }

        Customer customer = customerOpt.get();
        Optional<Supplier> supplierOpt = supplierRepository.findById(customer.getVendorId());
        if (supplierOpt.isEmpty()) {
            return FALSE;
        }

        Supplier supplier = supplierOpt.get();
        return sendEmail(customer, supplier, emailDto);
    }

    private boolean sendEmail(Customer customer, Supplier supplier, EmailDto emailDto) {
        final String payerName = customer.getFirstName() + " " + customer.getLastName();
        final String vendorName = supplier.getFirstName() + " " + supplier.getLastName();
        final String formattedDate = emailDto.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final Long daysLeft = emailDto.getDaysLate();
        final String customerEmailId = customer.getEmailId();

        final String body = String.format(
                "Dear %s,\n\n" +
                        "This is a friendly reminder that your payment with us is due on %s. There are %d days left until the due date.\n\n" +
                        "We kindly request you to ensure that the payment is made by the due date to avoid any late fees or disruptions in service.\n\n" +
                        "Thank you for your attention to this matter.\n\n" +
                        "Best regards,\n" +
                        "%s",
                payerName, formattedDate, daysLeft, vendorName);
        try {
            emailService.sendSimpleMessage(customerEmailId, "Payment Reminder", body);
            return true;
        } catch (Exception e) {
            LOGGER.info("Failed to send email: " + e.getMessage());
            return false;
        }
    }

    public String uploadInvoiceFile(final MultipartFile file, final Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoicingRepository.findById(invoiceId);
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            final String s3Path = invoicingHelper.uploadFile(file);
            if (nonNull(s3Path)) {
                invoice.setS3Path(s3Path);
            }
            return s3Path;
        }
        return null;
    }
}
