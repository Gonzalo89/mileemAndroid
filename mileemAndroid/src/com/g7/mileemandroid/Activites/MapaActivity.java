package com.g7.mileemandroid.Activites;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.R.id;
import com.g7.mileemandroid.R.layout;
import com.g7.mileemandroid.R.menu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MapaActivity extends ActionBarActivity {

	static final LatLng staticLocation = new LatLng(53.551, 9.993);
	private GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		
		// Initialize Map
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		    
	if (map!=null){
		Marker testMarker = map.addMarker(new MarkerOptions()
		          .position(staticLocation)
		          .title("Posiscion Hardcodeada")
		          .snippet("Kiel is cool - descripcion")
		          .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
	}
	
    // Move the camera instantly to staticLocation with a zoom of 15.
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(staticLocation, 15));

    // Zoom in, animating the camera.
    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		    
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
