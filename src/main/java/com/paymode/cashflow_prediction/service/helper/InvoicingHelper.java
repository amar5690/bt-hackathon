package com.paymode.cashflow_prediction.service.helper;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.paymode.cashflow_prediction.client.PythonClient;
import com.paymode.cashflow_prediction.dto.CreateInvoiceResponseDto;
import com.paymode.cashflow_prediction.dto.InvoiceDto;
import com.paymode.cashflow_prediction.dto.PredictionRequestDto;
import com.paymode.cashflow_prediction.dto.PredictionResponseDto;
import com.paymode.cashflow_prediction.model.entity.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
public class InvoicingHelper {

    private final PythonClient pythonClient;

    private final String bucketName;

    private final AmazonS3 amazonS3;

    public InvoicingHelper(final PythonClient pythonClient,
                           final AmazonS3 amazonS3,
                           @Value("${cloud.aws.s3.bucket}") final String bucketName) {

        this.pythonClient = pythonClient;
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public CreateInvoiceResponseDto buildCreateInvoiceResponse(final Invoice invoice) {
        final CreateInvoiceResponseDto responseDto = new CreateInvoiceResponseDto();
        if (null != invoice) {
            responseDto.setInvoiceId(invoice.getId());
            responseDto.setInvoiceNumber(invoice.getInvoiceNumber());
        }
        return responseDto;
    }

    public PredictionResponseDto getPaymentPredictionsForInvoices(final InvoiceDto invoice,String customerIdForPrediction) {
        return pythonClient.getCashflowPrediction(buildPredictionRequestDto(invoice,customerIdForPrediction));
    }

    private PredictionRequestDto buildPredictionRequestDto(final InvoiceDto invoice, String customerIdForPrediction) {
        final PredictionRequestDto requestDto = new PredictionRequestDto();
        requestDto.setInvoiceDate(invoice.getInvoiceDate());
        requestDto.setInvoiceDueDate(invoice.getInvoiceDueDate());
        requestDto.setInvoiceAmount(invoice.getInvoiceAmount());
        requestDto.setCustomerIdForPrediction(customerIdForPrediction);
        return requestDto;
    }

    public String uploadFile(MultipartFile file) {
        final String filePath =  RandomUtil.getPositiveInt()+ "_"+file.getOriginalFilename();
        String key = "uploads/" + filePath;

        try {
            amazonS3.putObject(bucketName, key, file.getInputStream(), null);
            return filePath;
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return null;
    }


    public byte[] downloadFile(String fileName) {

        byte[] data = null;
        final String key = "uploads/" + fileName; // Assuming the file was saved under "uploads/"
        final S3Object s3Object = amazonS3.getObject(bucketName, key);
        try {
            data = s3Object.getObjectContent().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
