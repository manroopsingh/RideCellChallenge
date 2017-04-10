package com.example.singh.ridecellchallenge.activities.getroute;

import com.example.singh.ridecellchallenge.model.Coordinates;

/**
 * Created by singh on 09-Apr-17.
 */

public class GetRoutePresenter implements GetRouteContract.Presenter {

    private GetRouteContract.View view;

    @Override
    public void addView(GetRouteContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {

        this.view = null;
    }

    @Override
    public void validateCoordinates(Coordinates origin, Coordinates destination, double speed) {

        if (!origin.validate() || !destination.validate()){
            view.invalidCoordinates();
            return;
        }

        view.animateMapActivity(origin, destination, speed);

    }
}
