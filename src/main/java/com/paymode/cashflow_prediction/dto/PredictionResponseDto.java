package com.paymode.cashflow_prediction.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class PredictionResponseDto implements Serializable {
    private String status;
    private Integer predicted_days;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPredicted_days() {
        return predicted_days;
    }

    public void setPredicted_days(Integer predicted_days) {
        this.predicted_days = predicted_days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("status", status)
                .append("predicted_days", predicted_days)
                .toString();
    }
}
