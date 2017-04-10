package com.example.singh.ridecellchallenge.dependencyInjection.getroute;

import com.example.singh.ridecellchallenge.activities.getroute.GetRouteActivity;

import dagger.Component;

/**
 * Created by singh on 09-Apr-17.
 */

@Component(modules = GetRouteModule.class)
public interface GetRouteComponent {
    void inject(GetRouteActivity getRouteActivity);


}
