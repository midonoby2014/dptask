package com.noby.dptask.Data.network;

import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.Data.beans.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by A.Noby on 8/29/2018.
 */
public interface MainApiInterface {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Accept-Language: En",
            "x-api-key: d7VJ5M5X9emP8ZXvmALlPryAwIJMXmRtO9WxdDj55ypFPgn3JCfTqM3CaEsD"
    })
    @GET("api/users")
    Observable<EmployeeResponse> GetEmpList(@Query("page") int pageIndex);
//
//    @POST("customer/Welcome")
//    Observable<MainResponse<WelcomeResponse>> WelcomePage(@Body RequestBody requestBody);
}
