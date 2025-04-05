package com.paymode.cashflow_prediction.controller;

import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.InvoiceDto;
import com.paymode.cashflow_prediction.service.InvoicingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.paymode.cashflow_prediction.controller.InvoicingControllerDefinition.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = INVOICING_RESOURCE_ROOT, produces = APPLICATION_JSON_VALUE)
public class InvoicingController {

    private final InvoicingService invoicingService;

    public InvoicingController(final InvoicingService invoicingService) {
        this.invoicingService = invoicingService;
    }

    @PostMapping(value = VENDOR_INVOICE_RESOURCE, consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateInvoiceResponseDto> createInvoice(@RequestPart("invoiceDto") InvoiceDto invoiceDto,
                                                                  @RequestPart MultipartFile file,
                                                                  @PathVariable Long vendorCompanyId) {
        return ResponseEntity.ok(invoicingService.createInvoice(invoiceDto, vendorCompanyId,file));
    }

    @GetMapping(value = VENDOR_INVOICE_RESOURCE)
    public ResponseEntity<List<InvoiceDto>> getInvoices(@PathVariable Long vendorCompanyId) {

        return ResponseEntity.ok(invoicingService.getInvoices(vendorCompanyId));
    }

    @GetMapping(value = INVOICE_DETAILS_RESOURCE)
    public ResponseEntity<InvoiceDto> getInvoiceDetails(@PathVariable Long Id) {

        return ResponseEntity.ok(invoicingService.getInvoice(Id));
    }

    @GetMapping(value = DOWNLOAD_FILES_RESOURCE,produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long invoiceId)  {

        return ResponseEntity.ok(invoicingService.downloadFile(invoiceId));
    }
}
