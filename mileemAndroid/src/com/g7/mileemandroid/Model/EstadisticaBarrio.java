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

}
