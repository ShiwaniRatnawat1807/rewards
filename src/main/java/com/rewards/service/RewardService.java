package com.rewards.service;

import com.rewards.dao.CustomerRepository;
import com.rewards.dao.RewardPointsRepository;
import com.rewards.dao.TransactionRepository;
import com.rewards.model.RewardPoints;
import com.rewards.model.CustomerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RewardService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RewardPointsRepository rewardPointsRepository;

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    public Map<Month, Integer> getMonthlyPoints(Long customerId) {
        List<CustomerTransaction> transactions = transactionRepository.findByCustomerId(customerId);

        Map<Month, Integer> pointsPerMonth = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth(),
                        Collectors.summingInt(t -> calculatePoints(t.getAmount()))
                ));

        pointsPerMonth.forEach((month, points) -> {
            RewardPoints rewardPoints = new RewardPoints();
            rewardPoints.setCustomerId(customerId);
            rewardPoints.setMonth(month.toString());
            rewardPoints.setPoints(points);
            rewardPointsRepository.save(rewardPoints);
        });
        return pointsPerMonth;
    }

    public int getTotalPoints(Long customerId) {
        return transactionRepository.findByCustomerId(customerId).stream()
                .mapToInt(t -> calculatePoints(t.getAmount()))
                .sum();
    }

    public List<CustomerTransaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<RewardPoints> getRewardPoints(Long customerId) {
        return rewardPointsRepository.findById(customerId);
    }
}
