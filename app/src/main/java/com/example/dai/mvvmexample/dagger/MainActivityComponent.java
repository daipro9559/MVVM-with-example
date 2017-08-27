package com.example.dai.mvvmexample.dagger;

import com.example.dai.mvvmexample.dagger.modul.MainActivityModul;
import com.example.dai.mvvmexample.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dai on 8/27/2017.
 */
@MainActivityScope
@Component(modules = MainActivityModul.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
