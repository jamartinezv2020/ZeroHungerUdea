package com.example.ZeroHungerUdea.model;

import java.time.LocalDate;

// record InformacionHogar(Date date, String nombre, String gradoEscolaridad, String jefeFamilia, BigDecimal salario,
// byte miembros, character comidas, character habitaciones){
public record HouseHoldIncome(LocalDate submissionDate,
                              String nombre,
                              String gradoEscolaridad,
                              String jefeFamilia,
                              Double salario,
                              int miembros,
                              int habitaciones,
                              int comidas) {}

//public record HouseHoldIncome(String project, Double families, LocalDate submissionDate) {}

/** */

