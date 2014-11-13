package com.g7.mileemandroid.Activites;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.google.android.gms.drive.internal.ap;

public class DetallePropiedad extends Fragment {
	
	private Propiedad propiedad;
	
	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {	 
	        View rootView = inflater.inflate(R.layout.activity_detalle_propiedad, container, false);
	        return rootView;
		}
	       
	   @Override
	   public void onActivityCreated(Bundle bundle){		
		super.onActivityCreated(bundle); 
		
		this.propiedad = PropiedadSingleton.getPropiedad();

		// Imagen principal
		ImageView imagenView = (ImageView)getView().findViewById(R.id.imagenDetalle);
		if(propiedad.getCantFotos() > 0 && propiedad.getFotosCompleta()[0] != null)
			imagenView.setImageBitmap(propiedad.getFotosCompleta()[0]);			
		else
			imagenView.setImageResource(R.drawable.placeholder);
		
		String moneda = null;
		if(propiedad.getMoneda().compareTo("Pesos") == 0) 
			moneda = getResources().getString(R.string.SignoPesos);
		else
			moneda = getResources().getString(R.string.SignoDolares);
		
		TextView dir = (TextView)getView().findViewById(R.id.direccionDetalle);
        dir.setText(propiedad.getDireccion() + " " + propiedad.getNumero()); 
        TextView precio = (TextView)getView().findViewById(R.id.precioDetalle);
        precio.setText(moneda + Integer.toString(propiedad.getPrecio())); 
        
        ListView listAmenities = (ListView)getView().findViewById(R.id.listAmenities);
        AdapterAmenities aAmenities = new AdapterAmenities(getActivity(), propiedad.getAmenities()); 
        listAmenities.setAdapter(aAmenities);
        setListViewHeightBasedOnChildren(listAmenities);
        
        TextView descripcion = (TextView)getView().findViewById(R.id.descripcionDetalle);
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
			
		ListView listView = (ListView) getView().findViewById(R.id.listViewDetalle);
		AdapterDetallePropiedad adapter = new AdapterDetallePropiedad(getActivity(), listaAtributos);
		listView.setAdapter(adapter);
		
		setListViewHeightBasedOnChildren(listView);		

		// Phone Call Listener
		EndCallListener callListener = new EndCallListener();
		TelephonyManager phoneManager = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		phoneManager.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);		
		
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_propiedad, menu);
		return true;
	}*/

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
	    	Intent intent = new Intent(getActivity(), FotosSlide.class);
	    	startActivity(intent);			
		}else {
			Toast.makeText(getActivity(), getResources().getString(R.string.NoHayFotosCargadas), Toast.LENGTH_SHORT).show();
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
			Intent intent = new Intent(getActivity(), MapaActivity.class);
			intent.putExtra("Latitud", propiedad.getLatitud());
			intent.putExtra("Longitud", propiedad.getLongitud());
			intent.putExtra("Direccion", propiedad.getDireccion());
			intent.putExtra("Descripcion", propiedad.getDescripcion());
			startActivity(intent);		
		}else {
			Toast.makeText(getActivity(), getResources().getString(R.string.NoHayLocalizacionCargada), Toast.LENGTH_SHORT).show();
		}		
	}
	
	public void onClickContactar(View view) {
		Intent intent = new Intent(getActivity(), EmailFormActivity.class);
		intent.putExtra("KEY_ANUNCIANTE", propiedad.getNombre() + " " + propiedad.getApellido());
		intent.putExtra("KEY_EMAIL", propiedad.getEmail());
		startActivity(intent);

	}
	
	public void onClickPhoneCall(View view) {
//		  if (propiedad.getTelefono() == null) {
//		         Toast.makeText(this, "Tel�fono no disponible", Toast.LENGTH_SHORT).show();
//		         return;
//		  }
//		  
//		String urlPhone = "tel:" + propiedad.getTelefono();
//		Intent intent = new Intent( Intent.ACTION_CALL );
//		intent.setData( Uri.parse(urlPhone) );
//		this.startActivity(intent);
	}
	
	public void onClickShare (View view) {

		// Contenido
		String content = "Mileem - Que te parece esta propiedad? Dirección: " + propiedad.getDireccion()+" "+propiedad.getNumero() + " Ambientes: " 
		+ propiedad.getAmbientes() + " Precio: " + propiedad.getPrecio() + " Superficie: " + propiedad.getSuperficie();
		String subject = "Propiedad en Mileem";
		
		// Control de apps instaladas
		String appsNoInstaladas = "";
		int count = 0;
		try{
		    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.facebook.katana", 0 );
		} catch( PackageManager.NameNotFoundException e ){
		    appsNoInstaladas += "Facebook";
		    count++;
		}
		try{
		    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.twitter.android", 0 );
		} catch( PackageManager.NameNotFoundException e ){
			if (count>0)
				appsNoInstaladas += ", ";
		    appsNoInstaladas += "Twitter";
		    count++;
		}
		try{
		    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.plus", 0 );
		} catch( PackageManager.NameNotFoundException e ){
			if (count>0)
				appsNoInstaladas += ", ";
		    appsNoInstaladas += "Google+";
		    count++;
		}
		
		if (count > 0) {
			
			String textoToast = (count>1?"Las aplicaciones " : "La aplicacion ") + appsNoInstaladas + ", "+ (count>1? "no se encuentran disponibles" : "no se encuentra disponible") +" para compartir.";
			Toast.makeText(getActivity(), textoToast, Toast.LENGTH_SHORT).show();
		}
		
	    Resources resources = getResources();

	    Intent emailIntent = new Intent();
	    emailIntent.setAction(Intent.ACTION_SEND);
	    // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
	    emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(content));
	    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
	    emailIntent.setType("message/rfc822");

	    PackageManager pm = getActivity().getPackageManager();
	    Intent sendIntent = new Intent(Intent.ACTION_SEND);     
	    sendIntent.setType("text/plain");


	    Intent openInChooser = Intent.createChooser(emailIntent, "Compartir");

	    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
	    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();        
	    for (int i = 0; i < resInfo.size(); i++) {
	        // Extract the label, append it, and repackage it in a LabeledIntent
	        ResolveInfo ri = resInfo.get(i);
	        String packageName = ri.activityInfo.packageName;
	        if(packageName.contains("android.email")) {
	            emailIntent.setPackage(packageName);
	        } else if(packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("com.google.android.apps.plus") || packageName.contains("android.gm")) {
	            Intent intent = new Intent();
	            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
	            intent.setAction(Intent.ACTION_SEND);
	            intent.setType("text/plain");
	            if(packageName.contains("twitter")) {
	                intent.putExtra(Intent.EXTRA_TEXT, content);
	            } else if(packageName.contains("facebook")) {
	                intent.putExtra(Intent.EXTRA_TEXT, content);
	            } else if(packageName.contains("com.google.android.apps.plus")) {
	                intent.putExtra(Intent.EXTRA_TEXT, content);
	            } else if(packageName.contains("android.gm")) {
	                intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(content));
	                intent.putExtra(Intent.EXTRA_SUBJECT, subject);               
	                intent.setType("message/rfc822");
	            }

	            intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
	        }
	    }

	    // convert intentList to array
	    LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

	    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
	    startActivity(openInChooser);       

		
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
	
	

