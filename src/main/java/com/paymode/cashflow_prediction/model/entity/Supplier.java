package com.paymode.cashflow_prediction.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SUPPLIER")
@SequenceGenerator(name = "SUPPLIER_SEQ", sequenceName = "SUPPLIER_SEQ", allocationSize = 1)
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLIER_SEQ")
    @Column(name = "SUPPLIER_ID", unique = true, nullable = false)
    private Long supplierId;

    @Column(name = "FIRST_NAME", length = 50, nullable = true)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50, nullable = true)
    private String lastName;

    @Column(name = "EMAIL_ID", length = 100, nullable = true)
    private String emailId;

    @Column(name = "CREATED_DATE", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT LOCALTIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_DATE", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT LOCALTIMESTAMP")
    private LocalDateTime modifiedDate;

    // Getters and Setters
    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
}
