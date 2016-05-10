package com.uva.adrmart.tfg.presenter;


import android.util.Log;

import com.uva.adrmart.tfg.callback.OnLoadFinishListener;
import com.uva.adrmart.tfg.domain.Imagen;
import com.uva.adrmart.tfg.view.GalleryView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adrian on 07/03/2016.
 */
public class GalleryPresenter implements OnLoadFinishListener {

    private GalleryView view;

    public GalleryPresenter(GalleryView view) {
        this.view = view;
        Log.d("CACA", "GalleryPresenter");

    }

    @Override
    public void onLoadFinish(List<? extends Serializable> items) {
        view.setImages((List<Imagen>) items);
    }
}
