package com.paymode.cashflow_prediction.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PredictionResponseDto {
    private String status;
    private float predictionDays;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPredictionDays() {
        return predictionDays;
    }

    public void setPredictionDays(float predictionDays) {
        this.predictionDays = predictionDays;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("status", status)
                .append("predictionDays", predictionDays)
                .toString();
    }
}
