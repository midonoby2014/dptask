package com.noby.dptask.baseClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;

import com.noby.dptask.R;
import com.noby.dptask.app.DPApp;
import com.noby.dptask.utils.StaticMethods;


import java.util.List;

import dmax.dialog.SpotsDialog;
import pub.devrel.easypermissions.EasyPermissions;
/**
 * Created by A.Noby on 8/29/2018.
 */
public abstract class BaseActivity extends AppCompatActivity
        implements BaseView,
        EasyPermissions.PermissionCallbacks {

    SpotsDialog dialog;
    public BaseModels<? extends BaseView> baseModels;

    @Override
    public Context getContextBase() {
        return DPApp.getAppContext();
    }

    @Override
    public void showErrorMessageBase(CoordinatorLayout coordinatorLayout, Context context, String errormessage){
        StaticMethods.ShowSnake(coordinatorLayout,context,errormessage);
    }
    @Override
    public void showErrorMessageBase( Context context, String errormessage){
       // ToastUtil.showErrorToast(context,errormessage);
    }

    @Override
    public void showNoNetworkConnectionBase(CoordinatorLayout coordinatorLayout, Context context){
        StaticMethods.NOConnecting(coordinatorLayout,context);
    }
    @Override
    public void showNoNetworkConnectionBase( Context context){
        //ToastUtil.showErrorToast(context,context.getString(R.string.noconnection));
    }




    @Override
    public void showSuccessMessageBase(Context context, String message) {
      //  ToastUtil.showSuccessToast(context,message);
    }

    @Override
    public void showSuccessMessageBase(CoordinatorLayout coordinatorLayout, Context context, String message) {
        StaticMethods.ShowSnake(coordinatorLayout,context,message);
    }

    @Override
    public void showloadingviewBase(Context context){
        spotDialoug(context);
    }

    @Override
    public void hideloadingviewBase(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    private void spotDialoug(Context context){
        dialog = new SpotsDialog(context,getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseModels = getModel();
    }
    public abstract BaseModels<? extends BaseView> getModel();

    public  boolean hasPermissions(String... permissions) {
        return EasyPermissions.hasPermissions(this, permissions);
    }

    public  boolean hasListPermissions(String[] permissions) {
        return EasyPermissions.hasPermissions(this, permissions);
    }

    public   void requestPermissions(int requestCode, String... permissions) {
        requestAppPermissions(0, requestCode, permissions);
    }

    public  void requestListPermissions(int requestCode, String[] permissions) {
        requestAppPermissions(0, requestCode, permissions);
    }

    public  void requestPermissions(int requestCode, @StringRes int text, String... permissions) {
        requestAppPermissions(text, requestCode, permissions);
    }

    public  void requestListPermissions(int requestCode, @StringRes int text, String[] permissions) {
        requestAppPermissions(text, requestCode, permissions);
    }

    private void requestAppPermissions(@StringRes int text, int requestCode, String[] permissions) {
        if (text == 0) {
            text = R.string.app_need_permissions;
        }

        EasyPermissions.requestPermissions(this, getString(text),
                requestCode, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        String[] permissions = list.toArray(new String[list.size()]);
//        permissionsGranted(requestCode, permissions)
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        String[] permissions = list.toArray(new String[list.size()]);
//        permissionsGranted(requestCode, permissions)
    }

}
