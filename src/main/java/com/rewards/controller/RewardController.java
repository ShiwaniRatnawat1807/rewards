package com.rewards.controller;

import com.rewards.dao.CustomerRepository;
import com.rewards.dao.TransactionRepository;
import com.rewards.model.Customer;
import com.rewards.model.CustomerTransaction;
import com.rewards.model.RewardPoints;
import com.rewards.service.CustomerService;
import com.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/monthly/{customerId}")
    public Map<Month, Integer> getMonthlyPoints(@PathVariable Long customerId) {
        return rewardService.getMonthlyPoints(customerId);
    }

    @GetMapping("/total/{customerId}")
    public int getTotalPoints(@PathVariable Long customerId) {
        return rewardService.getTotalPoints(customerId);
    }

    @GetMapping("getCustomerByName/{customerName}")
    public Customer getCustomerDetails(@PathVariable String customerName) {
        return customerService.getCustomerDetails(customerName);
    }

    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/getAllCustomerDetails")
    public List<Customer> findAllCustomerDetails() {
        return customerService.findAllCustomerDetails();
    }

    @GetMapping("/getAllTransactions")
    public List<CustomerTransaction> findAllTransactions() {
        return rewardService.findAllTransactions();
    }

    @GetMapping("/getRewardPoints/{customerId}")
    public Optional<RewardPoints> getRewardPoints(@PathVariable Long customerId) {
        return rewardService.getRewardPoints(customerId);
    }

}
