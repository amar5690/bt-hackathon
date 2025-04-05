package com.paymode.cashflow_prediction.service;

import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.CustomerDto;
import com.paymode.cashflow_prediction.dto.EmailDto;
import com.paymode.cashflow_prediction.dto.InvoiceDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface InvoicingService {

    CreateInvoiceResponseDto createInvoice(InvoiceDto invoiceDto, Long vendorCompanyId, MultipartFile file);

    List<InvoiceDto> getInvoices(Long vendorCompanyId);

    InvoiceDto getInvoice(Long id);

    byte [] downloadFile(long invoiceId);

    List<CustomerDto> getCustomerList(Long suplierId);

    Boolean triggerEmail(EmailDto emailDto);

    String uploadInvoiceFile(MultipartFile file, Long invoiceId);
}
