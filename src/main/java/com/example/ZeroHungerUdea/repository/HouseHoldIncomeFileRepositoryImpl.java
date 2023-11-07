package com.example.ZeroHungerUdea.repository;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;


import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class HouseHoldIncomeFileRepositoryImpl extends HouseHoldIncomeUsingFileRepositoryImpl {

    public HouseHoldIncomeFileRepositoryImpl() {
        super();
    }

    @Override
    public List<HouseHoldIncome> loadHouseHoldIncome() {
        // Implementa la carga de datos desde el archivo families.txt
        // Utiliza la lógica que ya tienes en HouseHoldIncomeUsingFileRepositoryImpl.
        // Por ejemplo:
        List<String> plainTextHouseHoldIncomeList = readFileWithHouseHoldIncome();
        List<HouseHoldIncome> list = plainTextHouseHoldIncomeList.stream().map(this::createHouseHoldIncomeFromPlainText).toList();
        return list;
    }

    @Override
    public List<HouseHoldIncome> findAllHouseHoldIncome() {
        return houseHoldIncomeList;
    }

    @Override
    public Optional<HouseHoldIncome> getHouseHoldIncome(String family) {
        return this.houseHoldIncomeList.stream()
                .filter(houseHoldIncome -> houseHoldIncome.nombre().equals(family))
                .findAny();
    }

    @Override
    public HouseHoldIncome addHouseHoldIncome(HouseHoldIncome newHouseHoldIncome) {
        this.houseHoldIncomeList.add(newHouseHoldIncome);
        return newHouseHoldIncome;
    }

    private Predicate<HouseHoldIncome> isTheHouseHoldIncomeOfTheName(HouseHoldIncome newHouseHoldIncome) {
        return houseHoldIncome -> houseHoldIncome.nombre().equals(newHouseHoldIncome.nombre());
    }
}
