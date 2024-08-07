package com.rewards;

import org.springframework.boot.SpringApplication;

public class TestRewardsApplication {

	public static void main(String[] args) {
		SpringApplication.from(RewardsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
