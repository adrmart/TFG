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
import com.uva.adrmart.tfg.domain.Imagen;
import com.uva.adrmart.tfg.persistence.ImagenDao;
import com.uva.adrmart.tfg.persistence.ImagenDaoImpl;

import java.util.List;

/**
 * Created by Adrian on 07/03/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ImagenDao imagenDao;

    private List<Imagen> listaImagen;

    public ImageAdapter(Context context){
        this.context = context;
        imagenDao = new ImagenDaoImpl();
    }

    @Override
    public int getCount() {
        return listaImagen.size();
    }

    @Override
    public Imagen getItem(int position) {
        return listaImagen.get(position);
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
        Imagen item = getItem(position);
        Log.d("ImageAdapter", String.valueOf(position) + "  "+ R.drawable.colon1);
        imagen.setImageResource(R.drawable.colon1);
        Glide.with(imagen.getContext())
                .load(R.drawable.colon1)
                .into(imagen);
        titulo.setText(item.getTitulo());

        return convertView;
    }

    public void setImagesFromStreet(int id) {
        imagenDao.findImagenesByStreet(id);
        listaImagen = imagenDao.getListaImagenes();
    }
}
