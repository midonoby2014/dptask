package com.noby.dptask.gui.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.noby.dptask.databinding.ActivitySplashBinding;
import com.noby.dptask.R;
import com.noby.dptask.baseClasses.BaseActivity;
import com.noby.dptask.baseClasses.BaseModels;
import com.noby.dptask.baseClasses.BaseView;
import com.noby.dptask.gui.listemployee.ListEmployesActivity;
import com.noby.dptask.utils.IntentUtiles;

public class SplashActivity extends BaseActivity implements   SplashView {

    SplashViewModel   splashViewModel ;
    Animation animFadein;
    ActivitySplashBinding binding ;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);
        initializeObjects();
        inilizeVaribles();
    }
    private void inilizeVaribles() {
        splashViewModel = new SplashViewModel();
        splashViewModel.attachView(this);
        animFadein= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        binding.imgsplash.startAnimation(animFadein);

    }
    private void initializeObjects() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                splashViewModel.activityFinishedSplashTimer();
            }
        };
    }
    @Override
    public BaseModels<? extends BaseView> getModel() {
        return splashViewModel;
    }
    @Override
    protected void onStart() {
        super.onStart();
        splashViewModel.activityStarted();
    }
    @Override
    protected void onStop() {
        super.onStop();
        splashViewModel.activityStopped();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashViewModel.detachView();
    }
    @Override
    public void openListActivity() {
        IntentUtiles.openActivityInNewStack(SplashActivity.this, ListEmployesActivity.class);
    }
    @Override
    public void startSplashViewTimer() {
        handler.postDelayed(runnable, 4000);
    }
    @Override
    public void stopSplashViewTimer() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
