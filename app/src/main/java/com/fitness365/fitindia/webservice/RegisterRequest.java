package com.fitness365.fitindia.webservice;

import android.app.ProgressDialog;
import android.content.Context;

import com.fitness365.fitindia.models.RegisterModel;
import com.fitness365.fitindia.models.StateDistrictBlockModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRequest implements Callback<RegisterModel> {

    Context context;
    ResponseListener responseListener;
    ProgressDialog progressDialog;
    HashMap<String, Object> postParams;

    public RegisterRequest(Context context, ResponseListener responseListener, HashMap<String, Object> postParams){
        this.context = context;
        this.responseListener = responseListener;
        this.postParams = postParams;
    }

    public void hitUserRequest() {
        ApiRequest apiService =
                ApiClient.getClient(context).create(ApiRequest.class);
        Call<RegisterModel> call = apiService.registerUser(postParams);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait while we are creating your account..");
        progressDialog.show();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
        try {
            progressDialog.dismiss();
            responseListener.onResponse(response.body());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<RegisterModel> call, Throwable t) {
        progressDialog.dismiss();
        responseListener.onResponse(null);
    }
}
