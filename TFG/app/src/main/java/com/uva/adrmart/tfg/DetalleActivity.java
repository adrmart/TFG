package com.uva.adrmart.tfg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.uva.adrmart.tfg.adapter.ImageAdapter;
import com.uva.adrmart.tfg.domain.Imagen;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Created by Adrian on 27/04/2016.
 */
public class DetalleActivity extends AppCompatActivity {

    private static final String TAG = DetalleActivity.class.getName();

    public static final String EXTRA_PARAM_ID = "com.uva.adrmart.tfg.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Imagen itemDetallado;
    private ImageViewTouch imagenExtendida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        ImageAdapter ia = new ImageAdapter(this);
       // itemDetallado = ia.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        imagenExtendida = (ImageViewTouch) findViewById(R.id.imagen_extendida);

        cargarImagenExtendida();
    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(R.drawable.colon3)
                .into(imagenExtendida);
    }
}
