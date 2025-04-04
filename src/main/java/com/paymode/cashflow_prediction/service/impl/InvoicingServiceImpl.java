package com.paymode.cashflow_prediction.service.impl;

import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.InvoiceDto;
import com.paymode.cashflow_prediction.dto.PredictionResponseDto;
import com.paymode.cashflow_prediction.model.entity.Invoice;
import com.paymode.cashflow_prediction.repository.InvoicingRepository;
import com.paymode.cashflow_prediction.service.InvoicingService;
import com.paymode.cashflow_prediction.service.helper.InvoicingHelper;
import com.paymode.cashflow_prediction.service.mapping.InvoiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class InvoicingServiceImpl implements InvoicingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoicingServiceImpl.class);

    private final InvoicingRepository invoicingRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoicingHelper invoicingHelper;

    public InvoicingServiceImpl(final InvoicingRepository invoicingRepository,
                                final InvoiceMapper invoiceMapper,
                                final InvoicingHelper invoicingHelper) {
        this.invoicingRepository = invoicingRepository;
        this.invoiceMapper = invoiceMapper;
        this.invoicingHelper = invoicingHelper;
    }

    @Override
    public CreateInvoiceResponseDto createInvoice(final InvoiceDto invoiceDto,
                                                  final Long vendorCompanyId) {
        final Invoice invoice = invoiceMapper.toInvoice(invoiceDto);

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

                final PredictionResponseDto predictionResponseDtoDto = invoicingHelper.getPaymentPredictionsForInvoices(invoiceDto);

                if (nonNull(predictionResponseDtoDto)) {
                    invoiceDto.setPredictionResponseDto(predictionResponseDtoDto);
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
}
