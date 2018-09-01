package com.noby.dptask.baseClasses;

/**
 * Created by A.Noby on 8/29/2018.
 */

public class BaseModels<V extends BaseView> {

    public V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public boolean isViewDetached() {
        return view == null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

}
