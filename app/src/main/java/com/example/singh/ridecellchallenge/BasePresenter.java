package com.example.singh.ridecellchallenge;

/**
 * Created by singh on 09-Apr-17.
 */

public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
