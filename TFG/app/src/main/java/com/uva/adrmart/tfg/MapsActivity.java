package com.uva.adrmart.tfg;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uva.adrmart.tfg.domain.MarkerPropio;
import com.uva.adrmart.tfg.persistence.MarkerDao;
import com.uva.adrmart.tfg.persistence.MarkerDaoImpl;

import java.util.Iterator;
import java.util.List;

public class MapsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleMap.OnInfoWindowClickListener, LocationListener, GpsStatus.Listener {

    private static final String TAG = MapsActivity.class.getName();

    private GoogleMap mMap;
    private final int checkInterval = 2;
    private final int minDistance = 2;
    private static final int TWO_MINUTES = 1000 * 60 * 2;

    // Variables de localizacion
    private LocationManager locationManager;
    private Location bestLocation; //Mejor localizacion del dispositivo
    public double lat; //Latitud del usuario
    public double lon; //Longitud del usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inicio de la base de datos
        BDHelper.init(this);

        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Inicio del mapa
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        startMap();
    }
    /**
    * Método que inicializa el mapa con marcadores y posicion del dispositivo
    */
    private void startMap() {
        Log.d(TAG, "startMap");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Comprobacion de permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (!provider.contains("gps")) {
                AlertNoGps();
            }
            //Inicializacion de los providers
            for (String s : locationManager.getAllProviders()) {
                locationManager.requestLocationUpdates(s, checkInterval,
                        minDistance, this);
            }
            mMap.setMyLocationEnabled(true);
            Log.d(TAG, "Posicion actual -> LAT: "+ lat + " LON: " + lon);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15));

            addMarkers();
        } else {
            // Show rationale and request permission.
        }
        Log.d(TAG, "Map lsito");

    }

    @Override
    public void onStatusChanged(String provider,
                                int status, Bundle extras) {
        Log.d(TAG, "Cambia estado: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "Se habilita: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "Se deshabilita: " + provider);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Nueva localización: " + location);
        lat = location.getLatitude();
        lon = location.getLongitude();
        actualizaMejorLocaliz(location);
    }

    private void actualizaMejorLocaliz(Location localiz) {
        if (localiz != null && (bestLocation == null
                || localiz.getAccuracy() < 2 * bestLocation.getAccuracy()
                || localiz.getTime() - bestLocation.getTime() > TWO_MINUTES)) {
            Log.d(TAG, "Nueva mejor localización");
            bestLocation = localiz;
            lat = localiz.getLatitude();
            lon = localiz.getLongitude();
        }
    }

    /**
     * Método que solicita al usuario que active la localizacion GPS
     */
    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Método que agrega los marcadores de la base de datos al mapa
     */

    private void addMarkers() {
        MarkerDao markerDao = new MarkerDaoImpl();
        markerDao.getMarkers();
        List<MarkerPropio> listaMarkerPropios = markerDao.getListaMarkers();
        Iterator i = listaMarkerPropios.iterator();
        while (i.hasNext()) {
            MarkerPropio m = (MarkerPropio) i.next();
            LatLng posicion = new LatLng(m.getLatitud(), m.getLogitud());
            final MarkerOptions marker = new MarkerOptions()
                    .position(posicion)
                    .title(m.getTitulo())
                    .snippet(m.getDescripcion());
            mMap.addMarker(marker);
           /* mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

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
                    Log.d("PUTA MIERDA", (String) titulo.getText());

                    v1.setImageResource(R.drawable.plaza);
                    v2.setImageResource(R.drawable.plaza);
                    v3.setImageResource(R.drawable.plaza);
                    return myContestsView;
                }
            });*/
            mMap.setOnInfoWindowClickListener(this);
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
        Fragment frag;
        if (id == R.id.nav_map) {
            startMap();

        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Show some text on the screen.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onGpsStatusChanged(int event) {

    }
}
