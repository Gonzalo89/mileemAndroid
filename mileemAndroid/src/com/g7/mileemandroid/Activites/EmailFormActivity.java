package com.g7.mileemandroid.Activites;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.R.id;
import com.g7.mileemandroid.R.layout;
import com.g7.mileemandroid.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailFormActivity extends Activity {

	public EditText _emailEditText;
	public EditText _telefonoEditText;
	public EditText _mensajeEditText;
	public EditText _horarioEditText;
	public TextView _anuncianteTextView;
	public String anunciante;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_form);
		setTitle("  Contactar Anunciante");	
		
		// Cargar anunciante
		anunciante = getIntent().getExtras().getString("KEY_ANUNCIANTE");
		
		if (anunciante != null)
			
		// Load Views
		set_anuncianteTextView((TextView) findViewById(R.id.anuncianteTextField));
		_anuncianteTextView.setText(anunciante);
		_emailEditText = (EditText) findViewById(R.id.emailEditText);
		_telefonoEditText = (EditText) findViewById(R.id.telefonoEditText);
		_mensajeEditText = (EditText) findViewById(R.id.mensajeEditText);
		_horarioEditText = (EditText) findViewById(R.id.horarioEditText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_form, menu);
		return true;
	}
	
	public void onSendEmail( View view ) {
		
		String email = _emailEditText.getText().toString();
		String message = _mensajeEditText.getText().toString();
		String telefono = _telefonoEditText.getText().toString();
		String horario = _horarioEditText.getText().toString();
		
		if ( email.matches("") || message.matches("") || telefono.matches("") || horario.matches("") )
		{
			Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
		    return;
		}
		
		email = "mailto:" + email;
		message += "\n\n Telefono:" + telefono + "\n\n Horario de Contacto:" + horario;
		
		Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, message);
		intent.putExtra(Intent.EXTRA_TEXT, "Contacto Mileem");
		intent.setData(Uri.parse(email)); // or just "mailto:" for blank
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
		startActivity(intent);
	}

	public TextView get_anuncianteTextView() {
		return _anuncianteTextView;
	}

	public void set_anuncianteTextView(TextView _anuncianteTextView) {
		this._anuncianteTextView = _anuncianteTextView;
	}
}

