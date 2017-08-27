package com.example.dai.mvvmexample.view;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.dai.mvvmexample.R;
import com.example.dai.mvvmexample.dagger.DaggerMainActivityComponent;

import com.example.dai.mvvmexample.databinding.ActivityMainBinding;
import com.example.dai.mvvmexample.viewModel.MainActivityViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainActivityViewModel mainActivityViewModel;

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainActivityComponent.builder().build().inject(this);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setMainFragmentViewModel(mainActivityViewModel);
        activityMainBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainActivityViewModel.loadDefault();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityViewModel.onDestroy();
    }
}
