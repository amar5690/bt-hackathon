package com.paymode.cashflow_prediction.client;


import com.paymode.cashflow_prediction.dto.PredictionRequestDto;
import com.paymode.cashflow_prediction.dto.PredictionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class PythonClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PythonClient.class);
    private final RestTemplate restTemplate;

    public PythonClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PredictionResponseDto getCashflowPrediction(final PredictionRequestDto predictionRequestDto) {

        final String getCashflowPredictionUrl = "http://localhost:5000/run";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_JSON);

            HttpEntity<PredictionRequestDto> entity = new HttpEntity<>(predictionRequestDto, headers);
            LOGGER.info("Sending request to python service {} , body {}", getCashflowPredictionUrl, predictionRequestDto);

            ResponseEntity<PredictionResponseDto> response = restTemplate.exchange(
                    getCashflowPredictionUrl, POST, entity, PredictionResponseDto.class);

            LOGGER.info("getCashflowPrediction response: {} status {}", response.getBody(), response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            LOGGER.error("failed to call cashflow prediction {}", e.getLocalizedMessage());
        }
        return null;
    }
}

