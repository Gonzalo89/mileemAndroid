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

import com.dacer.androidcharts.BarView;
import com.dacer.androidcharts.PieHelper;
import com.dacer.androidcharts.PieView;
import com.dacer.androidcharts.PieView.OnPieClickListener;
import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.EstadisticaBarrio;
import com.google.android.youtube.player.YouTubePlayerView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class FragmentTorta extends Fragment {
	EstadisticaBarrio estadisticaBarrio;
	int barrioId;	
	
		public FragmentTorta(int barrioId) {
		super();
		this.barrioId = barrioId;
	}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_fragment_torta,
					container, false);
			return rootView;
		}
	
		@Override
		public void onActivityCreated(Bundle bundle) {
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

			@Override
			protected void onPostExecute(EstadisticaBarrio result) {
				estadisticaBarrio = result;
				if (estadisticaBarrio != null) {
					TextView barrio = (TextView) getActivity().findViewById(R.id.textViewBarrio2);
					barrio.setText(estadisticaBarrio.getBarrio());				
					TextView totalPropiedades = (TextView) getActivity().findViewById(R.id.TotalPropiedades2);
					totalPropiedades.setText(getResources().getString(R.string.TotalDePublicaciones) + 
							estadisticaBarrio.getTotalPropiedades());

					float pAmb1 = estadisticaBarrio.getpAmb1();
					float pAmb2 = estadisticaBarrio.getpAmb2();
					float pAmb3 = estadisticaBarrio.getpAmb3();
					float pAmb4 = estadisticaBarrio.getpAmb4();

					// Grafico de torta
					PieHelper amb1 = new PieHelper(pAmb1, Color.BLUE);
					PieHelper amb2 = new PieHelper(pAmb2, Color.GREEN);
					PieHelper amb3 = new PieHelper(pAmb3, Color.RED);
					PieHelper amb4 = new PieHelper(pAmb4, Color.MAGENTA);
					PieView pieView = (PieView) getActivity().findViewById(R.id.pie_view);
					ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
					pieHelperArrayList.add(amb1);
					pieHelperArrayList.add(amb2);
					pieHelperArrayList.add(amb3);
					pieHelperArrayList.add(amb4);
					pieView.setDate(pieHelperArrayList);
					pieView.showPercentLabel(true);
					pieView.setOnPieClickListener(new OnPieClickListener() {
						
						@Override
						public void onPieClick(int index) {
							int cantidad = estadisticaBarrio.getAmb(index);
							Toast toast1 =
						            Toast.makeText(getActivity(),
						                    Integer.toString(cantidad) /*+ "Index: " +index*/, Toast.LENGTH_SHORT);
						 
						        toast1.show();						
						}
					});

				} else {
					Toast.makeText(this.context, "Error al conectarse al servidor",
							Toast.LENGTH_SHORT).show();
				}
			
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
