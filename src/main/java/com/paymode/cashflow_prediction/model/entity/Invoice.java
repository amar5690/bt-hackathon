package com.paymode.cashflow_prediction.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "INVOICE")
@SequenceGenerator(name = "INVOICE_SEQ", sequenceName = "INVOICE_SEQ", allocationSize = 1)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_SEQ")
    @Column(name = "INVOICE_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "INVOICE_NUMBER", length = 50, nullable = false)
    private String invoiceNumber;

    @Column(name = "INVOICE_AMOUNT", precision = 18, scale = 9, nullable = false)
    private BigDecimal invoiceAmount;

    @Column(name = "INVOICE_DATE", nullable = false)
    private String invoiceDate;

    @Column(name = "INVOICE_DUE_DATE", nullable = false)
    private String invoiceDueDate;

    @Column(name = "PAYMENT_DATE")
    private String paymentDate;

    @Column(name = "STATUS", length = 20)
    private String status;

    @Column(name = "SUPPLIER_ID", columnDefinition = "NUMERIC(20)")
    private Long supplierId;

    @Column(name = "CUSTOMER_ID", columnDefinition = "NUMERIC(20)")
    private Long customerId;

    @Column(name = "PAYMENT_TERM", columnDefinition = "NUMERIC(20)")
    private Long paymentTerm;

    @Column(name = "DAYS_LATE", columnDefinition = "NUMERIC(20)")
    private Long daysLate;

    @Column(name = "CUSTOMER_AVG_DELAY", columnDefinition = "NUMERIC(20)")
    private Long customerAvgDelay;

    @Column(name = "DAYS_UNTIL_DUE", columnDefinition = "NUMERIC(20)")
    private Long daysUntilDue;

    @Column(name = "DAY_BETWEEN_INVOICE_AND_PAYMENT", columnDefinition = "NUMERIC(20)")
    private Long daysBetweenInvoiceAndPayment;

    @Column(name = "CUSTOMER_ID_ENCODDED", columnDefinition = "NUMERIC(20)")
    private Long customerIdEncoded;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "MODBYID", columnDefinition = "NUMERIC(20) NOT NULL DEFAULT 0")
    private Long modifiedById;

    @Column(name = "MODTS", nullable = false, columnDefinition = "TIMESTAMPTZ NOT NULL DEFAULT LOCALTIMESTAMP")
    private LocalDateTime modifiedTimestamp;

    @Column(name = "S3_PATH", columnDefinition = "NUMERIC(20)")
    private String s3Path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(String invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(Long paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Long getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Long daysLate) {
        this.daysLate = daysLate;
    }

    public Long getCustomerAvgDelay() {
        return customerAvgDelay;
    }

    public void setCustomerAvgDelay(Long customerAvgDelay) {
        this.customerAvgDelay = customerAvgDelay;
    }

    public Long getDaysUntilDue() {
        return daysUntilDue;
    }

    public void setDaysUntilDue(Long daysUntilDue) {
        this.daysUntilDue = daysUntilDue;
    }

    public Long getDaysBetweenInvoiceAndPayment() {
        return daysBetweenInvoiceAndPayment;
    }

    public void setDaysBetweenInvoiceAndPayment(Long daysBetweenInvoiceAndPayment) {
        this.daysBetweenInvoiceAndPayment = daysBetweenInvoiceAndPayment;
    }

    public Long getCustomerIdEncoded() {
        return customerIdEncoded;
    }

    public void setCustomerIdEncoded(Long customerIdEncoded) {
        this.customerIdEncoded = customerIdEncoded;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(Long modifiedById) {
        this.modifiedById = modifiedById;
    }

    public LocalDateTime getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(LocalDateTime modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    public String getS3Path() {
        return s3Path;
    }

    public void setS3Path(String s3Path) {
        this.s3Path = s3Path;
    }
}
