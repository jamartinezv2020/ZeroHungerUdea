package com.example.ZeroHungerUdea.model;

import com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ZeroHungerUdeaApplication {
	private static List<HouseHoldIncome> incomeList;
	public static void main(String[] args) {
		SpringApplication.run(ZeroHungerUdeaApplication.class, args);

	}

}
