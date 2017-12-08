package com.example.nart1.geoalumno_v2.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.example.nart1.geoalumno_v2.FireBase.Adapter_Profes;
import com.example.nart1.geoalumno_v2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.nart1.geoalumno_v2.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String INTENT_FILTER = "firebasedemoappfiuv";
    public static final String KEY_LAT = "latitud";
    public static final String KEY_LONG = "longitud";

    Marker currentMarker = null,marker = null;
    double lat,longi;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, new IntentFilter(MapsActivity.INTENT_FILTER));
    }


    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
    }

    public BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            /*String lat = String.valueOf(bundle.getDouble (KEY_LAT));
            String longi = String.valueOf(bundle.getDouble (KEY_LONG));

            String message = String.format (Locale.US, "LAT: %s, LONG: %s", lat, longi);
            Toast.makeText (getBaseContext (), message, Toast.LENGTH_LONG).show ();*/

            double lat = bundle.getDouble(KEY_LAT);
            double longi = bundle.getDouble(KEY_LONG);

            //marker = new LatLng(lat,longi);

            if(currentMarker != null){
                currentMarker.remove();
                currentMarker = null;
            }

            if(currentMarker == null){
                currentMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat,longi))
                        .title("The teacher is here ;)"));
            }

        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        float zoom = 17.0f;
        LatLng marker2 = new LatLng(Adapter_Profes.lat, Adapter_Profes.longi);

        if(Adapter_Profes.lat == 0 && Adapter_Profes.longi == 0){
            Toast.makeText (this,"No se encuentra dentro del Rango o No tiene Activado el servicio de Localizacion",
                    Toast.LENGTH_LONG).show();
        }

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker2, zoom));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}