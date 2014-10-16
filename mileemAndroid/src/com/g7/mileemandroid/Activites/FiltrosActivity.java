package com.g7.mileemandroid.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.FiltroSingleton;

public class FiltrosActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtros);
		FiltroSingleton.getInstance().resetFiltroSecundario();
        Button bBuscar = (Button)findViewById(R.id.filtroBuscar);
        bBuscar.setBackgroundColor(Color.BLUE);
        bBuscar.setTextColor(Color.WHITE);
		ArrayAdapter<CharSequence> aPrecio = ArrayAdapter.createFromResource(this,
				R.array.precios_array, android.R.layout.simple_spinner_item);
		Spinner sPrecio = (Spinner)findViewById(R.id.sPrecio);		 
		aPrecio.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sPrecio.setAdapter(aPrecio);
		sPrecio.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("Spinner", "Precio id: "+ id);
		       FiltroSingleton.getInstance().setPrecio(id);
		    }			
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );
		
		ArrayAdapter<CharSequence> aSupCubierta = ArrayAdapter.createFromResource(this,
				R.array.sup_cubierta_array, android.R.layout.simple_spinner_item);
		Spinner sSupCubierta = (Spinner)findViewById(R.id.sSupCubierta);		 
		aSupCubierta.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sSupCubierta.setAdapter(aSupCubierta);
		sSupCubierta.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("Spinner", "Superficie id: "+ id);
		       FiltroSingleton.getInstance().setSupCubierta(id);
		    }			
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );
		
		ArrayAdapter<CharSequence> aFechaPublicacion = ArrayAdapter.createFromResource(this,
				R.array.fecha_publicacion_array, android.R.layout.simple_spinner_item);
		Spinner sFechaPublicacion = (Spinner)findViewById(R.id.sFechaPublicacion);		 
		aFechaPublicacion.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sFechaPublicacion.setAdapter(aFechaPublicacion);
		sFechaPublicacion.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("Spinner", "Fecha Publicacion id: "+ id);
		       FiltroSingleton.getInstance().setFechaPublicacion(id);
		    }			
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );
		
		ArrayAdapter<CharSequence> aOrden = ArrayAdapter.createFromResource(this,
				R.array.orden_array, android.R.layout.simple_spinner_item);
		Spinner sOrden = (Spinner)findViewById(R.id.sOrden);		 
		aOrden.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sOrden.setAdapter(aOrden);
		sOrden.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("Spinner", "Orden id: "+ id);
		       FiltroSingleton.getInstance().setOrden(id);
		    }			
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );		
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filtros, menu);
		return true;
	}*/

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
	
    public void buscarPropiedades(View view) {    	
    	Intent intent = new Intent(this, ListaPropiedadesActivity.class); 
    	startActivity(intent);
    }		
}
