package com.rewards.dao;

import com.rewards.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String customerName);
    List<Customer> findAll();

}
