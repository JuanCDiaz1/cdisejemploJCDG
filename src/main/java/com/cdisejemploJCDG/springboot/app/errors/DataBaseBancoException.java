package com.cdisejemploJCDG.springboot.app.errors;

public class DataBaseBancoException extends RuntimeException{

	private static final long serialVersionUID = -5716339034664154990L;

	public DataBaseBancoException() {
		super("Contacte con la administracion, hubo un error con la base de datos");
	}
}
