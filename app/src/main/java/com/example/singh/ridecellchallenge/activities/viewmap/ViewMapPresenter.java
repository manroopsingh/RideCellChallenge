package com.example.singh.ridecellchallenge.activities.viewmap;

import android.os.Handler;

import com.example.singh.ridecellchallenge.model.Coordinates;
import com.example.singh.ridecellchallenge.model.directions.Directions;
import com.example.singh.ridecellchallenge.model.directions.Leg;
import com.example.singh.ridecellchallenge.model.directions.Route;
import com.example.singh.ridecellchallenge.model.directions.Step;
import com.example.singh.ridecellchallenge.utility.MapAnimation;
import com.example.singh.ridecellchallenge.utility.RetrofitHelper;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by singh on 09-Apr-17.
 */

public class ViewMapPresenter implements ViewMapContract.Presenter {

    private ViewMapContract.View view;
    private MapAnimation mapAnimation;

    String GoogleApiKey = "AIzaSyBFOLLi_eSEa9v_8Q5aZiIQMHiKra5flPM";

    @Override
    public void addView(ViewMapContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void getDirections(Coordinates origin, Coordinates destination, double speed) {

        String originString = origin.getLatitude() + "," + origin.getLongitude();
        String destinationString = destination.getLatitude() + "," + destination.getLongitude();

        Observable<Directions> directionsObservable = RetrofitHelper.createDirectionstObs(originString, destinationString, GoogleApiKey);
        directionsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Directions>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Directions directions) {
                        addPolylineOptions(directions);
                    }
                });

    }

    @Override
    public void startAnimation(List<LatLng> latLngList, Handler handler, String speed) {
        mapAnimation = new MapAnimation(latLngList, handler, speed);
        mapAnimation.start();
    }

    public void addPolylineOptions(Directions directions) {
        List<Route> routes = directions.getRoutes();
        if (routes.size() > 0) {
            List<Leg> legs = routes.get(0).getLegs();
            if (legs.size() > 0) {
                List<Step> steps = legs.get(0).getSteps();
                for (Step step : steps) {
                    List<LatLng> latLngs = PolyUtil.decode(step.getPolyline().getPoints());
                    for (LatLng latLng : latLngs)
                        view.addStep(latLng);
                }
            }
            view.addRoute();
        }
    }
}
