package com.noby.dptask.Data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.noby.dptask.Data.beans.EmployeeResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.BuildConfig;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.noby.dptask.utils.AppConstant.API_LINK;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class MainApi {


    private static MainApiInterface getApi() {
        return getApi(MainApiInterface.class);
    }

    public static <T> T getApi(Class<T> aClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        // Retrofit.
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_LINK)
//                .client(client)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(API_LINK)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
//                .build()

        return retrofit.create(aClass);
    }


    public static void getEmployeeapi(int page, final ConnectionListener<EmployeeResponse> connectionListener) {
        getApi().GetEmpList(page).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmployeeResponse>() {

            @Override
            public void onError(Throwable e) {
                connectionListener.onFail(e);
            }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
            public void onNext(EmployeeResponse aBoolean) {
                ConnectionResponse<EmployeeResponse> response = new ConnectionResponse<>();
                response.data = aBoolean;
                connectionListener.onSuccess(response);
            }
        });
    }
}
