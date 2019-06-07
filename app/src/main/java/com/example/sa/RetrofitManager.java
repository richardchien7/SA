package com.example.sa;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static RetrofitManager mInstance = new RetrofitManager();

    private MyAPIService myAPIService;

    private RetrofitManager() {

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        // 資料庫"測試"的URL為 : https://api.airtable.com/v0/app2Na8quylF0scW1/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.airtable.com/v0/app2Na8quylF0scW1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIService = retrofit.create(MyAPIService.class);
    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public MyAPIService getAPI() {
        return myAPIService;
    }
}
