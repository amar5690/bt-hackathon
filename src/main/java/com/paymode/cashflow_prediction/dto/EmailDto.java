package com.paymode.cashflow_prediction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDto implements Serializable {
    private Long customerId;
    private Long supplierId;
    private LocalDateTime paymentDate;
    private Long daysLate;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Long daysLate) {
        this.daysLate = daysLate;
    }
}
