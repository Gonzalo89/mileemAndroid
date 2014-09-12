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
    public View getView(int position, View convertView, ViewGroup parent) {
 
        // Generamos una convertView por motivos de eficiencia
        View v = convertView;
 
        //Asociamos el layout de la lista que hemos creado
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.itemlista, null);
        }
 
        // Creamos un objeto directivo
        Propiedad propiedad = items.get(position);
        //Rellenamos la fotografía
        ImageView foto = (ImageView) v.findViewById(R.id.imagenPropiedad);
        foto.setImageDrawable(propiedad.getFoto());
        //Rellenamos el textos
        TextView nombre = (TextView) v.findViewById(R.id.direccionLista);
        nombre.setText(propiedad.getDireccion());        
        TextView superficie = (TextView) v.findViewById(R.id.superficieLista);
        superficie.setText(Integer.toString(propiedad.getSuperficie())+"m2");
        TextView precio = (TextView) v.findViewById(R.id.precioLista);
        precio.setText("$"+Integer.toString(propiedad.getPrecio()));       
        TextView ambientes = (TextView) v.findViewById(R.id.ambientesLista);
        ambientes.setText("Amb: " +propiedad.getAmbientes());      
        return v;
    }

}
