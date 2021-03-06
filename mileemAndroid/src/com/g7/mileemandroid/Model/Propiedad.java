package com.g7.mileemandroid.Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.g7.mileemandroid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Propiedad implements Serializable{

	private static final long serialVersionUID = 1L;
	private String direccion;
	private String descripcion;
	private int antiguedad;
	private String tipoOperacion;
	private String tipoPropiedad;
	private String tipoPublicacion;
	private int tipoPublicacionId;
	private int precio;
	private int superficie;
	private int ambientes;
	private int dormitorios;
	private int expensas;
	private String barrio;
	private int barrioId;
	private int supNCubierta;
	private int numero;
	private String piso ;
	private String departamento;
	private String moneda;
	private List<String> amenities;	
	private String[] idVideos;
	private int cantFotos;
	private int cantVideos;
	private String latitud;
	private String longitud;
	private long id;
	protected static long contador = 0;
	private String telefono;
	private String email;
	private String nombre;
	private String apellido;
	private String[] imagesUrlThumb;
	private String[] imagesUrlCompleta;
	private Bitmap[] fotosThumb;
	private Bitmap[] fotosCompleta;
	
	public Propiedad(JSONObject json) {
		
		String dirFotoThumb;
		String dirFotoCompleta;
		
		try {
			JSONArray jsonFotos = json.getJSONArray("fotos");
			this.fotosThumb = null;
			this.fotosCompleta = null;
			
			this.cantFotos = jsonFotos.length();
			
			if (jsonFotos.length() >= 1) { //Cargo fotos
				this.imagesUrlThumb = new String[this.cantFotos];
				this.imagesUrlCompleta = new String[this.cantFotos];
				
				for(int i = 0; i < this.cantFotos; i++) {
					JSONObject jsonFoto = jsonFotos.getJSONObject(i);
					JSONObject jsonNombre = jsonFoto.getJSONObject("nombre");
					JSONObject jsonThumb = jsonNombre.getJSONObject("thumb");
					dirFotoThumb = jsonThumb.getString("url");
					dirFotoCompleta = jsonNombre.getString("url");
					String imageUrlThumb = "";
					String imageUrlCompleta = "";					
					if (Constantes.WEB) {
						imageUrlThumb = Constantes.DIRSERVER + dirFotoThumb;
						imageUrlCompleta = Constantes.DIRSERVER + dirFotoCompleta;						
					} else {
						imageUrlThumb =  "http://" + Constantes.IPSERVER	+ dirFotoThumb;
						imageUrlCompleta = "http://" + Constantes.IPSERVER	+ dirFotoCompleta;					
					}
					
					this.imagesUrlThumb[i] = imageUrlThumb;
					this.imagesUrlCompleta[i] = imageUrlCompleta;
		
				}
			}

			JSONArray jsonVideos = json.getJSONArray("videos"); // Cargo Videos
			this.cantVideos = jsonVideos.length();
			JSONObject jsonVideo;

			if (this.cantVideos >= 1) {
				this.idVideos = new String[this.cantVideos];
				for (int i = 0; i < this.cantVideos; i++) {
					jsonVideo = jsonVideos.getJSONObject(i);
					this.idVideos[i] = extractYoutubeId(jsonVideo.getString("url"));
					}
			}

			long id = ++Propiedad.contador;
			this.id = id;
			
			JSONArray jsonAmenities = json.getJSONArray("amenities");
			this.amenities = new ArrayList<String>();
			
			// Usuario anunciante
			JSONObject jsonUser = json.getJSONObject("user");
			if (!jsonUser.isNull("telefono"))
				this.telefono = jsonUser.getString("telefono");
			if (!jsonUser.isNull("email"))
				this.email = jsonUser.getString("email");
			if (!jsonUser.isNull("nombre"))
				this.nombre = jsonUser.getString("nombre");
			if (!jsonUser.isNull("apellido"))
				this.apellido = jsonUser.getString("apellido");

			// Amenities
			for (int i = 0; i < jsonAmenities.length(); i++) {
				this.amenities.add(jsonAmenities.getJSONObject(i).getString(
						"nombre"));
			}
			
			// Datos propiedad
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
			if (!json.isNull("tipo_publicacion_id"))
				this.tipoPublicacionId = json.getInt("tipo_publicacion_id");
			if (!json.isNull("barrio_id"))
				this.barrioId = json.getInt("barrio_id");
			if (!json.isNull("tipo_publicacion"))
				this.tipoPublicacion = json.getString("tipo_publicacion");
			else
				this.tipoPublicacion = "Gratuita";
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public String extractYoutubeId(String url) throws MalformedURLException {
		String query = new URL(url).getQuery();
		String[] param = query.split("&");
		String id = null;
		for (String row : param) {
			String[] param1 = row.split("=");
			if (param1[0].equals("v")) {
				id = param1[1];
			}
		}
		return id;
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

	public String getTelefono() {
		return telefono;
	}
	public String getEmail() {
		return email;
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
	
	public String getTipoPublicacion() {
		return tipoPublicacion;
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

	public int getTipoPublicacionId() {
		return tipoPublicacionId;
	}

	public void setTipoPublicacionId(int tipoPublicacionId) {
		this.tipoPublicacionId = tipoPublicacionId;
	}

	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String[] getIdVideos() {
		return idVideos;
	}

	public int getCantVideos() {
		return cantVideos;
	}
	

	public String[] getImagesUrlThumb() {
		return imagesUrlThumb;
	}

	public void setImagesUrlThumb(String[] imagesUrlThumb) {
		this.imagesUrlThumb = imagesUrlThumb;
	}

	public String[] getImagesUrlCompleta() {
		return imagesUrlCompleta;
	}

	public void setImagesUrlCompleta(String[] imagesUrlCompleta) {
		this.imagesUrlCompleta = imagesUrlCompleta;
	}
	
	public void setFotosThumb(Bitmap[] fotosThumb) {
		this.fotosThumb = fotosThumb;
	}

	public void setFotosCompleta(Bitmap[] fotosCompleta) {
		this.fotosCompleta = fotosCompleta;
	}
	
	public boolean descargaDeDatosCompleta() {
		
		if (this.imagesUrlCompleta == null && this.imagesUrlThumb == null)
			return true;
		if (this.fotosThumb == null || this.fotosCompleta == null)
			return false;
		if (this.fotosThumb.length != this.imagesUrlThumb.length || this.fotosCompleta.length != this.imagesUrlCompleta.length)
			return false;
		return true;
	}

	public int getBarrioId() {
		return barrioId;
	}

	public void setBarrioId(int barrioId) {
		this.barrioId = barrioId;
	}

}

