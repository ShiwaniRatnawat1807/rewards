package com.rewards.dao;


import com.rewards.model.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
}
