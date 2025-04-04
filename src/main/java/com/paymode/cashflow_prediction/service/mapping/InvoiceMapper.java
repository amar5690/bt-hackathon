package com.paymode.cashflow_prediction.service.mapping;

import com.paymode.cashflow_prediction.dto.InvoiceDto;
import com.paymode.cashflow_prediction.model.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    Invoice toInvoice(InvoiceDto invoiceDto);

    @Mapping(source = "id", target = "invoiceId")
    InvoiceDto toInvoiceDto(Invoice invoice);

    default List<Invoice> toInvoiceList(List<InvoiceDto> invoiceDtoList) {
        return invoiceDtoList.stream().map(this::toInvoice).collect(Collectors.toList());
    }

    default List<InvoiceDto> toInvoiceDtoList(List<Invoice> invoiceList) {
        return invoiceList.stream().map(this::toInvoiceDto).collect(Collectors.toList());
    }
}
