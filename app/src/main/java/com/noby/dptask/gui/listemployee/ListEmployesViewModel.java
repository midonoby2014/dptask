package com.noby.dptask.gui.listemployee;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.Data.beans.EmployeeResponse;
import com.noby.dptask.Data.network.ConnectionListener;
import com.noby.dptask.Data.network.ConnectionResponse;
import com.noby.dptask.Data.network.MainApi;
import com.noby.dptask.Data.network.MainApiBody;
import com.noby.dptask.R;
import com.noby.dptask.baseClasses.BaseModels;
import com.noby.dptask.utils.StaticMethods;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class ListEmployesViewModel  extends BaseModels<ListEmployesView> {

    boolean  IsLoading =  false ;


    void SendData(Context context, CoordinatorLayout coordinatorLayout,
                            int page , boolean isnewData) {

        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout, context);
            return   ;
        }
        if (!isnewData)
        view.showloadingviewBase(context);
        else
         view.showProgressBar();

        MainApi.getEmployeeapi(page, new ConnectionListener<EmployeeResponse>() {
            @Override
            public void onSuccess(ConnectionResponse<EmployeeResponse> connectionResponse) {
                if (!isnewData)
                view.hideloadingviewBase();
                else
                    view.hideProgressBar();
                if (connectionResponse.data!= null ) {
                    if (connectionResponse.data.getLinks().getNext() != null  )
                        IsLoading =  true;
                     else
                        IsLoading = false ;
                   if (!isnewData)
                   view.LoadingData(connectionResponse.data.getData(),IsLoading,connectionResponse.data.getMeta().last_page);
                   else
                   view.LoadingNewData(connectionResponse.data.getData(),IsLoading);
               }
            }
            @Override
            public void onFail(Throwable throwable) {
                if (!isnewData)
                view.hideloadingviewBase();
                else
                    view.hideProgressBar();
                view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
                Log.e("error", throwable.toString());
            }
        });
    }
}
