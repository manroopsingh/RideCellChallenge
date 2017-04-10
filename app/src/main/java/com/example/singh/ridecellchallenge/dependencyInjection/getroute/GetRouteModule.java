package com.example.singh.ridecellchallenge.dependencyInjection.getroute;

import com.example.singh.ridecellchallenge.activities.getroute.GetRoutePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by singh on 09-Apr-17.
 */

@Module
public class GetRouteModule {

    @Provides
    public GetRoutePresenter provideGetRoutePresenter(){
        return new GetRoutePresenter();
    }

}
