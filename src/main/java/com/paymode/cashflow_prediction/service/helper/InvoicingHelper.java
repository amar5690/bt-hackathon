package com.paymode.cashflow_prediction.service.helper;

import com.paymode.cashflow_prediction.client.PythonClient;
import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.InvoiceDto;
import com.paymode.cashflow_prediction.dto.PredictionRequestDto;
import com.paymode.cashflow_prediction.dto.PredictionResponseDto;
import com.paymode.cashflow_prediction.model.entity.Invoice;
import org.springframework.stereotype.Component;



@Component
public class InvoicingHelper {

    private final PythonClient pythonClient;

    public InvoicingHelper(final PythonClient pythonClient) {
        this.pythonClient = pythonClient;
    }

    public CreateInvoiceResponseDto buildCreateInvoiceResponse(final Invoice invoice) {
        final CreateInvoiceResponseDto responseDto = new CreateInvoiceResponseDto();
        if (null != invoice) {
            responseDto.setInvoiceId(invoice.getId());
            responseDto.setInvoiceNumber(invoice.getInvoiceNumber());
        }
        return responseDto;
    }

    public PredictionResponseDto getPaymentPredictionsForInvoices(final InvoiceDto invoice) {
        return pythonClient.getCashflowPrediction(buildPredictionRequestDto(invoice));
    }

    private PredictionRequestDto buildPredictionRequestDto(final InvoiceDto invoice) {
        final PredictionRequestDto requestDto = new PredictionRequestDto();
        requestDto.setInvoiceDate(invoice.getInvoiceDate());
        requestDto.setInvoiceDueDate(invoice.getInvoiceDueDate());
        requestDto.setInvoiceAmount(invoice.getInvoiceAmount());
        requestDto.setInvoiceAmount(invoice.getInvoiceAmount());
        return requestDto;
    }
}
