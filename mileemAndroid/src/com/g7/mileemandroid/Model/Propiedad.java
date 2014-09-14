package com.g7.mileemandroid.Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Propiedad {

	private String direccion;
	private String descripcion;
	private int antiguedad;
	private String tipoOperacion;
	private int precio;
	private int superficie;
	private int ambientes;
	private int dormitorios;
	private int expensas;
	private String barrio;
	private String tipoPropiedad;
	private Bitmap foto;
	private long id;
	protected static long contador;
	
	public Propiedad(String direccion, String descripcion, int antiguedad,
			String tipoOperacion, int precio, int superficie, int ambientes,
			int dormitorios, int expensas, String barrio, String tipoPropiedad,
			Bitmap foto, long id) {
		super();
		this.direccion = direccion;
		this.descripcion = descripcion;
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
		this.id = ++Propiedad.contador;
	}

	public Propiedad(JSONObject json) {
		String dirFoto;
		try {
			JSONArray jsonFotos = json.getJSONArray("fotos");
			this.foto = null;

			if (jsonFotos.length() >= 1) {
				JSONObject jsonFoto = jsonFotos.getJSONObject(0);
				JSONObject jsonNombre = jsonFoto.getJSONObject("nombre");
				JSONObject jsonThumb = jsonNombre.getJSONObject("thumb");
				dirFoto = jsonThumb.getString("url");

				String imageUrl = "http://" + Constantes.IPSERVER + ":3000"
						+ dirFoto;
				URL url;
				try {
					url = new URL(imageUrl);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					InputStream is = connection.getInputStream();
					Bitmap img = BitmapFactory.decodeStream(is);

					this.foto = img;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			long id = ++Propiedad.contador;
			this.direccion = json.getString("direccion");
			this.descripcion = json.getString("descripcion");
			this.antiguedad = json.getInt("antiguedad");
			this.tipoOperacion = json.getString("operacion");
			this.precio = json.getInt("precio");
			this.superficie = json.getInt("superficie");
			this.ambientes = json.getInt("ambientes");
			this.dormitorios = json.getInt("dormitorios");
			this.expensas = json.getInt("expensas");
			this.barrio = json.getString("barrio");
			this.tipoPropiedad = json.getString("tipo_propiedad");

			this.id = id;
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Bitmap getFoto() {
		return foto;
	}

	public void setFoto(Bitmap foto) {
		this.foto = foto;
	}
	
		
}
