package com.g7.mileemandroid.Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import com.g7.mileemandroid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private final String BUNDLE_COUNT = "count";
	
    // Objetos
    protected Activity activity;
    protected ListView listView;
    protected ArrayList<Propiedad> items;	
    private HashMap<Integer, ImageView> imageViews;
	
    
    public AdapterPropiedad(Activity activity, ArrayList<Propiedad> items, ListView parentListView) {
		this.activity = activity;
		this.listView = parentListView;
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
    	propiedadImageView.setImageResource(R.drawable.placeholder);
        imageViews.put(position, propiedadImageView);
        
        if (propiedad.getFotosCompleta() == null || propiedad.getFotosThumb() == null) {
        	
	        // Carga de datos para la async task
	        Bundle bundle = new Bundle();
	        bundle.putStringArray(BUNDLE_URL_THUMB, propiedad.getImagesUrlThumb());
	        bundle.putStringArray(BUNDLE_URL_COMPLETE, propiedad.getImagesUrlCompleta());
	        bundle.putInt(BUNDLE_POS, position);
	        bundle.putInt(BUNDLE_COUNT, propiedad.getCantFotos());
	        // Inicio carga de datos en nuevo thread
	        new FotosPropiedadesTask().execute(bundle);
        }else {
        	
        	Bitmap bm = null;
            if ( propiedad.getFotosCompleta().length != 0)
            	bm = propiedad.getFotosCompleta()[0];
            else if ( propiedad.getFotosThumb().length != 0 )
            	bm = propiedad.getFotosThumb()[0];
            
            if (bm != null)
            	propiedadImageView.setImageBitmap(bm);
        }
        
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
    	
    	private final String BUNDLE_THUMB_IMAGES = "bundleThumbImages";
    	private final String BUNDLE_COMPLETE_IMAGES = "bundleCompleteImages";
    	
       @Override
       protected Bundle doInBackground(Bundle... bundle) {
    	   
    	   int position = bundle[0].getInt(BUNDLE_POS);
    	   int count = bundle[0].getInt(BUNDLE_COUNT);
    	   String[] thumbUrls = bundle[0].getStringArray(BUNDLE_URL_THUMB);
    	   String[] completeUrls = bundle[0].getStringArray(BUNDLE_URL_COMPLETE);
    	   Bitmap[] fotosThumb = new Bitmap[count];
    	   Bitmap[] fotosCompleta = new Bitmap[count];
    	   String thumbUrl = "";
    	   String completeUrl = "";
			URL url;		
    	   
    	   for(int i = 0; i < count; i++) {
    		   
    		   thumbUrl = thumbUrls[i];
    		   completeUrl = completeUrls[i];
    		   			
				try {
					//Obtengo fotoThumb						
					url = new URL(thumbUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					InputStream is = connection.getInputStream();
					Bitmap img = BitmapFactory.decodeStream(is);
					fotosThumb[i] = img;	

					//Obtengo fotoCompleta
					url = new URL(completeUrl);
					HttpURLConnection connection2 = (HttpURLConnection) url.openConnection();
					InputStream is2 = connection2.getInputStream();
					Bitmap img2 = BitmapFactory.decodeStream(is2);
					fotosCompleta[i] = img2;	
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
    	   }
    	   
    	   //PROCESAR
           Bundle newBundle = new Bundle();
           newBundle.putInt(BUNDLE_POS, position);
           newBundle.putParcelableArray(BUNDLE_THUMB_IMAGES, fotosThumb);
           newBundle.putParcelableArray(BUNDLE_COMPLETE_IMAGES, fotosCompleta);
           return newBundle;
       }
       
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(Bundle result) {
			
			// Load Data
			int position = result.getInt(BUNDLE_POS);
			Propiedad propiedad = items.get(position);
			Bitmap[] fotosThumb = (Bitmap[]) result.getParcelableArray(BUNDLE_THUMB_IMAGES);
	    	Bitmap[] fotosCompleta = (Bitmap[]) result.getParcelableArray(BUNDLE_COMPLETE_IMAGES);
	    	
	    	// Save Photos in propiedad
	    	propiedad.setFotosThumb(fotosThumb);
	    	propiedad.setFotosCompleta(fotosCompleta);

	    	int first = listView.getFirstVisiblePosition();
	    	int last = listView.getLastVisiblePosition();  
	    	
	    	if ( position <= last && position >= first) 
	    	{
				// Get picture saved in the map + set
	            ImageView view = imageViews.get(position);
	            Bitmap bm = null;
	            if ( fotosCompleta.length != 0){
	            	bm = fotosCompleta[0];
	            }else if ( fotosThumb.length != 0 ){
	            	bm = fotosThumb[0];
	            }
	            	
	            if (bm != null){ //if bitmap exists...
	                view.setImageBitmap(bm);
	            }else{ //if not picture, display the default resource
	                view.setImageResource(R.drawable.placeholder);
	            }
	    	}
		}
	}

}
