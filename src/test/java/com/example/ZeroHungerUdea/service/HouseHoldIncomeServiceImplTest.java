package com.example.ZeroHungerUdea.service;

import com.example.ZeroHungerUdea.HouseHoldIncomeServiceImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeInMemoryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HouseHoldIncomeServiceImplTest {

  private HouseHoldIncomeService houseHoldIncomeService;

  HouseHoldIncomeServiceImplTest(HouseHoldIncomeService houseHoldIncomeService) {
    this.houseHoldIncomeService = houseHoldIncomeService;
  }

  @BeforeEach
  void setUp() {
    this.houseHoldIncomeService = new HouseHoldIncomeServiceImpl(new HouseHoldIncomeInMemoryRepositoryImpl () {
      @Override
      public HouseHoldIncome createHouseHoldIncomeFromPlainText (String plainTextHouseHoldIncome) {
        return null;
      }
    });
  }

  @Test
  void calculateAverageHouseHoldIncome() {

    Double average = this.houseHoldIncomeService.calculateAverageHouseHoldIncome();


    assertNotNull(average);
  }

  @Test
  void summation_of_number_of_families_should_return_a_valid_number() {
    Integer numberOfFamilies = this.houseHoldIncomeService.sumNumberOfFamilies();

    assertNotNull(numberOfFamilies);
  }
}
