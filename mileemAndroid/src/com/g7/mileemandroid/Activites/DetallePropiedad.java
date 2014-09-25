package com.g7.mileemandroid.Activites;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.AdapterDetallePropiedad;
import com.g7.mileemandroid.Model.AtributoPropiedad;
import com.g7.mileemandroid.Model.Propiedad;
import com.g7.mileemandroid.Model.PropiedadSingleton;

public class DetallePropiedad extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_propiedad);
		
		Propiedad propiedad = PropiedadSingleton.getPropiedad();
		
		if(propiedad.getCantFotos() > 0){
			ImageView imagenView = (ImageView)findViewById(R.id.imagenDetalle);
			imagenView.setImageBitmap(propiedad.getFotos()[0]);			
		}
		
		ArrayList<AtributoPropiedad> listaAtributos = new ArrayList<AtributoPropiedad>();
		
		listaAtributos.add(new AtributoPropiedad("Superficie", propiedad.getSuperficie() + "m2"));
		listaAtributos.add(new AtributoPropiedad("Ambientes" , Integer.toString(propiedad.getAmbientes())));
		listaAtributos.add(new AtributoPropiedad("Dormitorios", Integer.toString(propiedad.getDormitorios())));
		listaAtributos.add(new AtributoPropiedad("Barrio", propiedad.getBarrio()));
		listaAtributos.add(new AtributoPropiedad("Antiguedad",propiedad.getAntiguedad() + " años"));
		listaAtributos.add(new AtributoPropiedad("Tipo Operación",propiedad.getTipoOperacion()));
		listaAtributos.add(new AtributoPropiedad("Tipo Propiedad",propiedad.getTipoPropiedad()));
		listaAtributos.add(new AtributoPropiedad("Expensas", "$" + propiedad.getExpensas()));
	
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
    	Intent intent = new Intent(this, FotosSlide.class);
    	startActivity(intent);
	}
}
