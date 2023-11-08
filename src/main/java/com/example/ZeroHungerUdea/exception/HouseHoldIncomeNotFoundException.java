package com.example.ZeroHungerUdea.exception;

public class HouseHoldIncomeNotFoundException extends Exception {

    public HouseHoldIncomeNotFoundException(String familyName) {
        super(buildMessage(familyName));
    }

    private static String buildMessage(String familyName) {
        return String.format("No se encontró el salario para la familia %s", familyName);
    }
}













