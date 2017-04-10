package com.example.singh.ridecellchallenge.dependencyInjection.viewmap;

import com.example.singh.ridecellchallenge.activities.viewmap.ViewMapActivity;

import dagger.Component;

/**
 * Created by singh on 09-Apr-17.
 */


@Component(modules = ViewMapModule.class)
public interface ViewMapComponent {
    void inject(ViewMapActivity viewMapActivity);
}
