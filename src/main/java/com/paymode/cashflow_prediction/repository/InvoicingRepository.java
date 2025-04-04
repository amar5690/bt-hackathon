package com.paymode.cashflow_prediction.repository;

import com.paymode.cashflow_prediction.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicingRepository extends JpaRepository<Invoice,Long> {

    List<Invoice> findBySupplierId(Long supplierId);
}
