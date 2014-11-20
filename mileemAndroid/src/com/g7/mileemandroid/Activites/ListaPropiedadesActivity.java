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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.g7.mileemandroid.Estadistica2;
import com.g7.mileemandroid.GraficosTabs;
import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.AdapterPropiedad;
import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.FiltroSingleton;
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
		String url = "";
		
		if(Constantes.WEB)
			url = Constantes.DIRSERVER + Constantes.DIRFILTRO;
		else
			url = "http://" + Constantes.IPSERVER + Constantes.DIRFILTRO;
		
		FiltroSingleton filtro = FiltroSingleton.getInstance();
		if(filtro.getOperacion() != 0)
			url += "&operacionId=" + filtro.getOperacion();		
		if(filtro.getBarrio() != 0)
			url += "&barrioId="+ filtro.getBarrio();
		if(filtro.gettPropiedad() != 0)
			url += "&tipoPropiedadId="+ filtro.gettPropiedad();
		if(filtro.getAmbientes() != 0)
			url += "&codAmb="+ filtro.getAmbientes();		
		if(filtro.getPrecio() != 0)
			url += "&codPrecio="+ filtro.getPrecio();
		if(filtro.getOrden() != 0)
			url += "&codOrd="+ filtro.getOrden();
		if(filtro.getFechaPublicacion() != 0)
			url += "&codFecha="+ filtro.getFechaPublicacion();		
		if(filtro.getSupCubierta() != 0)
			url += "&codSup="+ filtro.getSupCubierta();	
		
		loadingSpinner = new ProgressDialog(this);
		loadingSpinner.setMessage("Procesando busqueda...");
		loadingSpinner.setCancelable(false);
		loadingSpinner.show();
		Log.d("Envio", "Peticion: " + url);
		new PropiedadesTask(this).execute( url );
	}


	protected void verDetalle(Long idProp) {
    	
    	Propiedad propiedad = this.buscarPropiedad(idProp);
    	if ( !propiedad.descargaDeDatosCompleta() ) {
			Toast.makeText(this, "Cargando datos de la propiedad, por favor espere y vuelva a intentarlo..", Toast.LENGTH_SHORT).show();
			return;
    	}
    		
    	Intent intent = new Intent(this, DetallePropiedadTabs.class);
    	PropiedadSingleton.setPropiedad(propiedad);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.MnuFiltro:
			Log.d("Boton", "Apreto menu filtro");
			Intent intent = new Intent(this, FiltrosActivity.class);
			startActivity(intent);
			return true;
		case R.id.MnuEstadisticas:
			return true;
		default:			
			String nombreBarrio = (String) item.getTitle();				
			Log.d("Boton", "Apreto menu Estadisticas");
			int id = obtenerIdBarrio(nombreBarrio);
			//Intent intent2 = new Intent(this, Estadistica2.class);
			Intent intent2 = new Intent(this, GraficosTabs.class);
			intent2.putExtra(Constantes.BARRIO_ID, id);
			startActivity(intent2);
			return true;	
		}
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
	
    private class PropiedadesTask extends AsyncTask<String, Void, ArrayList<Propiedad>> {
    	
    	Context context;
        
    	
    	public PropiedadesTask( Context context ) {
            this.context = context;         
        }
    	
       @Override
       protected ArrayList<Propiedad> doInBackground(String... urls) {
           // params comes from the execute() call: params[0] is the url.
           try {        	   
        	   	return  parsearPropiedadesJson(downloadJson( urls[0]));
	   		} catch (ClientProtocolException e) {
	   			e.printStackTrace();
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
		return null;
       }
       
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(ArrayList<Propiedad> result) {
			propiedades = result;
			if (propiedades != null) {
				
				listaPropiedades = (ListView) findViewById(R.id.listaPropiedades);
				AdapterPropiedad adapter = new AdapterPropiedad((Activity) this.context, propiedades, listaPropiedades);
				listaPropiedades.setAdapter(adapter);
				listaPropiedades
						.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> a, View v, int position, long id) {
								Long idProp = ((Propiedad) a.getAdapter().getItem(position)).getId();
								verDetalle(idProp);
							}
						});
			} else {
				Toast.makeText(this.context, "Error al conectarse al servidor", Toast.LENGTH_SHORT).show();
			}
			loadingSpinner.dismiss();
		}
	}

	private int obtenerIdBarrio(String nombreBarrio) {
		int id = 1;
		switch (nombreBarrio) {
		case "Agronomía":
			id = 1;
			break;
		case "Almagro":
			id = 2;
			break;
		case "Balvanera":
			id = 3;
			break;
		case "Barracas":
			id = 4;
			break;
		case "Belgrano":
			id = 5;
			break;
		case "Boedo":
			id = 6;
			break;
		case "Caballito":
			id = 7;
			break;
		case "Chacarita":
			id = 8;
			break;
		case "Coghlan":
			id = 9;
			break;
		case "Colegiales":
			id = 10;
			break;
		case "Constitución":
			id = 11;
			break;
		case "Flores":
			id = 12;
			break;
		case "Floresta":
			id = 13;
			break;
		case "La Boca":
			id = 14;
			break;
		case "La Paternal":
			id = 15;
			break;
		case "Liniers":
			id = 16;
			break;
		case "Mataderos":
			id = 17;
			break;
		case "Monte Castro":
			id = 18;
			break;
		case "Monserrat":
			id = 19;
			break;
		case "Nueva Pompeya":
			id = 20;
			break;
		case "Núñez":
			id = 21;
			break;
		case "Palermo":
			id = 22;
			break;
		case "Parque Avellaneda":
			id = 23;
			break;
		case "Parque Chacabuco":
			id = 24;
			break;
		case "Parque Chas":
			id = 25;
			break;
		case "Parque Patricios":
			id = 26;
			break;
		case "Puerto Madero":
			id = 27;
			break;
		case "Recoleta":
			id = 28;
			break;
		case "Retiro":
			id = 29;
			break;
		case "Saavedra":
			id = 30;
			break;
		case "San Cristóbal":
			id = 31;
			break;
		case "San Nicolás":
			id = 32;
			break;
		case "San Telmo":
			id = 33;
			break;
		case "Vélez Sársfield":
			id = 34;
			break;
		case "Versalles":
			id = 35;
			break;
		case "Villa Crespo":
			id = 36;
			break;
		case "Villa del Parque":
			id = 37;
			break;
		case "Villa Devoto":
			id = 38;
			break;
		case "Villa General Mitre":
			id = 39;
			break;
		case "Villa Lugano":
			id = 40;
			break;
		case "Villa Luro":
			id = 41;
			break;
		case "Villa Ortúzar":
			id = 42;
			break;
		case "Villa Pueyrredón":
			id = 43;
			break;
		case "Villa Real":
			id = 44;
			break;
		case "Villa Riachuelo":
			id = 45;
			break;
		case "Villa Santa Rita":
			id = 46;
			break;
		case "Villa Soldati":
			id = 47;
			break;
		case "Villa Urquiza":
			id = 48;
			break;
		}
		return id;
	}
    
}
