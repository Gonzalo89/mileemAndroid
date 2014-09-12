package com.g7.mileemandroid.Activites;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.AdapterPropiedad;
import com.g7.mileemandroid.Model.Propiedad;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class ListaPropiedadesActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_propiedades);

		ListView listaPropiedades= (ListView) findViewById(R.id.listaPropiedades);
		ArrayList<Propiedad> arrayProp = new ArrayList<Propiedad>();
		Propiedad propiedad;

	//Intento de leer JSON sin exito
		/* 	
		// 1. create HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		HttpPost httppost = new HttpPost("http://"
				+ Constantes.IPSERVER + ":3000/propiedads.json");
	//	MultipartEntity mpEntity = new MultipartEntity();
	//	mpEntity.addPart("jsonString", new StringBody(json));

	//	httppost.setEntity(mpEntity);

		HttpResponse resp = null;
		try {
			resp = httpclient.execute(httppost);
			HttpEntity ent = resp.getEntity();// y obtenemos una respuesta
			String text = EntityUtils.toString(ent);
			Log.d("Mileem", "Respuesta ListaProp: " + text);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	
		// Introduzco los datos Harcodeados
		propiedad = new Propiedad("Av. Rivadavia","Una descripcion", 5,
				"Venta", 100000, 150, 5,
				3, 300, "Belgrano", "Casa", getResources().getDrawable(R.drawable.casa1), 1);
		arrayProp.add(propiedad);	
		
		propiedad = new Propiedad("Cosquin","Una descripcion", 5,
				"Venta", 100000, 150, 5,
				3, 300, "Belgrano", "Casa", getResources().getDrawable(R.drawable.casa2), 2);
		arrayProp.add(propiedad);	

		propiedad = new Propiedad("J. L. Suarez","Una descripcion", 5,
				"Venta", 100000, 150, 5,
				3, 300, "Belgrano", "Casa", getResources().getDrawable(R.drawable.casa3), 3);
		arrayProp.add(propiedad);	
		
		// Creo el adapter personalizado
		AdapterPropiedad adapter = new AdapterPropiedad(this, arrayProp);

		// Lo aplico
		listaPropiedades.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_propiedades, menu);
		return true;
	}

}
