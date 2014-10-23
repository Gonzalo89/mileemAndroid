package com.g7.mileemandroid.Activites;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
        setListViewHeightBasedOnChildren(listAmenities);
        
        TextView descripcion = (TextView)findViewById(R.id.descripcionDetalle);
        descripcion.setText(propiedad.getDescripcion());        
		
		ArrayList<AtributoPropiedad> listaAtributos = new ArrayList<AtributoPropiedad>();

		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.Barrio), propiedad.getBarrio()));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.Superficie), propiedad.getSuperficie() + getResources().getString(R.string.m2)));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.SuperficieNoCubierta),  propiedad.getSupNCubierta() + getResources().getString(R.string.m2)));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.Ambientes) , Integer.toString(propiedad.getAmbientes())));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.Dormitorios), Integer.toString(propiedad.getDormitorios())));		
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.Antiguedad),propiedad.getAntiguedad() + " " +getResources().getString(R.string.anios)));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.TipoOperacion),propiedad.getTipoOperacion()));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.TipoPropiedad),propiedad.getTipoPropiedad()));
		listaAtributos.add(new AtributoPropiedad(getResources().getString(R.string.Expensas), getResources().getString(R.string.SignoPesos) + propiedad.getExpensas()));
			
		ListView listView = (ListView) findViewById(R.id.listViewDetalle);
		AdapterDetallePropiedad adapter = new AdapterDetallePropiedad(this, listaAtributos);
		listView.setAdapter(adapter);
		
		setListViewHeightBasedOnChildren(listView);		

		// Phone Call Listener
		EndCallListener callListener = new EndCallListener();
		TelephonyManager phoneManager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		phoneManager.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_propiedad, menu);
		return true;
	}*/

/*	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	
	public void onClickVerFotos(View view) {
		if(this.propiedad.getFotosThumb() != null) {
	    	Intent intent = new Intent(this, FotosSlide.class);
	    	startActivity(intent);			
		}else {
			Toast.makeText(this, getResources().getString(R.string.NoHayFotosCargadas), Toast.LENGTH_SHORT).show();
		}
	}
	
	/**** Method for Setting the Height of the ListView dynamically.
	 **** Hack to fix the issue of not showing all the items of the ListView
	 **** when placed inside a ScrollView  ****/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        view = listAdapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}	
	
	public void onClickVerEnMapa(View view) {
		
		Log.d("Mapa", "Click en botonMapaDetalle");
		if( this.propiedad.getLatitud() != null && this.propiedad.getLongitud() != null ) {			
			Intent intent = new Intent(this, MapaActivity.class);
			intent.putExtra("Latitud", propiedad.getLatitud());
			intent.putExtra("Longitud", propiedad.getLongitud());
			intent.putExtra("Direccion", propiedad.getDireccion());
			intent.putExtra("Descripcion", propiedad.getDescripcion());
			startActivity(intent);		
		}else {
			Toast.makeText(this, getResources().getString(R.string.NoHayLocalizacionCargada), Toast.LENGTH_SHORT).show();
		}		
	}
	
	public void onClickContactar(View view) {
		
		Intent intent = new Intent(this, EmailFormActivity.class);
		intent.putExtra("KEY_ANUNCIANTE", "Nombre Anunciante");
		startActivity(intent);
//
//		  if (propiedad.getEmail() == null) {
//		         Toast.makeText(this, getResources().getString(R.string.EmailNoDisponible), Toast.LENGTH_SHORT).show();
//		         return;
//		  }
//		
//	      Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",propiedad.getEmail(), null));
//	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[Contacto Mileem]");
//
//	      try {
//	         startActivity(Intent.createChooser(emailIntent, "Enviar email"));
//	         Log.i("Email Enviado", "");
//	      } catch (android.content.ActivityNotFoundException ex) {
//	         Toast.makeText(this, getResources().getString(R.string.NoSeEncontraronClientes), Toast.LENGTH_SHORT).show();
//	      }
	}
	
	public void onClickPhoneCall(View view) {
		
		  if (propiedad.getTelefono() == null) {
		         Toast.makeText(this, "Tel�fono no disponible", Toast.LENGTH_SHORT).show();
		         return;
		  }
		  
		String urlPhone = "tel:" + propiedad.getTelefono();
		Intent intent = new Intent( Intent.ACTION_CALL );
		intent.setData( Uri.parse(urlPhone) );
		this.startActivity(intent);
	}
	
	
	private class EndCallListener extends PhoneStateListener {
	    
		@Override
	    public void onCallStateChanged(int state, String incomingNumber) {
			
	        if(TelephonyManager.CALL_STATE_RINGING == state) {
	            Log.i("LOG_TAG", "RINGING, number: " + incomingNumber);
	        }
	        
	        if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
	            //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
	            Log.i("LOG_TAG", "OFFHOOK");
	        }
	        
	        if(TelephonyManager.CALL_STATE_IDLE == state) {
	            //when this state occurs, and your flag is set, restart your app
	            Log.i("LOG_TAG", "IDLE");
	        }
	    }
	}
	
}
	
	

