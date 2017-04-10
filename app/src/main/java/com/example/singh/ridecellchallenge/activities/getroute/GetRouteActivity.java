package com.example.singh.ridecellchallenge.activities.getroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.singh.ridecellchallenge.R;
import com.example.singh.ridecellchallenge.activities.viewmap.ViewMapActivity;
import com.example.singh.ridecellchallenge.dependencyInjection.getroute.DaggerGetRouteComponent;
import com.example.singh.ridecellchallenge.model.Coordinates;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetRouteActivity extends AppCompatActivity implements GetRouteContract.View{

    @BindView(R.id.etOrigin)
    EditText etOrigin;

    @BindView(R.id.etDestination)
    EditText etDestination;

    @BindView(R.id.etSpeed)
    EditText etSpeed;


    @BindView(R.id.btnGetDirections)
    Button btnGetDirections;

    String originLat, originLon, destinationLat, destinationLon, speed;


    @Inject
    GetRoutePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_route);

        ButterKnife.bind(this);

        setTitle("Enter coordinates");

        DaggerGetRouteComponent.create().inject(this);
        presenter.addView(this);


        btnGetDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originLat =  etOrigin.getText().toString().split(",")[0];
                originLon =  etOrigin.getText().toString().split(",")[1];
                destinationLat =  etDestination.getText().toString().split(",")[0];
                destinationLon =  etDestination.getText().toString().split(",")[1];
                Coordinates origin = new Coordinates(originLat, originLon);
                Coordinates destination = new Coordinates(destinationLat, destinationLon);
                speed = etSpeed.getText().toString();
                presenter.validateCoordinates(origin, destination, Double.parseDouble(speed));


            }
        });


    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void animateMapActivity(Coordinates origin, Coordinates destination, double speed) {

        Intent intent = new Intent(GetRouteActivity.this, ViewMapActivity.class);
        intent.putExtra("origin",origin);
        intent.putExtra("destination", destination);
        intent.putExtra("speed", speed);
        startActivity(intent);

    }

    @Override
    public void invalidCoordinates() {
        Toast.makeText(this, "Please enter valid coordinates", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();

    }
}
