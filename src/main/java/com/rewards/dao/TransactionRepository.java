package com.rewards.dao;


import com.rewards.model.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerId(Long customerId);
    List<CustomerTransaction> findAll();
}
