package com.uva.adrmart.tfg.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.uva.adrmart.tfg.DetalleActivity;
import com.uva.adrmart.tfg.R;
import com.uva.adrmart.tfg.adapter.CalleAdapter;
import com.uva.adrmart.tfg.adapter.ImageAdapter;
import com.uva.adrmart.tfg.callback.FragmentCallback;
import com.uva.adrmart.tfg.domain.Calle;
import com.uva.adrmart.tfg.domain.Imagen;
import com.uva.adrmart.tfg.presenter.GalleryPresenter;
import com.uva.adrmart.tfg.view.GalleryView;

import java.util.List;

/**
 * Created by Adrian on 07/03/2016.
 */
public class GalleryFragment extends Fragment implements GalleryView, AdapterView.OnItemClickListener {

    private static final String TAG = GalleryFragment.class.getName();

    public static int mSelected = 4;
    private GridView gridview;
    private FragmentCallback callback;
    protected GalleryPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Galleria");
        presenter = new GalleryPresenter(this);
        Log.d(TAG, "onCreate GalleryFragment");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        gridview = (GridView) view.findViewById(R.id.gridView);
        CalleAdapter calleAdapter = new CalleAdapter((getContext()));
        gridview.setAdapter(calleAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "click");
                Calle c = (Calle) gridview.getItemAtPosition(position);
                setImageAdapter(c.getId());
            }
        });

        return view;
    }

    private void setImageAdapter(int id) {
        ImageAdapter imageAdapter = new ImageAdapter(getContext());
        imageAdapter.setImagesFromStreet(id);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Imagen item = (Imagen) parent.getItemAtPosition(position);
                Imagen i = (Imagen) gridview.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetalleActivity.class);
                intent.putExtra(DetalleActivity.EXTRA_PARAM_ID, item.getId());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    ActivityOptionsCompat activityOptions =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    getActivity(),
                                    new Pair<>(view.findViewById(R.id.image),
                                            DetalleActivity.VIEW_NAME_HEADER_IMAGE)
                            );

                    ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle());
                } else
                    startActivity(intent);
            }
        });
    }

    @Override
    public void setImages(List<Imagen> items) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

