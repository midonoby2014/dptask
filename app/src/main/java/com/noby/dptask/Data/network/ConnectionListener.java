package com.noby.dptask.Data.network;

/**
 * Created by A.Noby on 8/29/2018.
 */
public interface ConnectionListener<T> {

    void onSuccess(ConnectionResponse<T> connectionResponse);

    void onFail(Throwable throwable);
}