package com.g7.mileemandroid;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ListaPropiedadesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_propiedades);

		ListView listaPropiedades= (ListView) findViewById(R.id.listaPropiedades);
		ArrayList<Propiedad> arrayProp = new ArrayList<Propiedad>();
		Propiedad propiedad;

		// Introduzco los datos
		propiedad = new Propiedad("Av. Rivadavia","Una descripcion", 5,
				"Venta", 100000, 150, 5,
				3, 300, "Belgrano", "Casa", getResources().getDrawable(R.drawable.casa1), 1);
		arrayProp.add(propiedad);	
		
		propiedad = new Propiedad("Cosquin","Una descripcion", 5,
				"Venta", 100000, 150, 5,
				3, 300, "Belgrano", "Casa", getResources().getDrawable(R.drawable.casa2), 2);
		arrayProp.add(propiedad);	

		propiedad = new Propiedad("J. L. Suarez","Una descripcion", 5,
				"Venta", 100000, 150, 5,
				3, 300, "Belgrano", "Casa", getResources().getDrawable(R.drawable.casa3), 3);
		arrayProp.add(propiedad);	
		
		// Creo el adapter personalizado
		AdapterPropiedad adapter = new AdapterPropiedad(this, arrayProp);

		// Lo aplico
		listaPropiedades.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_propiedades, menu);
		return true;
	}

}
