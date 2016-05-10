package com.uva.adrmart.tfg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uva.adrmart.tfg.R;
import com.uva.adrmart.tfg.domain.Calle;
import com.uva.adrmart.tfg.persistence.StreetDao;
import com.uva.adrmart.tfg.persistence.StreetDaoImpl;

import java.util.List;

/**
 * Created by Adrian on 10/05/2016.
 */
public class CalleAdapter extends BaseAdapter {

    private Context context;

    private StreetDao streetDao;

    private List<Calle> listaCalles;

    public CalleAdapter(Context context){
        this.context = context;
        streetDao = new StreetDaoImpl();
        streetDao.findStreets();
        listaCalles = streetDao.getListaCalles();
    }

    @Override
    public int getCount() {
        return listaCalles.size();
    }

    @Override
    public Calle getItem(int position) {
        return listaCalles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
        }

        ImageView imagen = (ImageView) convertView.findViewById(R.id.image);
        TextView titulo = (TextView) convertView.findViewById(R.id.name);
        Calle item = getItem(position);
        Log.d("ImageAdapter", String.valueOf(position) + "  "+ R.drawable.colon1);
        imagen.setImageResource(R.drawable.colon1);
        Glide.with(imagen.getContext())
                .load(R.drawable.colon1)
                .into(imagen);
        titulo.setText(item.getNombre());

        return convertView;
    }
}
