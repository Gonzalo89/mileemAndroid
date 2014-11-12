package com.g7.mileemandroid;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.g7.mileemandroid.Activites.ListaPropiedadesActivity;
import com.g7.mileemandroid.Model.FiltroSingleton;


public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("  Mileem");
        FiltroSingleton.getInstance().resetAll();
        Button bBuscar = (Button)findViewById(R.id.buscarButton);
        bBuscar.setBackgroundColor(Color.BLUE);
        bBuscar.setTextColor(Color.WHITE);
        
		ArrayAdapter<CharSequence> aBarrio = ArrayAdapter.createFromResource(this,
				R.array.barrios_array, android.R.layout.simple_spinner_item);
		Spinner sBarrio = (Spinner)findViewById(R.id.sPrecio);		 
		aBarrio.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sBarrio.setAdapter(aBarrio);
		sBarrio.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("Barrio", "Barrio id: "+ id);
		       FiltroSingleton.getInstance().setBarrio(id);
		    }
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );
		
		ArrayAdapter<CharSequence> aTProp = ArrayAdapter.createFromResource(this,
				R.array.tPropiedadd_array, android.R.layout.simple_spinner_item);
		Spinner sTPropiedad = (Spinner)findViewById(R.id.sTPropiedad);		 
		aTProp.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sTPropiedad.setAdapter(aTProp);	
		sTPropiedad.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("T. Propiedad", "T. Prop id: "+ id);
		       FiltroSingleton.getInstance().settPropiedad(id);
		    }
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );

		ArrayAdapter<CharSequence> aAmbientes = ArrayAdapter.createFromResource(this,
				R.array.ambientes_array, android.R.layout.simple_spinner_item);
		Spinner sAmbientes = (Spinner)findViewById(R.id.sAmbientes);		 
		aAmbientes.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sAmbientes.setAdapter(aAmbientes);
		sAmbientes.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("Ambientes", "Ambientes id: "+ id);
		       FiltroSingleton.getInstance().setAmbientes(id);
		    }
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );
		
		ArrayAdapter<CharSequence> aTOperaciones = ArrayAdapter.createFromResource(this,
				R.array.tOperaciones_array, android.R.layout.simple_spinner_item);
		Spinner sTOperaciones = (Spinner)findViewById(R.id.sTOperacion);		 
		aTOperaciones.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);		 
		sTOperaciones.setAdapter(aTOperaciones);
		sTOperaciones.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		       Log.d("TOperacion", "TOperacion id: "+ id);
		       FiltroSingleton.getInstance().setOperacion(id);
		    }
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }			
		} );		
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    //	Intent intent = new Intent(this, ListaPropiedadesActivity.class);
    	Intent intent = new Intent(this, Estadistica2.class);
    //	Intent intent = new Intent(this, FiltrosActivity.class);
    //	Intent intent = new Intent(this, PruebaVideo.class);
    	String message = "Mensaje a el Listing";
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    	
    	// Para testear el mapa
//		Intent intent = new Intent(this, MapaActivity.class);
//		intent.putExtra("Latitud", "-34.6");
//		intent.putExtra("Longitud","-58.45");
//		intent.putExtra("Direccion", "Direccion completa!");
//		intent.putExtra("Descripcion", "descripcion :)");
//		startActivity(intent);
    }
}
