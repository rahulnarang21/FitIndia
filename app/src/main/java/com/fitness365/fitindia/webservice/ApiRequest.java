package com.fitness365.fitindia.webservice;

/**
 * Created by CT13 on 2017-05-08.
 */

import com.fitness365.fitindia.helper.AppConfig;
import com.fitness365.fitindia.models.Category;
import com.fitness365.fitindia.models.RegisterModel;
import com.fitness365.fitindia.models.StateDistrictBlockModel;
import com.fitness365.fitindia.models.LoginModel;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiRequest {

    @GET(AppConfig.GET_STATE_DISTRICT_BLOCK_URL)
    Call<StateDistrictBlockModel> getStateDistrictBlock();

    @POST(AppConfig.LOGIN_URL)
    Call<LoginModel> loginUser(@Body HashMap<String, Object> hashMap);

    @POST(AppConfig.REGISTER_URL)
    Call<RegisterModel> registerUser(@Body HashMap<String, Object> hashMap);

    @POST(AppConfig.GET_EVENTS_CATEGORY_URL)
    Call<ArrayList<Category>> getEventsCategory();
}