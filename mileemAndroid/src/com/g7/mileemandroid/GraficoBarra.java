package com.g7.mileemandroid;

import java.io.IOException;
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

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.EstadisticaBarrio;

public class GraficoBarra extends Fragment {
	EstadisticaBarrio estadisticaBarrio;
	int barrioId;
	int cantBarriosVecinos = -1;
	String Barrio1;
	String Barrio2;
	String Barrio3;
	String Barrio4;
	String Barrio5;
	String Barrio6;
	String Barrio7;
	String Barrio8;
	String Barrio9;
	int precioBarrio1 = -1;
	int precioBarrio2 = -1;
	int precioBarrio3 = -1;
	int precioBarrio4 = -1;
	int precioBarrio5 = -1;
	int precioBarrio6 = -1;
	int precioBarrio7 = -1;
	int precioBarrio8 = -1;
	int precioBarrio9 = -1;
	
	public GraficoBarra(int barrioId) {
		super();
		this.barrioId = barrioId;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {	 
        View rootView = inflater.inflate(R.layout.activity_grafico_barra, container, false);
        return rootView;
	}

	@Override
	   public void onActivityCreated(Bundle bundle){		
		super.onActivityCreated(bundle); 
		String url = Constantes.DIRESTADISTICAS + barrioId;
		Log.d("Envio", "Peticion: " + url);
		new EstadisticasTask(getActivity()).execute(url);     
		
	}
	
	// ////////////////////////////////////////////////////////////////////////////
			private class EstadisticasTask extends
					AsyncTask<String, Void, EstadisticaBarrio> {

				Context context;

				public EstadisticasTask(Context context) {
					this.context = context;
				}

				@Override
				protected EstadisticaBarrio doInBackground(String... urls) {
					try {
						return new EstadisticaBarrio(downloadJson(urls[0]));
					} catch (ClientProtocolException e) {
							e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}

				@SuppressLint("SetJavaScriptEnabled")
				@Override
		protected void onPostExecute(EstadisticaBarrio result) {
			estadisticaBarrio = result;
			if (estadisticaBarrio != null) {
				cantBarriosVecinos = estadisticaBarrio.getBarriosVecinos().size();

				if(cantBarriosVecinos >= 1)
					Barrio1 = estadisticaBarrio.getBarriosVecinos().get(0);
				if(cantBarriosVecinos >= 2)
					Barrio2 = estadisticaBarrio.getBarriosVecinos().get(1);
				if(cantBarriosVecinos >= 3)
					Barrio3 = estadisticaBarrio.getBarriosVecinos().get(2);
				if(cantBarriosVecinos >= 4)
					Barrio4 = estadisticaBarrio.getBarriosVecinos().get(3);
				if(cantBarriosVecinos >= 5)
					Barrio5 = estadisticaBarrio.getBarriosVecinos().get(4);
				if(cantBarriosVecinos >= 6)
					Barrio6 = estadisticaBarrio.getBarriosVecinos().get(5);
				if(cantBarriosVecinos >= 7)
					Barrio7 = estadisticaBarrio.getBarriosVecinos().get(6);
				if(cantBarriosVecinos >= 8)
					Barrio8 = estadisticaBarrio.getBarriosVecinos().get(7);
				if(cantBarriosVecinos >= 9)
					Barrio9 = estadisticaBarrio.getBarriosVecinos().get(8);
				
				if(cantBarriosVecinos >= 1)
					precioBarrio1 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(0);
				if(cantBarriosVecinos >= 2)
					precioBarrio2 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(1);
				if(cantBarriosVecinos >= 3)
					precioBarrio3 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(2);
				if(cantBarriosVecinos >= 4)
					precioBarrio4 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(3);
				if(cantBarriosVecinos >= 5)
					precioBarrio5 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(4);
				if(cantBarriosVecinos >= 6)
					precioBarrio6 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(5);
				if(cantBarriosVecinos >= 7)
					precioBarrio7 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(6);
				if(cantBarriosVecinos >= 8)
					precioBarrio8 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(7);
				if(cantBarriosVecinos >= 9)
					precioBarrio9 = estadisticaBarrio.getPrecioDolaresM2Vecinos().get(8);				
				
				WebView webView = (WebView) getActivity()
						.findViewById(R.id.web);					
				webView.addJavascriptInterface(new WebAppInterface(), "Android");

				webView.getSettings().setJavaScriptEnabled(true);
				webView.loadUrl("http://young-tundra-9853.herokuapp.com/columnchart");
				//webView.loadUrl("http://young-tundra-9853.herokuapp.com/columnchartprueba");
			} else {
				Toast.makeText(this.context, "Error al conectarse al servidor",
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	public class WebAppInterface {

		/*
		 * @JavascriptInterface public String[] getBarriosGraficoBarras(){ int
		 * tam = estadisticaBarrio.getBarriosVecinos().size(); String[]
		 * resultado = new String[tam]; for (int i = 0; i <= tam ; i++ ){
		 * resultado[i] = estadisticaBarrio.getBarriosVecinos().get(i); } return
		 * resultado; }
		 * 
		 * @JavascriptInterface public String[] getPrecioPromedioDeBarrios(){
		 * int tam = estadisticaBarrio.getBarriosVecinosConM2EnDolares().size();
		 * String[] resultado = new String[tam]; for (int i = 0; i <= tam ; i++
		 * ){ resultado[i] =
		 * estadisticaBarrio.getBarriosVecinosConM2EnDolares().get(i); } return
		 * resultado; }
		 */

		@JavascriptInterface
		public String getBarrio1() {
			if (Barrio1 != null)
				return Barrio1;
			return null;
		}

		@JavascriptInterface
		public String getBarrio2() {
			if (Barrio2 != null)
				return Barrio2;
			return null;
		}

		@JavascriptInterface
		public String getBarrio3() {
			if (Barrio3 != null)
				return Barrio3;
			return null;
		}

		@JavascriptInterface
		public String getBarrio4() {
			if (Barrio4 != null)
				return Barrio4;
			return null;
		}

		@JavascriptInterface
		public String getBarrio5() {
			if (Barrio5 != null)
				return Barrio5;
			return null;
		}

		@JavascriptInterface
		public String getBarrio6() {
			if (Barrio6 != null)
				return Barrio6;
			return null;
		}

		@JavascriptInterface
		public String getBarrio7() {
			if (Barrio7 != null)
				return Barrio7;
			return null;
		}

		@JavascriptInterface
		public String getBarrio8() {
			if (Barrio8 != null)
				return Barrio8;
			return null;
		}

		@JavascriptInterface
		public String getBarrio9() {
			if (Barrio9 != null)
				return Barrio9;
			return null;
		}

		@JavascriptInterface
		public int getCantidadBarrios() {
			return cantBarriosVecinos;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio1() {
			return precioBarrio1;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio2() {
			return precioBarrio2;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio3() {
			return precioBarrio3;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio4() {
			return precioBarrio4;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio5() {
			return precioBarrio5;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio6() {
			return precioBarrio6;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio7() {
			return precioBarrio7;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio8() {
			return precioBarrio8;
		}

		@JavascriptInterface
		public int getEstadisticaBarrio9() {
			return precioBarrio9;
		}
	}

	private String downloadJson(String url) throws ClientProtocolException,
			IOException {

		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		HttpGet httpget = new HttpGet(url);

		HttpResponse resp = null;
		String stringJson = null;

		resp = httpclient.execute(httpget);
		HttpEntity ent = resp.getEntity();// y obtenemos una respuesta
		stringJson = EntityUtils.toString(ent);
		Log.d("Mileem", "Respuesta Estadistica Barrio: " + stringJson);
		return stringJson;
	}
}
