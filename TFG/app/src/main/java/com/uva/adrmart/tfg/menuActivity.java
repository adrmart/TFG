package com.uva.adrmart.tfg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class menuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
