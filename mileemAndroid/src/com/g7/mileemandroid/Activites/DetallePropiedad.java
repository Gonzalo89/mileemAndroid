package com.g7.mileemandroid.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.Propiedad;

public class DetallePropiedad extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_propiedad);
		
		Propiedad propiedad = (Propiedad)getIntent().getExtras().getSerializable("Propiedad");
		
		if(propiedad.getCantFotos() > 0){
			ImageView imagenView = (ImageView)findViewById(R.id.imagenDetalle);
			imagenView.setImageBitmap(propiedad.getFotos()[0]);			
		}

		
		TextView superficieView = (TextView)findViewById(R.id.superficieDetalle);
		superficieView.setText("Superficie: " + propiedad.getSuperficie() + "m2");
		
		TextView precioView = (TextView)findViewById(R.id.precioDetalle);
		precioView.setText("Precio: " + propiedad.getPrecio());
		
		TextView ambientesView = (TextView)findViewById(R.id.ambientesDetalle);
		ambientesView.setText("Ambientes: " + propiedad.getAmbientes());
		
		TextView DireccionView = (TextView)findViewById(R.id.direccionDetalle);
		DireccionView.setText("Direccion: " + propiedad.getDireccion());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_propiedad, menu);
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
	
	public void onClickVerFotos(View view) {
    	Intent intent = new Intent(this, FotosSlide.class);
    	startActivity(intent);
	}
}
