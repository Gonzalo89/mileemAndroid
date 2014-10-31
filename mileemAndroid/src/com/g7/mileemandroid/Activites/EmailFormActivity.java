package com.g7.mileemandroid.Activites;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import com.g7.mileemandroid.BuildConfig;
import com.g7.mileemandroid.R;
import com.g7.mileemandroid.R.id;
import com.g7.mileemandroid.R.layout;
import com.g7.mileemandroid.R.menu;
import com.g7.mileemandroid.Model.GMailSender;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
	public String emailAnunciante;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_form);
		setTitle("  Contactar Anunciante");	
		
		// Cargar anunciante
		anunciante = getIntent().getExtras().getString("KEY_ANUNCIANTE");
		emailAnunciante = getIntent().getExtras().getString("KEY_EMAIL");	
		
		// Load Views
		_anuncianteTextView = (TextView)  findViewById(R.id.anuncianteTextView);
		_emailEditText = (EditText) findViewById(R.id.emailEditText);
		_telefonoEditText = (EditText) findViewById(R.id.telefonoEditText);
		_mensajeEditText = (EditText) findViewById(R.id.mensajeEditText);
		_horarioEditText = (EditText) findViewById(R.id.horarioEditText);

		if (anunciante != null)
			_anuncianteTextView.setText(anunciante);
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
		
		if ( email.matches("") || message.matches("") || telefono.matches("") )
		{
			Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
		    return;
		}
		else if ( !this.isValidEmail( _emailEditText.getText().toString() ) )
		{
			Toast.makeText(this, "Por favor, ingrese un email válido", Toast.LENGTH_SHORT).show();
		    return;
		}
		else
		{
			message += "\n\n Email:" + email + "\n Telefono:" + telefono + "\n Horario de Contacto:" + horario;
			
			new SendEmailAsyncTask(emailAnunciante, message).execute();

			 Toast.makeText(this, "Email enviado al anunciante", Toast.LENGTH_SHORT).show();
			 finish();
		}
		
	}
	
	public final boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	        return false;
	    } else {
	        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	    }
	}

	public TextView get_anuncianteTextView() {
		return _anuncianteTextView;
	}

	public void set_anuncianteTextView(TextView _anuncianteTextView) {
		this._anuncianteTextView = _anuncianteTextView;
	}
}

class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
    
	GMailSender sender = new GMailSender("rod.testit@gmail.com", "rodtestit");
    private String email;
    private String message;

    public SendEmailAsyncTask( String pemail, String pmessage ) {
    	email = pemail;
    	message = pmessage;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
    	
        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
        
        try 
        {
        	sender.sendMail("Contacto Mileem", message, "mileem.admin@gmail.com", email);
            return true;
        } catch (AuthenticationFailedException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
