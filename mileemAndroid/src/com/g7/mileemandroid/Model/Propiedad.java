package com.g7.mileemandroid.Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Propiedad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private int supNCubierta;
	private int numero;
	private String piso ;
	private String departamento;
	private String moneda;
	private List<String> amenities;	
	private Bitmap[] fotosThumb;
	private Bitmap[] fotosCompleta;
	private int cantFotos;
	private String latitud;
	private String longitud;
	private long id;
	protected static long contador = 0;
	
/*	public Propiedad(String direccion, String descripcion, int antiguedad,
			String tipoOperacion, int precio, int superficie, int ambientes,
			int dormitorios, int expensas, String barrio, String tipoPropiedad,
			Bitmap[] fotos) {
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
		this.fotos = fotos;
		this.id = ++Propiedad.contador;
	}	*/
	
	public Propiedad(JSONObject json) {
		String dirFotoThumb;
		String dirFotoCompleta;
		try {
			JSONArray jsonFotos = json.getJSONArray("fotos");
			this.fotosThumb = null;
			this.fotosCompleta = null;
			
			this.cantFotos = jsonFotos.length();
			
			if (jsonFotos.length() >= 1) {
				this.fotosThumb = new Bitmap[this.cantFotos];
				this.fotosCompleta = new Bitmap[this.cantFotos];
				
				for(int i = 0; i < this.cantFotos; i++) {
					JSONObject jsonFoto = jsonFotos.getJSONObject(i);
					JSONObject jsonNombre = jsonFoto.getJSONObject("nombre");
					JSONObject jsonThumb = jsonNombre.getJSONObject("thumb");
					dirFotoThumb = jsonThumb.getString("url");
					dirFotoCompleta = jsonNombre.getString("url");
					String imageUrlThumb =  Constantes.DIRSERVER	+ dirFotoThumb;
					String imageUrlCompleta = Constantes.DIRSERVER	+ dirFotoCompleta;
					URL url;					
					try {
						//Obtengo fotoThumb						
						url = new URL(imageUrlThumb);
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						InputStream is = connection.getInputStream();
						Bitmap img = BitmapFactory.decodeStream(is);
						this.fotosThumb[i] = img;	

						//Obtengo fotoCompleta
						url = new URL(imageUrlCompleta);
						HttpURLConnection connection2 = (HttpURLConnection) url
								.openConnection();
						InputStream is2 = connection2.getInputStream();
						Bitmap img2 = BitmapFactory.decodeStream(is2);
						this.fotosCompleta[i] = img2;	
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}		
					
					
				}
			}

			long id = ++Propiedad.contador;
			this.id = id;
			
			JSONArray jsonAmenities = json.getJSONArray("amenities");
			this.amenities = new ArrayList<String>();

			for (int i = 0; i < jsonAmenities.length(); i++) {
				this.amenities.add(jsonAmenities.getJSONObject(i).getString(
						"nombre"));
			}
			
			if (!json.isNull("direccion"))
				this.direccion = json.getString("direccion");
			if (!json.isNull("descripcion"))
				this.descripcion = json.getString("descripcion");
			if (!json.isNull("antiguedad"))
				this.antiguedad = json.getInt("antiguedad");
			if (!json.isNull("operacion"))
				this.tipoOperacion = json.getString("operacion");
			if (!json.isNull("precio"))
				this.precio = json.getInt("precio");
			if (!json.isNull("superficie"))
				this.superficie = json.getInt("superficie");
			if (!json.isNull("ambientes"))
				this.ambientes = json.getInt("ambientes");
			if (!json.isNull("dormitorios"))
				this.dormitorios = json.getInt("dormitorios");
			if (!json.isNull("expensas"))
				this.expensas = json.getInt("expensas");
			if (!json.isNull("barrio"))
				this.barrio = json.getString("barrio");
			if (!json.isNull("tipo_propiedad"))
				this.tipoPropiedad = json.getString("tipo_propiedad");
			if (!json.isNull("numero"))
				this.numero = json.getInt("numero");
			if (!json.isNull("piso"))
				this.piso = json.getString("piso");
			if (!json.isNull("departamento"))
				this.departamento = json.getString("departamento");
			if (!json.isNull("moneda"))
				this.moneda = json.getString("moneda");
			if (!json.isNull("superficie_nc"))
				this.supNCubierta = json.getInt("superficie_nc");
			if (!json.isNull("latitude"))
				this.latitud = json.getString("latitude");
			if (!json.isNull("longitude"))
				this.longitud = json.getString("longitude");
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

	public Bitmap[] getFotosThumb() {
		return fotosThumb;
	}

	public void setFoto(Bitmap[] foto) {
		this.fotosThumb = foto;
	}

	public int getCantFotos() {
		return cantFotos;
	}

	public void setCantFotos(int cantFotos) {
		this.cantFotos = cantFotos;
	}

	public int getSupNCubierta() {
		return supNCubierta;
	}

	public void setSupNCubierta(int supNCubierta) {
		this.supNCubierta = supNCubierta;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Bitmap[] getFotosCompleta() {
		return fotosCompleta;
	}
	
}
