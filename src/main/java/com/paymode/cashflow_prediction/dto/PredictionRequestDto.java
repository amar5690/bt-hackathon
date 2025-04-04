package com.paymode.cashflow_prediction.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class PredictionRequestDto {
    private Integer customerId;
    private BigDecimal invoiceAmount;
    private String invoiceDate;
    private String invoiceDueDate;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customerId", customerId)
                .append("invoiceAmount", invoiceAmount)
                .append("invoiceDate", invoiceDate)
                .append("invoiceDueDate", invoiceDueDate)
                .toString();
    }
}


