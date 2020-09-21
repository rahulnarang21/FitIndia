package com.fitness365.fitindia.webservice;

/**
 * Created by CT13 on 2017-05-08.
 */


import android.content.Context;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.helper.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
