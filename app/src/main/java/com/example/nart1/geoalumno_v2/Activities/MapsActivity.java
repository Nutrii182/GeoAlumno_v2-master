package com.example.nart1.geoalumno_v2.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.nart1.geoalumno_v2.Coordinate;
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

    private GoogleMap mMap;
    private Marker marcador;
    private Coordinate coordinate;
    double lat = 0;
    double longi = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        lat = getIntent().getDoubleExtra("KEY_LATITUD",0);
        longi = getIntent().getDoubleExtra("KEY_LONGITUD",0);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        float zoom = 16.0f;

        LatLng marker = new LatLng(lat,longi);
        //LatLng marker = new LatLng(19.165594, -96.114725);

        mMap.addMarker(new MarkerOptions().position(marker).title("Marker in Sydney"));
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,zoom));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
