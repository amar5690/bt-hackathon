package com.paymode.cashflow_prediction.repository;

import com.paymode.cashflow_prediction.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findBySupplierId(Long supplierId);
}
