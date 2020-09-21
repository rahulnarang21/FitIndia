package com.fitness365.fitindia.webservice;

import android.app.ProgressDialog;
import android.content.Context;

import com.fitness365.fitindia.models.Category;
import com.fitness365.fitindia.models.RegisterModel;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventCategoryRequest implements Callback<ArrayList<Category>> {
    Context context;
    ResponseListener responseListener;
    ProgressDialog progressDialog;

    public EventCategoryRequest(Context context, ResponseListener responseListener){
        this.context = context;
        this.responseListener = responseListener;
    }

    public void hitUserRequest() {
        ApiRequest apiService =
                ApiClient.getClient(context).create(ApiRequest.class);
        Call<ArrayList<Category>> call = apiService.getEventsCategory();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
        try {
            progressDialog.dismiss();
            responseListener.onResponse(response.body());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
        progressDialog.dismiss();
        responseListener.onResponse(null);
    }
}
