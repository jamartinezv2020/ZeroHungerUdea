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
        // Cargar datos desde el archivo families.txt
        List<String> plainTextHouseHoldIncomeList = readFileWithHouseHoldIncome();
        List<HouseHoldIncome> list = plainTextHouseHoldIncomeList.stream()
                .map(this::createHouseHoldIncomeFromPlainText)
                .toList();
        return list;
    }

    @Override
    public List<HouseHoldIncome> findAllHouseHoldIncome() {
        return houseHoldIncomeList;
    }

    @Override
    public Optional<HouseHoldIncome> getHouseHoldIncome(String familyName) {
        return houseHoldIncomeList.stream()
                .filter(houseHoldIncome -> houseHoldIncome.nombre().equals(familyName))
                .findAny();
    }

    @Override
    public HouseHoldIncome addHouseHoldIncome(HouseHoldIncome newHouseHoldIncome) {
        this.houseHoldIncomeList.add(newHouseHoldIncome);
        return newHouseHoldIncome;
    }

    private Predicate<HouseHoldIncome> isTheHouseHoldIncomeOfTheName(String familyName) {
        return houseHoldIncome -> houseHoldIncome.nombre().equals(familyName);
    }
}
