package com.example.singh.ridecellchallenge.activities.getroute;

import com.example.singh.ridecellchallenge.BasePresenter;
import com.example.singh.ridecellchallenge.BaseView;
import com.example.singh.ridecellchallenge.model.Coordinates;

/**
 * Created by singh on 09-Apr-17.
 */

public class GetRouteContract {

    interface View extends BaseView {
        void animateMapActivity(Coordinates origin, Coordinates destination, double speed);
        void invalidCoordinates();
    }

    interface Presenter extends BasePresenter<View> {
        void validateCoordinates(Coordinates origin, Coordinates destination, double speed);

    }
}
