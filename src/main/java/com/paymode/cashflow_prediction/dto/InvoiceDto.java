package com.paymode.cashflow_prediction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDto implements Serializable {
    private Long invoiceId;
    private String invoiceNumber;
    private BigDecimal invoiceAmount;
    private String invoiceDate;
    private String invoiceDueDate;
    private String paymentDate;
    private String status;
    private Long supplierId;
    private Long customerId;
    private Long paymentTerm;
    private Long daysLate;
    private Long customerAvgDelay;
    private Long daysUntilDue;
    private Long daysBetweenInvoiceAndPayment;
    private Long customerIdEncoded;
    private LocalDateTime createdDate;
    private Long modifiedById;
    private LocalDateTime modifiedTimestamp;
    private PredictionResponseDto predictionResponseDto;
    private String s3Path;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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

    public void setModifiedTimestamp(LocalDateTime modTs) {
        this.modifiedTimestamp = modTs;
    }

    public PredictionResponseDto getPredictionResponseDto() {
        return predictionResponseDto;
    }

    public void setPredictionResponseDto(PredictionResponseDto predictionResponseDto) {
        this.predictionResponseDto = predictionResponseDto;
    }

    public String getS3Path() {
        return s3Path;
    }

    public void setS3Path(String s3Path) {
        this.s3Path = s3Path;
    }
}
