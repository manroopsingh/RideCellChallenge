package com.example.singh.ridecellchallenge.activities.viewmap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.singh.ridecellchallenge.R;
import com.example.singh.ridecellchallenge.dependencyInjection.viewmap.DaggerViewMapComponent;
import com.example.singh.ridecellchallenge.model.Coordinates;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import javax.inject.Inject;

public class ViewMapActivity extends AppCompatActivity implements OnMapReadyCallback, ViewMapContract.View {

    private GoogleMap mMap;
    private Coordinates origin, destination;
    private Double speed;
    private PolylineOptions polylineOptions;
    private CircleOptions circleOptions;
    private Circle circle;
    private Handler handler;

    @Inject
    ViewMapPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //build object graph
        DaggerViewMapComponent.create().inject(this);

        presenter.addView(this);

        getCoordinates(getIntent());
        circleOptions = new CircleOptions();
        polylineOptions = new PolylineOptions();

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LatLng position = msg.getData().getParcelable("COORDINATE");
                updateLocation(position);
            }
        };

    }

    private void getCoordinates(Intent intent) {
        origin = (Coordinates) intent.getSerializableExtra("origin");
        destination = (Coordinates) intent.getSerializableExtra("destination");
        speed =  intent.getDoubleExtra("speed", 1.0);

    }

    public void updateLocation(LatLng latLng) {
        if(circle != null){
            circle.remove();
        }
        circleOptions.center(latLng);
        circleOptions.fillColor(Color.GRAY);
        circleOptions.radius(30);
        circleOptions.strokeColor(Color.BLACK);
        circle = mMap.addCircle(circleOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLngOrigin = new LatLng(origin.getLatitude(), origin.getLongitude());
        LatLng latLngDestination = new LatLng(destination.getLatitude(), destination.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLngOrigin).title("Origin"));
        mMap.addMarker(new MarkerOptions().position(latLngDestination).title("Destination"));
        presenter.getDirections(origin, destination, speed);

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void addStep(LatLng latlng) {
        polylineOptions.add(latlng);
    }

    @Override
    public void addRoute() {
        polylineOptions.color(Color.GREEN);
        polylineOptions.width(15);
        mMap.addPolyline(polylineOptions);
        presenter.startAnimation(polylineOptions.getPoints(), handler, String.valueOf(speed));
    }
}
