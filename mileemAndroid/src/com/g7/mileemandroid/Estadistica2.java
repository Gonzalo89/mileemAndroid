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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dacer.androidcharts.BarView;
import com.dacer.androidcharts.PieHelper;
import com.dacer.androidcharts.PieView;
import com.g7.mileemandroid.Model.AdapterPropiedad;
import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.EstadisticaBarrio;
import com.g7.mileemandroid.Model.Propiedad;
import com.g7.mileemandroid.Model.PropiedadSingleton;

public class Estadistica2 extends Activity {

	EstadisticaBarrio estadisticaBarrio;
	private ProgressDialog loadingSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadistica2);

		loadingSpinner = new ProgressDialog(this);
		loadingSpinner.setMessage("Procesando búsqueda...");
		loadingSpinner.setCancelable(false);
		loadingSpinner.show();

		String url = Constantes.DIRESTADISTICAS + "1"; // TODO harcodeado
		Log.d("Envio", "Peticion: " + url);
		new EstadisticasTask(this).execute(url);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadistica2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
				Propiedad unaPropiedad = PropiedadSingleton.getPropiedad();

				TextView barrio = (TextView) findViewById(R.id.textViewBarrio);
				barrio.setText(unaPropiedad.getBarrio());

				float pAmb1 = 20;// TODO no harcodear
				float pAmb2 = 30;
				float pAmb3 = 30;
				float pAmb4 = 20;

				// Grafico de torta
				PieHelper amb1 = new PieHelper(pAmb1, Color.BLUE);
				PieHelper amb2 = new PieHelper(pAmb2, Color.GREEN);
				PieHelper amb3 = new PieHelper(pAmb3, Color.RED);
				PieHelper amb4 = new PieHelper(pAmb4, Color.MAGENTA);
				PieView pieView = (PieView) findViewById(R.id.pie_view);
				ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
				pieHelperArrayList.add(amb1);
				pieHelperArrayList.add(amb2);
				pieHelperArrayList.add(amb3);
				pieHelperArrayList.add(amb4);
				pieView.setDate(pieHelperArrayList);
				pieView.showPercentLabel(true);

				// Grafico de barras
				BarView barView = (BarView) findViewById(R.id.bar_view);
				//ArrayList<String> barriosAdyacentes = new ArrayList<String>();
				/*ArrayList<String> strList = new ArrayList<String>(); // TODO no
																		// harcodear
				strList.add("barrio1");
				strList.add("barrio2");
				strList.add("barrio3");
				strList.add("barrio4");
				strList.add("barrio5");
				strList.add("barrio6");
				strList.add("barrio7");
				barView.setBottomTextList(strList);*/
				barView.setBottomTextList(estadisticaBarrio.getBarriosVecinos());
				ArrayList<Integer> dataList = new ArrayList<Integer>();
				dataList.add(Integer.valueOf(20));
				dataList.add(Integer.valueOf(30));
				dataList.add(Integer.valueOf(40));
				dataList.add(Integer.valueOf(50));
				dataList.add(Integer.valueOf(10));
				dataList.add(Integer.valueOf(22));
				dataList.add(Integer.valueOf(12));
				barView.setDataList(dataList, 50);
			} else {
				Toast.makeText(this.context, "Error al conectarse al servidor",
						Toast.LENGTH_SHORT).show();
			}
			loadingSpinner.dismiss();
		}
	}

}
