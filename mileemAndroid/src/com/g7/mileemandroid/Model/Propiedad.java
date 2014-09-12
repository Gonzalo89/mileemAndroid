package com.g7.mileemandroid.Model;

import android.graphics.drawable.Drawable;

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
	private Drawable foto;
	protected long id;
	
	public Propiedad(String direccion, String descripcion, int antiguedad,
			String tipoOperacion, int precio, int superficie, int ambientes,
			int dormitorios, int expensas, String barrio, String tipoPropiedad, Drawable foto, long id) {
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
		this.foto = foto;
		this.id=id;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public int getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(int ambientes) {
		this.ambientes = ambientes;
	}

	public int getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(int dormitorios) {
		this.dormitorios = dormitorios;
	}

	public int getExpensas() {
		return expensas;
	}

	public void setExpensas(int expensas) {
		this.expensas = expensas;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getTipoPropiedad() {
		return tipoPropiedad;
	}

	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Drawable getFoto() {
		return foto;
	}

	public void setFoto(Drawable foto) {
		this.foto = foto;
	}
	
		
}
