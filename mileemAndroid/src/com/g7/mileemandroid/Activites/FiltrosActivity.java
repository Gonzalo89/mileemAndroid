package com.g7.mileemandroid.Activites;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.g7.mileemandroid.R;

public class FiltrosActivity extends ActionBarActivity {
	private Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtros);
		List<String> miArray = new ArrayList<String>();
		miArray.add("Elemento1");
		miArray.add("Elemento2");
		miArray.add("Elemento3");
		ArrayAdapter<String> adapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item, miArray);
		
		spinner = (Spinner)findViewById(R.id.sBarrio);
		 
		adapter.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);
		 
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filtros, menu);
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
