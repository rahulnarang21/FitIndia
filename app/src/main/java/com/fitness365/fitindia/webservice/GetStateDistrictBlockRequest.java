package com.fitness365.fitindia.webservice;

import android.app.ProgressDialog;
import android.content.Context;

import com.fitness365.fitindia.models.StateDistrictBlockModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStateDistrictBlockRequest implements Callback<StateDistrictBlockModel> {

    Context context;
    ResponseListener responseListener;
    ProgressDialog progressDialog;

    public GetStateDistrictBlockRequest(Context context,ResponseListener responseListener){
        this.context = context;
        this.responseListener = responseListener;
    }

    public void hitUserRequest() {
        ApiRequest apiService =
                ApiClient.getClient(context).create(ApiRequest.class);
        Call<StateDistrictBlockModel> call = apiService.getStateDistrictBlock();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading the data! Please wait...");
        progressDialog.show();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<StateDistrictBlockModel> call, Response<StateDistrictBlockModel> response) {
        try {
            progressDialog.dismiss();
            responseListener.onResponse(response.body());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<StateDistrictBlockModel> call, Throwable t) {
        progressDialog.dismiss();
        responseListener.onResponse(null);
    }
}
