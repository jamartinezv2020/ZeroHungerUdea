package com.example.ZeroHungerUdea.exception;

import java.text.MessageFormat;

//Esta es una checked exception
public class HouseHoldIncomeNotFoundException extends Exception{

	public HouseHoldIncomeNotFoundException (String nombreFamilia) {
		super( MessageFormat.format( "No se encontró el salario para la familia {0}", nombreFamilia ) );
	}
}
