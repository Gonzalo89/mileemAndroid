package com.g7.mileemandroid.Model;

public class FiltroSingleton {
	private static FiltroSingleton instance;
	private long barrio = 0;
	private long tPropiedad = 0;
	private long ambientes = 0;
	private long operacion = 2; //para que empiece elegido alquiler

	public static FiltroSingleton getInstance() {
		if (instance == null) {
			// Create the instance
			instance = new FiltroSingleton();
		}
		return instance;
	}

	public long getBarrio() {
		return barrio;
	}

	public void setBarrio(long id) {
		this.barrio = id;
	}

	public long gettPropiedad() {
		return tPropiedad;
	}

	public void settPropiedad(long tPropiedad) {
		this.tPropiedad = tPropiedad;
	}

	public long getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(long ambientes) {
		this.ambientes = ambientes;
	}

	public long getOperacion() {
		return operacion;
	}

	public void setOperacion(long operacion) {
		this.operacion = operacion;
	}

}
