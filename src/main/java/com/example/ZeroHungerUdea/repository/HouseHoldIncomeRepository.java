package com.example.ZeroHungerUdea.repository;

import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import java.util.List;
import java.util.Optional;

public interface HouseHoldIncomeRepository {

    List<HouseHoldIncome> loadHouseHoldIncome();

    List<HouseHoldIncome> findAllHouseHoldIncome();

    Optional<HouseHoldIncome> getHouseHoldIncome(String familyName);

    HouseHoldIncome addHouseHoldIncome(HouseHoldIncome newHouseHoldIncome);

    HouseHoldIncome createHouseHoldIncomeFromPlainText(String plainTextHouseHoldIncome);
}


