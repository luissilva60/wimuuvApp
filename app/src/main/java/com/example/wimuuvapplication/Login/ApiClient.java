package com.example.wimuuvapplication.Login;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRefrofit(){

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://wimuuv.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


            return retrofit;
    }

}
