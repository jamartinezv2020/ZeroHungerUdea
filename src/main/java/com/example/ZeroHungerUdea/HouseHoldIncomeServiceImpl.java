package com.example.ZeroHungerUdea;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeRepository;
import com.example.ZeroHungerUdea.service.HouseHoldIncomeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HouseHoldIncomeServiceImpl implements HouseHoldIncomeService {

    private static final Logger logger = LoggerFactory.getLogger(HouseHoldIncomeServiceImpl.class);
    private final HouseHoldIncomeRepository houseHoldIncomeRepository;

    public HouseHoldIncomeServiceImpl(HouseHoldIncomeRepository houseHoldIncomeRepository) {
        this.houseHoldIncomeRepository = houseHoldIncomeRepository;
    }
    @Override
    public void DisplayFamilyName () {
        List<HouseHoldIncome> families = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : families) {
            System.out.println (ZeroHunger.nombre ());
        }
    }


    @Override
    public void ShowNumberMembers () {
        List<HouseHoldIncome> members = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : members) {
            System.out.println (ZeroHunger.miembros ());
        }
    }
    @Override
    public void ShowNumberMeals () {
        List<HouseHoldIncome> meals = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : meals) {
            System.out.println (ZeroHunger.comidas ());
        }
    }
    @Override
    public void ShowFamilyIncomeAmount () {
        List<HouseHoldIncome> familyIncomeAmounts = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : familyIncomeAmounts) {
            System.out.println (ZeroHunger.salario ());
        }
    }

    @Override
    public void ShowEducationalLevel () {
        List<HouseHoldIncome> educationalLavel = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : educationalLavel) {
            System.out.println (ZeroHunger.gradoEscolaridad ());
        }
    }
    @Override
    public void ShowHeadHousehold () {
        List<HouseHoldIncome> headHouse = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : headHouse) {
            System.out.println (ZeroHunger.jefeFamilia ());
        }
    }

    @Override
    public void ShowNumberRooms () {
        List<HouseHoldIncome> rooms = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        for (var ZeroHunger : rooms) {
            System.out.println (ZeroHunger.habitaciones ());
        }
    }

    @Override
    public Double IncomeSum () {

        System.out.println("Calculando la suma de los ingresos familiares");
        logger.info("Calculando la suma de los ingresos familiares");
        logger.warn("Calculando la suma de los ingresos familiares");
        logger.error("Calculando la suma de los ingresos familiares");
        // Todo calculateAverage of grades and return the average


        return null;
    }
    @Override
    public Double calculateAverageHouseHoldIncome() {

        System.out.println("Calculando el promedio de los ingresos familiares");
        logger.info("Calculando el promedio de los ingresos familiares");
        logger.warn("Calculando el promedio de los ingresos familiares");
        logger.error("Calculando el promedio de los ingresos familiares");
        // Todo calculateAverage of grades and return the average


        return null;
    }




    @Override
    public Integer sumNumberOfFamilies() {
        System.out.println("Sumando el número de familias");
        logger.info("Sumando el número de familias");
        logger.warn("Sumando el número de familias");
        logger.error("Sumando el número de familias");

        List<HouseHoldIncome> familiesList = this.houseHoldIncomeRepository.findAllHouseHoldIncome ();
        // TODO sum number of families and return the summation
        return null;
    }
}
