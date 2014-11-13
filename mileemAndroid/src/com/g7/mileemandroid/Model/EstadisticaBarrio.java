package com.g7.mileemandroid.Model;

import java.util.ArrayList;
import java.util.Collections;

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
	ArrayList<Integer> precioPesosM2Vecinos;
	private int maxPesosM2;
	
	public EstadisticaBarrio(String json) {
		barriosVecinos = new ArrayList<String>();
		precioPesosM2Vecinos = new ArrayList<Integer>();
		JSONObject jsonObject = null;
   		JSONArray jsonArray = null;
   		try {
   			jsonObject = new JSONObject(json);
   			this.amb1 = jsonObject.getInt("cantCodAmb1");
   			this.amb2 = jsonObject.getInt("cantCodAmb2");
   			this.amb3 = jsonObject.getInt("cantCodAmb3");
   			this.amb4 = jsonObject.getInt("cantCodAmb4");
   			this.totalPropiedades = amb1 + amb2+ amb3 + amb4;
   			this.calcularPorcentajesAmb();
   			
			jsonArray = jsonObject.getJSONArray("vecinos"); ;
			JSONObject unJson = null;
			
			barriosVecinos.add(jsonObject.getString("nombreBarrio"));//Se carga el barrio original
			precioPesosM2Vecinos.add(jsonObject.getInt("promedioM2"));
			for (int i = 0; i < jsonArray.length(); i++) {
				unJson = jsonArray.getJSONObject(i);
				barriosVecinos.add(unJson.getString("vecino_nombre"));
				precioPesosM2Vecinos.add(unJson.getInt(("promedioM2")));
			}
			this.maxPesosM2 = Collections.max(precioPesosM2Vecinos);
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

	public ArrayList<Integer> getPrecioPesosM2Vecinos() {
		return precioPesosM2Vecinos;
	}

	public int getMaxPesosM2() {
		return maxPesosM2;
	}

	public int getTotalPropiedades() {
		return totalPropiedades;
	}

}
