package com.noby.dptask.gui.splash;

import com.noby.dptask.baseClasses.BaseModels;

/**
 * Created by A.Noby on 8/29/2018.
 */
 class SplashViewModel  extends BaseModels<SplashView> {

    void activityFinishedSplashTimer(){
        view.openListActivity();
    }
    void activityStarted() {
        view.startSplashViewTimer();
    }
    void activityStopped() {
        view.stopSplashViewTimer();
    }
}
