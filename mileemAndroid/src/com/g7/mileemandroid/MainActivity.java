package com.g7.mileemandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.g7.mileemandroid.Activites.ListaPropiedadesActivity;
import com.g7.mileemandroid.Activites.MapaActivity;


public class MainActivity extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("  Mileem");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void buscarPropiedades(View view) {    	
    	Intent intent = new Intent(this, ListaPropiedadesActivity.class);    
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
