package com.paymode.cashflow_prediction.repository;

import com.paymode.cashflow_prediction.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
