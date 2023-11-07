package com.example.ZeroHungerUdea.repository;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class HouseHoldIncomeInMemoryRepositoryImpl implements HouseHoldIncomeRepository {

  private static final Logger logger = LoggerFactory.getLogger( HouseHoldIncomeInMemoryRepositoryImpl.class);

  private final List<HouseHoldIncome> houseHoldIncomeList;

  public HouseHoldIncomeInMemoryRepositoryImpl() {
    this.houseHoldIncomeList = new ArrayList<>(loadHouseHoldIncome());
  }


  @Override
  public List<HouseHoldIncome> loadHouseHoldIncome(){
    logger.info( "Cargando los datos predefinidos " );
    return List.of(
            new HouseHoldIncome(LocalDate.of(2023, Month.APRIL, 12), "JOSE ALFREDO MARTINEZ VALDES", "11", "Padre y Madre", 800000.0D, 3, 2, 2),
            new HouseHoldIncome(LocalDate.of(2022, Month.OCTOBER, 11), "JOSE ALFREDO MARTINEZ VALDES", "10", "Padre y Madre", 1600000.0D, 7, 5, 4),
            new HouseHoldIncome(LocalDate.of(2023, Month.AUGUST, 1), "JOSE ALFREDO MARTINEZ VALDES", "10", "Padre", 1130000.0D, 2, 5, 3),
            new HouseHoldIncome(LocalDate.of(2023, Month.FEBRUARY, 21), "JOSE ALFREDO MARTINEZ VALDES", "11", "Padre", 850000.0D, 2, 8, 3),
            new HouseHoldIncome(LocalDate.of(2021, Month.OCTOBER, 17), "JOSE ALFREDO MARTINEZ VALDES", "10", "Padre y Madre", 5800000.0D, 2, 4, 2),
            new HouseHoldIncome(LocalDate.of(2023, Month.SEPTEMBER, 19), "JOSE ALFREDO MARTINEZ VALDES", "8", "Madre", 820000.0D, 2, 7, 1),
            new HouseHoldIncome(LocalDate.of(2023, Month.JULY, 12), "JOSE ALFREDO MARTINEZ VALDES", "8", "Otros", 1856000.0D, 2, 4, 5),
            new HouseHoldIncome(LocalDate.of(2023, Month.JUNE, 5), "JOSE ALFREDO MARTINEZ VALDES", "11", "Abuelos", 1250000.0D, 2, 2, 30000000));

  }
  @Override
  public List<HouseHoldIncome> findAllHouseHoldIncome() {
    return houseHoldIncomeList;
  }

  @Override
  public Optional<HouseHoldIncome> getHouseHoldIncome(String family) {
    return this.houseHoldIncomeList.stream().filter( houseHoldIncome->houseHoldIncome.nombre().equals( family ) ).findAny();
  }

  @Override
  public HouseHoldIncome addHouseHoldIncome(HouseHoldIncome newHouseHoldIncome) {
    this.houseHoldIncomeList.add( newHouseHoldIncome );

    return this.houseHoldIncomeList.stream()
            .filter( isTheHouseHoldIncomeOfTheProject( newHouseHoldIncome ) )
            .findAny()
            .orElse( null );
  }

  private Predicate<HouseHoldIncome> isTheHouseHoldIncomeOfTheProject(HouseHoldIncome newHouseHoldIncome) {
    return p -> p.nombre ().equals( newHouseHoldIncome.nombre () );
  }







  private Predicate<HouseHoldIncome> isTheHouseHoldIncomeOfTheName(HouseHoldIncome newHouseHoldIncome) {
    return houseHoldIncome -> houseHoldIncome.nombre ().equals( newHouseHoldIncome.nombre ());
  }
}
