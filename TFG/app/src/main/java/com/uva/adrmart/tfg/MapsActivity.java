package com.uva.adrmart.tfg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng valladolid = new LatLng(41.646929, -4.716680);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plaza);
        final MarkerOptions marker = new MarkerOptions()
                .position(valladolid)
                .title("Marker in Valladolid")
                .snippet("Subtitulo")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(marker);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            private View myContestsView;

            public View getInfoWindow(Marker arg0) {
                return null;
            }

            public View getInfoContents(Marker arg0) {
                myContestsView = getLayoutInflater().inflate(R.layout.custom_infowindow, null);
                ImageView v1 = (ImageView) myContestsView.findViewById(R.id.image1);
                ImageView v2 = (ImageView) myContestsView.findViewById(R.id.image2);
                ImageView v3 = (ImageView) myContestsView.findViewById(R.id.image3);

                TextView titulo = (TextView) myContestsView.findViewById(R.id.titulo);
                Log.d("MIERDA", marker.getTitle());
                titulo.setText(marker.getTitle());
                TextView subtitulo = (TextView) myContestsView.findViewById(R.id.subtitulo);
                Log.d("PUTA MIERDA", marker.getSnippet());
                subtitulo.setText(marker.getSnippet());
                Log.d("PUTA MIERDA", (String)titulo.getText());
/*
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plaza);
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                float scaleWidth = ((float) 400) / width;
                float scaleHeight = ((float) 400) / height;
                // create a matrix for the manipulation
                Matrix matrix = new Matrix();
                // resize the bit map
                matrix.postScale(scaleWidth, scaleHeight);
                // recreate the new Bitmap
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
                v1.setImageBitmap(bitmap);
                v2.setImageBitmap(bitmap);
                v3.setImageBitmap(bitmap);*/

                v1.setImageResource(R.drawable.plaza);
                v2.setImageResource(R.drawable.plaza);
                v3.setImageResource(R.drawable.plaza);
                return myContestsView;
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(valladolid, 15));
    }
}
