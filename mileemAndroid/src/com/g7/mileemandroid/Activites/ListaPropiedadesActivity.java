package com.g7.mileemandroid.Activites;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.AdapterPropiedad;
import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.Propiedad;
import com.g7.mileemandroid.Model.PropiedadSingleton;

public class ListaPropiedadesActivity extends ActionBarActivity {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private ProgressDialog loadingSpinner;
	private ListView listaPropiedades;
	private ArrayList<Propiedad> propiedades;

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


	protected void verDetalle(Long idProp) {
    	Intent intent = new Intent(this, DetallePropiedad.class);    	
    	Propiedad unaPropiedad = this.buscarPropiedad(idProp);
    	PropiedadSingleton.setPropiedad(unaPropiedad);
    	startActivity(intent);			
	}


	private Propiedad buscarPropiedad(Long idProp) {
		Propiedad resultado = null;
		Iterator<Propiedad> it = propiedades.iterator();
		while(it.hasNext()) {
			resultado = it.next();
			if(resultado.getId() == idProp)
				return resultado;
		}
		return null;
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
        }
    	
       @Override
       protected String doInBackground(String... urls) {
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
			propiedades = parsearPropiedadesJson(result);
			if (propiedades != null) {
				AdapterPropiedad adapter = new AdapterPropiedad(
						(Activity) this.context, propiedades);
				listaPropiedades = (ListView) findViewById(R.id.listaPropiedades);
				listaPropiedades.setAdapter(adapter);
				listaPropiedades
						.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> a, View v, int position, long id) {
								Long idProp = ((Propiedad) a.getAdapter().getItem(position)).getId();
								verDetalle(idProp);
							}
						});
			}
			loadingSpinner.dismiss();
		}
	}
}
