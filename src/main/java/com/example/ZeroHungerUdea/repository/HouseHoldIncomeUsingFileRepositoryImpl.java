package com.example.ZeroHungerUdea.repository;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class HouseHoldIncomeUsingFileRepositoryImpl implements HouseHoldIncomeRepository {
  public static final Logger logger = LoggerFactory.getLogger(HouseHoldIncomeUsingFileRepositoryImpl.class);
  public final List<HouseHoldIncome> houseHoldIncomeList;

  public HouseHoldIncomeUsingFileRepositoryImpl() {
    this.houseHoldIncomeList = new ArrayList<>(loadHouseHoldIncomeFromFile());
  }

  public List<HouseHoldIncome> loadHouseHoldIncomeFromFile() {
    logger.info("Loading data from file");
    List<String> plainTextHouseHoldIncomeList = readFileWithHouseHoldIncome();
    return plainTextHouseHoldIncomeList.stream()
            .map(this::createHouseHoldIncomeFromPlainText)
            .filter(Objects::nonNull)
            .toList();
  }

  public List<String> readFileWithHouseHoldIncome() {
    Path path = Paths.get("./src/main/resources/families.txt");
    if (!Files.exists(path) || !Files.isRegularFile(path)) {
      logger.error("El archivo 'families.txt' no existe o no es un archivo regular.");
      return Collections.emptyList();
    }

    try (Stream<String> stream = Files.lines(path)) {
      return stream.toList();
    } catch (IOException e) {
      logger.error("Error al leer el archivo 'families.txt'", e);
      return Collections.emptyList();
    }
  }

  public HouseHoldIncome createHouseHoldIncomeFromPlainText(String plainTextHouseHoldIncome) {
    logger.info("Cargando los datos desde el archivo");
    String[] houseHoldIncomeArray = plainTextHouseHoldIncome.split(",");
    try {
      if (houseHoldIncomeArray.length != 8) {
        logger.error("Número incorrecto de campos en los datos de ingresos del hogar: " + plainTextHouseHoldIncome);
        return null;
      }

      LocalDate date = LocalDate.parse(houseHoldIncomeArray[0], DateTimeFormatter.ISO_DATE);
      String field1 = houseHoldIncomeArray[1];
      String field2 = houseHoldIncomeArray[2];
      String field3 = houseHoldIncomeArray[3];

      double salario = Double.parseDouble(houseHoldIncomeArray[4]);
      int miembros = Integer.parseInt(houseHoldIncomeArray[5].trim());
      int habitaciones = Integer.parseInt(houseHoldIncomeArray[6].trim());
      int comidas = Integer.parseInt(houseHoldIncomeArray[7].trim());

      return new HouseHoldIncome(date, field1, field2, field3, salario, miembros, habitaciones, comidas);
    } catch (NumberFormatException | DateTimeParseException e) {
      logger.error("Error al analizar los datos de ingresos del hogar: " + plainTextHouseHoldIncome, e);
      return null;
    }
  }
}


