package com.g7.mileemandroid.Model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EstadisticaBarrio {
	private int amb1;
	private int amb2;
	private int amb3;
	private int amb4;
	private int totalPropiedades;
	private float pAmb1;
	private float pAmb2;
	private float pAmb3;
	private float pAmb4;
	ArrayList<String> barriosVecinos;
	ArrayList<Float> precioPesosM2Vecinos;
	
	public EstadisticaBarrio(String json) {
		barriosVecinos = new ArrayList<String>();
		JSONObject jsonObject = null;
   		JSONArray jsonArray = null;
   		try {
   			jsonObject = new JSONObject(json);
   			this.amb1 = jsonObject.getInt("cantCodAmb1");
   			this.amb2 = jsonObject.getInt("cantCodAmb2");
   			this.amb3 = jsonObject.getInt("cantCodAmb3");
   			this.amb4 = jsonObject.getInt("cantCodAmb4"); 
   			this.calcularPorcentajesAmb();
   			
			jsonArray = jsonObject.getJSONArray("vecinos"); ;
			JSONObject unJson = null;
			
			for (int i = 0; i < jsonArray.length(); i++) {
				unJson = jsonArray.getJSONObject(i);
				barriosVecinos.add(unJson.getString("vecino_nombre"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getBarriosVecinos() {
		return barriosVecinos;
	}
	
	private void calcularPorcentajesAmb() {
		this.totalPropiedades = this.amb1 + this.amb2 + this.amb3 + this.amb4;
		if (this.totalPropiedades == 0) {
			this.pAmb1 = 25;
			this.pAmb2 = 25;
			this.pAmb3 = 25;
			this.pAmb4 = 25;
		} else {
			this.pAmb1 = this.amb1 / this.totalPropiedades;
			this.pAmb2 = this.amb2 / this.totalPropiedades;
			this.pAmb3 = this.amb3 / this.totalPropiedades;
			this.pAmb4 = this.amb4 / this.totalPropiedades;
		}
	}

	public float getpAmb1() {
		return pAmb1;
	}

	public float getpAmb2() {
		return pAmb2;
	}

	public float getpAmb3() {
		return pAmb3;
	}

	public float getpAmb4() {
		return pAmb4;
	}

}
