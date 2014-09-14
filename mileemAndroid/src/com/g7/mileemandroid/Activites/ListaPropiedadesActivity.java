package com.g7.mileemandroid.Activites;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.AdapterPropiedad;
import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.Propiedad;

public class ListaPropiedadesActivity extends ActionBarActivity {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_propiedades);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("  Propiedades Disponibles");
		
		cargarPropiedades();
	}
	
	private void cargarPropiedades() {
		
		ListView listaPropiedades = (ListView) findViewById(R.id.listaPropiedades);
		ArrayList<Propiedad> arrayProp = new ArrayList<Propiedad>();
		Propiedad propiedad;

		// Lectura de JSON

		// 1. create HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		HttpGet httpget = new HttpGet("http://" + Constantes.IPSERVER
				+ ":3000/api/mostrarJson");
		
		HttpResponse resp = null;
		String respuesta = null;
		try {
			resp = httpclient.execute(httpget);
			HttpEntity ent = resp.getEntity();// y obtenemos una respuesta
			respuesta = EntityUtils.toString(ent);
			Log.d("Mileem", "Respuesta ListaProp: " + respuesta);
			// Introduzco los datos No Harcodeados
			JSONArray jsonArray = new JSONArray(respuesta);
			JSONObject unJson = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				unJson = jsonArray.getJSONObject(i);
				propiedad = new Propiedad(unJson);
				arrayProp.add(propiedad);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		
/*		// Introduzco los datos Harcodeados
		propiedad = new Propiedad("Av. Rivadavia", "Una descripcion", 5,
				"Venta", 100000, 150, 5, 3, 300, "Belgrano", "Casa",
				getResources().getDrawable(R.drawable.casa1), 155);
		arrayProp.add(propiedad);

		propiedad = new Propiedad("Cosquin", "Una descripcion", 5, "Venta",
				100000, 150, 5, 3, 300, "Belgrano", "Casa", getResources()
						.getDrawable(R.drawable.casa2), 156);
		arrayProp.add(propiedad);

		propiedad = new Propiedad("J. L. Suarez", "Una descripcion", 5,
				"Venta", 100000, 150, 5, 3, 300, "Belgrano", "Casa",
				getResources().getDrawable(R.drawable.casa3), 157);
		arrayProp.add(propiedad);*/

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

	public class PropiedadesTask extends AsyncTask<String, Void, String>{
		
	    Context context;

	    public PropiedadesTask(Context c) {
	        context = c;
	    }

	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	    }

	    @Override
	    protected String doInBackground(String... aurl){
	    String responseString="";
	    HttpClient client = null;
	    try {
	         client = new DefaultHttpClient();  
	         HttpGet get = new HttpGet("http://" + Constantes.IPSERVER + ":3000/api/mostrarJson");
	         HttpResponse responseGet = client.execute(get);  
	         HttpEntity resEntityGet = responseGet.getEntity();  
	         if (resEntityGet != null) {  
	             responseString = EntityUtils.toString(resEntityGet);
	             Log.i("GET RESPONSE", responseString.trim());
	         }
	    } catch (Exception e) {
	        Log.d("ANDRO_ASYNC_ERROR", "Error is "+e.toString());
	    }
	        Log.d("ANDRO_ASYNC_RESPONSE", responseString.trim());
	        client.getConnectionManager().shutdown();
	     return responseString.trim();

	    }


	    @Override
	    protected void onPostExecute(String response) {
	         super.onPostExecute(response); 
	        }
	}
}
