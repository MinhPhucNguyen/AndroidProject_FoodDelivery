package com.example.loginscreen.Retrofit;


import com.example.loginscreen.Domain.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;



public interface RetrofitApi {
    @GET
    Call<ResponseModel> getResponse(@Url String url);
}