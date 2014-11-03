package com.g7.mileemandroid.Model;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import com.g7.mileemandroid.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AdapterPropiedad extends BaseAdapter {

	// Constantes
	private static int TYPE_COUNT = 3 ;
	private static final int ID_TYPE_GRATUITA = 0;
	private static final int ID_TYPE_BASICA = 1;
	private static final int ID_TYPE_PREMIUM = 2;
    private final String BUNDLE_POS = "pos";
    private final String BUNDLE_URL_THUMB = "bundleUrlsThumb";
    private final String BUNDLE_URL_COMPLETE = "bundleUrlsComplete";
	
    // Objetos
    protected Activity activity;
    protected ArrayList<Propiedad> items;	
    private HashMap<Integer, ImageView> imageViews;
	
    
    public AdapterPropiedad(Activity activity, ArrayList<Propiedad> items) {
		this.activity = activity;
		this.items = items;
        this.imageViews = new HashMap<Integer, ImageView>();
	}

    @Override
    public int getCount() {
        return items.size();
    }
 
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
 
    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }
    
    @Override
    public int getItemViewType(int position) {
        Propiedad propiedad = items.get(position);
 /*       String tipo = propiedad.getTipoPublicacion();
        if ( tipo.equals(TYPE_PREMIUM) )
        	return ID_TYPE_PREMIUM;
        else if ( tipo.equals(TYPE_BASICA) )
        	return ID_TYPE_BASICA;
        else
        	return ID_TYPE_GRATUITA;*/
        return propiedad.getTipoPublicacionId()-1;
    }

       @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        int type = getItemViewType(position);        
        View view = convertView;
        
        if (convertView == null) 
        {
        	LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (type) {
                case ID_TYPE_GRATUITA:
                	view = inf.inflate(R.layout.itemlista_free, null);
                    break;
                case ID_TYPE_BASICA:
                	view = inf.inflate(R.layout.itemlista_basica, null);
                    break;
                case ID_TYPE_PREMIUM:
                	view = inf.inflate(R.layout.itemlista_premium, null);
                    break;
            }
        }
    	
        Propiedad propiedad = items.get(position);
        
        // Inicio carga fotografias asincronica
        ImageView propiedadImageView = (ImageView) view.findViewById(R.id.imagenPropiedad);
        imageViews.put(position, propiedadImageView);
        // Carga de datos para la async task
        Bundle bundle = new Bundle();
        bundle.putStringArray(BUNDLE_URL_THUMB, propiedad.getImagesUrlThumb());
        bundle.putStringArray(BUNDLE_URL_COMPLETE, propiedad.getImagesUrlCompleta());
        bundle.putInt(BUNDLE_POS, position);
        // Inicio carga de datos en nuevo thread
        new FotosPropiedadesTask().execute(bundle);
        
 //     foto.setImageDrawable(propiedad.getFoto());
//        if(propiedad.getCantFotos() > 0)
//        	propiedadImageView.setImageBitmap(propiedad.getFotosThumb()[0]);
//        else
//        	propiedadImageView.setImageResource(R.drawable.placeholder);
        
        // Rellenamos el textos
        TextView nombre = (TextView) view.findViewById(R.id.direccionLista);
        nombre.setText(propiedad.getDireccion());        
        TextView superficie = (TextView) view.findViewById(R.id.superficieLista);
        superficie.setText(Integer.toString(propiedad.getSuperficie())+"m2");
        TextView precio = (TextView) view.findViewById(R.id.precioLista);
        
        String moneda = "$";
        if(propiedad.getMoneda() != null) {
            if(propiedad.getMoneda().compareTo("Pesos") == 0)
            	moneda = "$";
            else
            	moneda = "u$s";        	
        }
        precio.setText(moneda +propiedad.getPrecio());       
        
        TextView ambientes = (TextView) view.findViewById(R.id.ambientesLista);
        ambientes.setText("Amb: " +propiedad.getAmbientes());      
        return view;
    }
    
    private class FotosPropiedadesTask extends AsyncTask<Bundle, Void, Bundle> {    
    	
       @Override
       protected Bundle doInBackground(Bundle... bundle) {
    	   
    	   //PROCESAR
           Bundle newBundle = new Bundle();
           return newBundle;
       }
       
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(Bundle result) {
			
		}
	}

}
