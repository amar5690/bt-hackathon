package com.paymode.cashflow_prediction.service;

import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.InvoiceDto;

import java.util.List;


public interface InvoicingService {

    CreateInvoiceResponseDto createInvoice(InvoiceDto invoiceDto, Long vendorCompanyId);

    List<InvoiceDto> getInvoices(Long vendorCompanyId);

    InvoiceDto getInvoice(Long id);
}
