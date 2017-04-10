package com.example.singh.ridecellchallenge.activities.viewmap;

import android.os.Handler;

import com.example.singh.ridecellchallenge.BasePresenter;
import com.example.singh.ridecellchallenge.BaseView;
import com.example.singh.ridecellchallenge.model.Coordinates;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by singh on 09-Apr-17.
 */

public class ViewMapContract {

    interface View extends BaseView {
        void addStep(LatLng latlng);
        void addRoute();
    }

    interface Presenter extends BasePresenter<View> {
        void getDirections(Coordinates origin, Coordinates destination, double speed);
        void startAnimation(List<LatLng> latLngList, Handler handler, String speed);
    }
}
