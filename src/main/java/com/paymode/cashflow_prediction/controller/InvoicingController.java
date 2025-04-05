package com.paymode.cashflow_prediction.controller;

import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.CustomerDto;
import com.paymode.cashflow_prediction.dto.EmailDto;
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

    @PostMapping(value = VENDOR_INVOICE_RESOURCE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateInvoiceResponseDto> createInvoice(@RequestBody InvoiceDto invoiceDto,
                                                                  @PathVariable Long vendorCompanyId) {
        return ResponseEntity.ok(invoicingService.createInvoice(invoiceDto, vendorCompanyId,null));
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

    @GetMapping(value = VENDOR_CUSTOMER_LIST)
    public ResponseEntity<List<CustomerDto>> getCustomerList(@PathVariable Long supplierId) {

        return ResponseEntity.ok(invoicingService.getCustomerList(supplierId));
    }

    @PostMapping(value = TRIGGER_EMAIL_NOTIFICATION, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> triggerEmail(@RequestBody EmailDto emailDto) {

        return ResponseEntity.ok(invoicingService.triggerEmail(emailDto));
    }

    @PostMapping(value = VENDOR_INVOICE_FILE_UPLOAD_RESOURCE, consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createInvoice(@RequestPart MultipartFile file,
                                                @PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoicingService.uploadInvoiceFile(file,invoiceId));
    }
}
