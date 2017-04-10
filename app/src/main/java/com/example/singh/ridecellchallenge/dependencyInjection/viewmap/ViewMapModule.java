package com.example.singh.ridecellchallenge.dependencyInjection.viewmap;

import com.example.singh.ridecellchallenge.activities.viewmap.ViewMapPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by singh on 09-Apr-17.
 */

@Module
public class ViewMapModule {

    @Provides
    public ViewMapPresenter provideViewMapPresenter() {
        return new ViewMapPresenter();
    }
}
