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
	private String barrio;
	ArrayList<String> barriosVecinos;
	ArrayList<Integer> precioDolaresM2Vecinos;
	ArrayList<String> barriosVecinosConM2EnDolares;
	private int maxDolaresM2;
	
	public EstadisticaBarrio(String json) {
		barriosVecinos = new ArrayList<String>();
		precioDolaresM2Vecinos = new ArrayList<Integer>();
		barriosVecinosConM2EnDolares = new ArrayList<String>();
		JSONObject jsonObject = null;
   		JSONArray jsonArray = null;
   		try {
   			jsonObject = new JSONObject(json);
   			this.amb1 = jsonObject.getInt("cantCodAmb1");
   			this.amb2 = jsonObject.getInt("cantCodAmb2");
   			this.amb3 = jsonObject.getInt("cantCodAmb3");
   			this.amb4 = jsonObject.getInt("cantCodAmb4");
   			this.totalPropiedades = amb1 + amb2+ amb3 + amb4;
   			this.barrio = jsonObject.getString("nombreBarrio");
   			this.calcularPorcentajesAmb();
   			
			jsonArray = jsonObject.getJSONArray("vecinos"); ;
			JSONObject unJson = null;
			
			barriosVecinos.add(jsonObject.getString("nombreBarrio"));//Se carga el barrio original
			precioDolaresM2Vecinos.add(jsonObject.getInt("promedioM2Dolares"));
			barriosVecinosConM2EnDolares.add(jsonObject.getString("nombreBarrio")+ " u$s" + jsonObject.getString("promedioM2Dolares"));
			String barrioM2EnDolares;
			for (int i = 0; i < jsonArray.length(); i++) {
				unJson = jsonArray.getJSONObject(i);				
				barriosVecinos.add(unJson.getString("vecino_nombre"));
				precioDolaresM2Vecinos.add(unJson.getInt(("promedioM2Dolares")));
				barrioM2EnDolares = unJson.getString("vecino_nombre") + "\n" + " u$s" + unJson.getString("promedioM2Dolares");
				barriosVecinosConM2EnDolares.add(barrioM2EnDolares);				
			}
			this.maxDolaresM2 = Collections.max(precioDolaresM2Vecinos);
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
			this.pAmb1 = ((float)this.amb1 / (float)this.totalPropiedades)*100;
			this.pAmb2 = ((float)this.amb2 / (float)this.totalPropiedades)*100;
			this.pAmb3 = ((float)this.amb3 / (float)this.totalPropiedades)*100;
			this.pAmb4 = ((float)this.amb4 / (float)this.totalPropiedades)*100;
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


	public int getMaxDolaresM2() {
		return maxDolaresM2;
	}

	public int getTotalPropiedades() {
		return totalPropiedades;
	}

	public String getBarrio() {
		return barrio;
	}

	public ArrayList<String> getBarriosVecinosConM2EnDolares() {
		return barriosVecinosConM2EnDolares;
	}

	public ArrayList<Integer> getPrecioDolaresM2Vecinos() {
		return precioDolaresM2Vecinos;
	}

	public int getAmb(int i) {
		switch(i){
		case 0:
			return getAmb1();
		case 1:
			return getAmb2();
		case 2:
			return getAmb3();
		case 3:
			return getAmb4();
		}
		return -1;
	}

	public int getAmb1() {
		return amb1;
	}

	public int getAmb2() {
		return amb2;
	}

	public int getAmb3() {
		return amb3;
	}

	public int getAmb4() {
		return amb4;
	}

	
}
