package com.ak.mvvmdemo.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.ak.mvvmdemo.R;
import com.ak.mvvmdemo.BR;
import com.ak.mvvmdemo.databinding.ActivityMainBinding;
import com.ak.mvvmdemo.ui.base.BaseActivity;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>  {

    private ActivityMainBinding mActivityMainBinding;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;


    MainViewModel mainViewModel;

    private ArrayList<Fragment> fragments;


    @Override
    public void initialization(Bundle savedInstance) {

        mActivityMainBinding = getViewDataBinding();
        setSupportActionBar(mActivityMainBinding.toolbar);

    }

    @Override
    public MainViewModel getViewModel() {

        mainViewModel= ViewModelProviders.of(this,mViewModelFactory).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}
