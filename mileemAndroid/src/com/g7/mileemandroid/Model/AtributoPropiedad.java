package com.g7.mileemandroid.Model;

public class AtributoPropiedad {
	private String nombre;
	private String valor;
	private long id;
	protected static long contador = 0;
	
	public AtributoPropiedad(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.id = ++Propiedad.contador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
}
