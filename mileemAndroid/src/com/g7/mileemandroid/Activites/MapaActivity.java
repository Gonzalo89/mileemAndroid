package com.g7.mileemandroid.Activites;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.g7.mileemandroid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends ActionBarActivity {

	private GoogleMap map;
	private String latitud;
	private String longitud;
	private String direccion;
	private String descripcion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
        setTitle("  Localización");
        
		// Load Parameters
		latitud = getIntent().getStringExtra("Latitud");
		longitud = getIntent().getStringExtra("Longitud");
		direccion = getIntent().getStringExtra("Direccion");
		descripcion = getIntent().getStringExtra("Descripcion");
		
		// Initialize Map and position
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		LatLng position = new LatLng( Double.parseDouble(latitud), Double.parseDouble(longitud) );
		    
		// Add Map Marker
		if (map!=null){
			map.addMarker(new MarkerOptions()
			   .position(position)
			   .title(direccion)
			   .snippet(descripcion));
		}
	
	    // Move the camera instantly to position with a zoom of 15.
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
	
	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa, menu);
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
}
