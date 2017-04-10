package com.example.singh.ridecellchallenge.utility;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Siris on 3/13/2017.
 */

public class MapAnimation extends Thread {

    private List<LatLng> latLngList;
    private Handler handler;
    private String speed;
    private Message message;
    private volatile boolean exit = false;

    public MapAnimation(List<LatLng> latLngList, Handler handler, String speed) {
        this.latLngList = latLngList;
        this.handler = handler;
        this.speed = speed;
    }

    @Override
    public void run() {
        float speed = Float.parseFloat(this.speed);
        for (LatLng latLng : latLngList) {
            if(exit) break;
            message = new Message();
            try {
                Thread.sleep((long) (10000 / speed));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bundle b = new Bundle();
            b.putParcelable("COORDINATE", latLng);
            message.setData(b);
            handler.sendMessage(message);
        }
    }

    public void stopProcess(){
        exit = true;
    }
}
