package com.g7.mileemandroid.Model;

public class FiltroSingleton {
	private static FiltroSingleton instance;
	private long barrio = 0;
	private long tPropiedad = 0;
	private long ambientes = 0;
	private long operacion = 0;
	private long precio = 0;
	private long supCubierta = 0;
	private long fechaPublicacion = 0;
	private long orden = 0;

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

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public long getSupCubierta() {
		return supCubierta;
	}

	public void setSupCubierta(long supCubierta) {
		this.supCubierta = supCubierta;
	}

	public long getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(long fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public long getOrden() {
		return orden;
	}

	public void setOrden(long orden) {
		this.orden = orden;
	}
	
	public void resetFiltroPrimario() {
		this.barrio = 0;
		this.operacion = 0;
		this.tPropiedad = 0;
		this.ambientes = 0;
	}
}
