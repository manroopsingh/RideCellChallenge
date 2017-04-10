package com.example.singh.ridecellchallenge.utility;

import com.example.singh.ridecellchallenge.model.directions.Directions;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by singh on 09-Apr-17.
 */

public class RetrofitHelper {
    private static final String BASE_URL = "https://maps.googleapis.com";

    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    public static Observable<Directions> createDirectionstObs(String Origin, String Destination, String key) {
        Retrofit retrofit = create();
        RetroFitService service = retrofit.create(RetroFitService.class);
        return service.getDirections(Origin, Destination, key);

    }

    public interface RetroFitService {


        @GET("/maps/api/directions/json")
        Observable<Directions> getDirections(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);
    }

}
