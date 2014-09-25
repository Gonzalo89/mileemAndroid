package com.g7.mileemandroid.Model;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.g7.mileemandroid.R;

public class AdapterDetallePropiedad extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<AtributoPropiedad> items;	
	
    public AdapterDetallePropiedad(Activity activity, ArrayList<AtributoPropiedad> items) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
 
        // Generamos una convertView por motivos de eficiencia
        View v = convertView;
 
        //Asociamos el layout de la lista que hemos creado
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.itemdetallepropiedad, null);
        }
 
        // Creamos un objeto directivo
        AtributoPropiedad atPropiedad = items.get(position);
       //Rellenamos la fotografía
        TextView nombre = (TextView) v.findViewById(R.id.itemDetalleNombre);
        nombre.setText(atPropiedad.getNombre());        
        TextView valor = (TextView) v.findViewById(R.id.itemDetalleValor);
        valor.setText(atPropiedad.getValor());  
        return v;
    }

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}
}
