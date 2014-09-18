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

import android.app.Activity;
import android.app.ProgressDialog;
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
	private ProgressDialog loadingSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_propiedades);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("  Propiedades Disponibles");
		
		String url = "http://" + Constantes.IPSERVER + ":3000/api/mostrarJson";
		loadingSpinner = new ProgressDialog(this);
		loadingSpinner.setMessage("Procesando...");
		loadingSpinner.setCancelable(false);
		loadingSpinner.show();
		new PropiedadesTask(this).execute( url );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_propiedades, menu);
		return true;
	}
	
	private String downloadJson( String url ) throws ClientProtocolException, IOException {
		
   		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		HttpGet httpget = new HttpGet( url );
		
		HttpResponse resp = null;
		String stringJson = null;
		
		resp = httpclient.execute(httpget);
		HttpEntity ent = resp.getEntity();// y obtenemos una respuesta
		stringJson = EntityUtils.toString(ent);
		Log.d("Mileem", "Respuesta ListaProp: " + stringJson);
		return stringJson;
	}
	
	private ArrayList<Propiedad> parsearPropiedadesJson ( String json ) {
		
		ArrayList<Propiedad> arrayProp = null;
   		JSONArray jsonArray = null;
   		Propiedad propiedad = null;
		try {
			jsonArray = new JSONArray( json );
			JSONObject unJson = null;
			
			arrayProp = new ArrayList<Propiedad>();
			for (int i = 0; i < jsonArray.length(); i++) {
				unJson = jsonArray.getJSONObject(i);
				propiedad = new Propiedad(unJson);
				arrayProp.add(propiedad);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayProp;
	}

    private class PropiedadesTask extends AsyncTask<String, Void, String> {
    	
    	Context context;
        
    	
    	public PropiedadesTask( Context context ) {
            this.context = context;
   //         
        }
    	
       @Override
       protected String doInBackground(String... urls) {
             
   // 	   loadingSpinner.show();
           // params comes from the execute() call: params[0] is the url.
           try {
        	   	return downloadJson( urls[0]);
	   		} catch (ClientProtocolException e) {
	   			e.printStackTrace();
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
		return null;
       }
       
       // onPostExecute displays the results of the AsyncTask.
       @Override
       protected void onPostExecute(String result) {
    	   
    	   ArrayList<Propiedad> propiedades = parsearPropiedadesJson( result );
    	   AdapterPropiedad adapter = new AdapterPropiedad( (Activity) this.context , propiedades);
    	   ListView listaPropiedades = (ListView) findViewById(R.id.listaPropiedades);
    	   listaPropiedades.setAdapter(adapter);
    	   loadingSpinner.dismiss();
      }
   }
}
