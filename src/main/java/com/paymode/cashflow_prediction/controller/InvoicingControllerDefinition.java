package com.paymode.cashflow_prediction.controller;

public class InvoicingControllerDefinition {

    public static final String INVOICING_RESOURCE = "/api/invoicing";
    public static final String INVOICING_RESOURCE_ROOT = INVOICING_RESOURCE;
    public static final String VENDOR_INVOICE_RESOURCE = "/vendors/{vendorCompanyId}/invoice";
    public static final String INVOICE_DETAILS_RESOURCE = "/invoices/{Id}";
    public static final String DOWNLOAD_FILES_RESOURCE = "/downloads/files/{invoiceId}";
    public static final String VENDOR_CUSTOMER_LIST = "/vendor/{supplierId}/customers";
    public static final String TRIGGER_EMAIL_NOTIFICATION = "/trigger/email";
    public static final String VENDOR_INVOICE_FILE_UPLOAD_RESOURCE = "/invoices/{invoiceId}/file";
}
