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
				WebView webView = (WebView) getActivity()
						.findViewById(R.id.web);
				webView.addJavascriptInterface(new WebAppInterface(), "Android");

				webView.getSettings().setJavaScriptEnabled(true);
				webView.loadUrl("http://young-tundra-9853.herokuapp.com/columnchart");

			} else {
				Toast.makeText(this.context, "Error al conectarse al servidor",
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	public class WebAppInterface {

		@JavascriptInterface
		public ArrayList<String> getBarriosGraficoBarras(){
			return estadisticaBarrio.getBarriosVecinos();
		}
		
		@JavascriptInterface
		public ArrayList<String> getPrecioPromedioDeBarrios(){
			return estadisticaBarrio.getBarriosVecinosConM2EnDolares();
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
