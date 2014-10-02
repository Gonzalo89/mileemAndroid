package com.g7.mileemandroid.Activites;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.AdapterAmenities;
import com.g7.mileemandroid.Model.AdapterDetallePropiedad;
import com.g7.mileemandroid.Model.AtributoPropiedad;
import com.g7.mileemandroid.Model.Propiedad;
import com.g7.mileemandroid.Model.PropiedadSingleton;

public class DetallePropiedad extends ActionBarActivity {
	private Propiedad propiedad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_propiedad);
		
		this.propiedad = PropiedadSingleton.getPropiedad();
		
		if(propiedad.getCantFotos() > 0){
			ImageView imagenView = (ImageView)findViewById(R.id.imagenDetalle);
			imagenView.setImageBitmap(propiedad.getFotosThumb()[0]);			
		}
		
		String moneda = null;
		if(propiedad.getMoneda().compareTo("Pesos") == 0) 
			moneda = "$";
		else
			moneda = "u$s";
		
		TextView dir = (TextView)findViewById(R.id.direccionDetalle);
        dir.setText(propiedad.getDireccion() + " " + propiedad.getNumero()); 
        TextView precio = (TextView)findViewById(R.id.precioDetalle);
        precio.setText(moneda + Integer.toString(propiedad.getPrecio())); 
        
        ListView listAmenities = (ListView)findViewById(R.id.listAmenities);
        AdapterAmenities aAmenities = new AdapterAmenities(this, propiedad.getAmenities()); 
        listAmenities.setAdapter(aAmenities);
        
        TextView descripcion = (TextView)findViewById(R.id.descripcionDetalle);
        descripcion.setText(propiedad.getDescripcion());        
		
		ArrayList<AtributoPropiedad> listaAtributos = new ArrayList<AtributoPropiedad>();

		listaAtributos.add(new AtributoPropiedad("Barrio", propiedad.getBarrio()));
		listaAtributos.add(new AtributoPropiedad("Superficie", propiedad.getSuperficie() + "m2"));
		listaAtributos.add(new AtributoPropiedad("Sup. no Cubierta",  propiedad.getSupNCubierta() + "m2"));
		listaAtributos.add(new AtributoPropiedad("Ambientes" , Integer.toString(propiedad.getAmbientes())));
		listaAtributos.add(new AtributoPropiedad("Dormitorios", Integer.toString(propiedad.getDormitorios())));		
		listaAtributos.add(new AtributoPropiedad("Antiguedad",propiedad.getAntiguedad() + " años"));
		listaAtributos.add(new AtributoPropiedad("Tipo Operación",propiedad.getTipoOperacion()));
		listaAtributos.add(new AtributoPropiedad("Tipo Propiedad",propiedad.getTipoPropiedad()));
		listaAtributos.add(new AtributoPropiedad("Expensas", moneda + propiedad.getExpensas()));
		listaAtributos.add(new AtributoPropiedad("Tipo Propiedad",propiedad.getTipoPropiedad()));
	
		ListView listView = (ListView) findViewById(R.id.listViewDetalle);
		AdapterDetallePropiedad adapter = new AdapterDetallePropiedad(this, listaAtributos);
		listView.setAdapter(adapter);
		
		

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
		if(this.propiedad.getFotosThumb() != null) {
	    	Intent intent = new Intent(this, FotosSlide.class);
	    	startActivity(intent);			
		}else {
			Toast.makeText(this, "No hay fotos cargadas", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onClickVerEnMapa(View view) {
		Log.d("Mapa", "Click en botonMapaDetalle");
/*		if(this.propiedad.getLatitud() != null && this.propiedad.getLatitud() != null) {			
			Intent intent = new Intent(this, MapaActivity.class);
			intent.putExtra("Latitud", propiedad.getLatitud());
			intent.putExtra("Longitud", propiedad.getLongitud());
			intent.putExtra("Direccion", propiedad.getDireccion());
			intent.putExtra("Descripcion", propiedad.getDescripcion());
			startActivity(intent);		
		}else {
			Toast.makeText(this, "No hay latitud/longitud cargada", Toast.LENGTH_SHORT).show();
		}*/		
	}
}
