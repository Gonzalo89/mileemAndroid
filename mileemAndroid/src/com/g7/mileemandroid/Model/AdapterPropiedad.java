package com.g7.mileemandroid.Model;

import java.util.ArrayList;

import com.g7.mileemandroid.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPropiedad extends BaseAdapter {

	private static int TYPE_COUNT = 3;
	private static final int ID_TYPE_GRATUITA = 0;
	private static final int ID_TYPE_BASICA = 1;
	private static final int ID_TYPE_PREMIUM = 2;
	private static final String TYPE_GRATUITA = "Gratuita";
	private static final String TYPE_BASICA = "Básica";
	private static final String TYPE_PREMIUM = "Premium";
    protected Activity activity;
    protected ArrayList<Propiedad> items;	
	
    public AdapterPropiedad(Activity activity, ArrayList<Propiedad> items) {
		this.activity = activity;
		this.items = items;
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
        String tipo = propiedad.getTipoPublicacion();
        if ( tipo.equals(TYPE_PREMIUM) )
        	return ID_TYPE_PREMIUM;
        else if ( tipo.equals(TYPE_BASICA) )
        	return ID_TYPE_BASICA;
        else
        	return ID_TYPE_GRATUITA;
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
    	
//        // Generamos una convertView por motivos de eficiencia
//        View v = convertView;
// 
//        //Asociamos el layout de la lista que hemos creado
//        if(convertView == null){
//            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = inf.inflate(R.layout.itemlista, null);
//        }
 
        // Creamos un objeto directivo
        Propiedad propiedad = items.get(position);
        //Rellenamos la fotograf�a
        ImageView foto = (ImageView) view.findViewById(R.id.imagenPropiedad);
 //     foto.setImageDrawable(propiedad.getFoto());
        if(propiedad.getCantFotos() > 0)
        	foto.setImageBitmap(propiedad.getFotosThumb()[0]);
        else
        	foto.setImageResource(R.drawable.ic_launcher);
        //Rellenamos el textos
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

}
