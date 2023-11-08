package com.example.ZeroHungerUdea.model;

import java.time.LocalDate;
public record HouseHoldIncome(
        LocalDate submissionDate,
        String nombre,
        String gradoEscolaridad,
        String jefeFamilia,
        Double salario,
        int miembros,
        int habitaciones,
        int comidas
) {}


