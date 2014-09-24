package com.g7.mileemandroid.Model;

public class PropiedadSingleton {

	private static PropiedadSingleton instance;

	private static Propiedad unaPropiedad;

	public static PropiedadSingleton getInstance() {
		if (instance == null) {
			// Create the instance
			instance = new PropiedadSingleton();
		}
		return instance;
	}
	
	
	public static Propiedad getPropiedad() {
		return unaPropiedad;
	}
	
	public static void setPropiedad(Propiedad unaProp) {
		unaPropiedad = unaProp;
	}
}
