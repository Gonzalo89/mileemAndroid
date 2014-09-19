package com.g7.mileemandroid.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.g7.mileemandroid.Model.Propiedad;

public class TestPropiedades extends TestCase {
	private Propiedad unaProp;
	

	protected void setUp() throws Exception {
		super.setUp();

	}
	
	public void testJSONCreator() { //FIXME NO PUEDO HACER EL TEST PORQUE NO ME LEE EL ARCHIVO
		File archivo = new File("raw/json1.txt");
		FileReader fr;
		try {
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String jsonString;
			while ((jsonString = br.readLine()) != null)
				jsonString += jsonString;
			br.close();
			JSONObject json = new JSONObject(jsonString);
			unaProp = new Propiedad(json);
		} catch (IOException e) {			
			e.printStackTrace();
			Log.e("Error: ", "Error al leer archico " + e.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
