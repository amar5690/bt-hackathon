package com.paymode.cashflow_prediction.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER")
@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
    private Long customerId;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Column(name = "EMAIL_ID", length = 100, nullable = false)
    private String emailId;

    @Column(name = "SUPPLIER_ID", nullable = false)
    private Long supplierId;

    @Column(name = "CREATED_DATE", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT LOCALTIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_DATE", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT LOCALTIMESTAMP")
    private LocalDateTime modifiedDate;

    @Column(name = "CUSTOMER_ID_FOR_PREDICTION", nullable = false, length = 50)
    private String customerIdForPrediction;

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getVendorId() {
        return supplierId;
    }

    public void setVendorId(Long vendorId) {
        this.supplierId = vendorId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getCustomerIdForPrediction() {
        return customerIdForPrediction;
    }

    public void setCustomerIdForPrediction(String customerIdForPrediction) {
        this.customerIdForPrediction = customerIdForPrediction;
    }
}