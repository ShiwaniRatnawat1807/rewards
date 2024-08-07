package com.rewards.service;

import com.rewards.dao.CustomerRepository;
import com.rewards.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerDetails(String customerName) {
        return customerRepository.findByName(customerName);
    }

    public List<Customer> findAllCustomerDetails() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        customer.getTransactions().forEach(transaction -> transaction.setCustomer(customer));
        return customerRepository.save(customer);
    }

}
