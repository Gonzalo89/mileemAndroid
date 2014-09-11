package com.g7.mileemandroid.Model;

public class Propiedad {

	private String direccion;
	private String Descripcion;
	private int antiguedad;
	private String tipoOperacion;
	private int precio;
	private int superficie;
	private int ambientes;
	private int dormitorios;
	private int expensas;
	private String barrio;
	private String tipoPropiedad;
	
	public Propiedad(String direccion, String descripcion, int antiguedad,
			String tipoOperacion, int precio, int superficie, int ambientes,
			int dormitorios, int expensas, String barrio, String tipoPropiedad) {
		super();
		this.direccion = direccion;
		Descripcion = descripcion;
		this.antiguedad = antiguedad;
		this.tipoOperacion = tipoOperacion;
		this.precio = precio;
		this.superficie = superficie;
		this.ambientes = ambientes;
		this.dormitorios = dormitorios;
		this.expensas = expensas;
		this.barrio = barrio;
		this.tipoPropiedad = tipoPropiedad;
	}
	
		
}
