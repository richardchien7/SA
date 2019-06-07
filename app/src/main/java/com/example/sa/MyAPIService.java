package com.example.sa;

import java.util.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPIService {

    //我的"hi"是資料表rec...Ze9是我特定資料的id api_key是我的api(不要動ouo)
    @GET("patient?view=Grid%20view&api_key=keycPi0WBRNXVMtiW")
    Call<patient> getPat();
    @GET("patient/{id}?api_key=keycPi0WBRNXVMtiW") // 用{}表示路徑參數，@Path會將參數帶入至該位置
    Call<Req> getPatientById(@Path("id") String id);

//    @GET("reservation?view=Grid%20view&api_key=keycPi0WBRNXVMtiW")
//    Call<reservation> getRes();
//
//    @GET("visit_time?view=Grid%20view&api_key=keycPi0WBRNXVMtiW")
//    Call<visit_time> getVis();
//
    @GET("reservation/{id}?api_key=keycPi0WBRNXVMtiW") // 用{}表示路徑參數，@Path會將參數帶入至該位置
    Call<Req> getReservationById(@Path("id") String id);
//
//    @GET("visit_time/{id}?api_key=keycPi0WBRNXVMtiW")
//    Call<Req> getVisById(@Path("id") String id);
//
//    @GET("doctor/{id}?api_key=keycPi0WBRNXVMtiW")
//    Call<Req> getDocById(@Path("id") String id);
//
//    @GET("division/{id}?api_key=keycPi0WBRNXVMtiW")
//    Call<Req> getDivById(@Path("id") String id);
//
//
//
//
//    @POST("hi?api_key=keyKsJNFtZhy4rUjk") // 用@Body表示要傳送Body資料
//
//    @Headers({
//            "Accept: application/json; charset=utf-8",
//            "Content-Type: application/json; charset=utf-8"
//    })
//    Call<patient> postPat(@Body Req fields);
//
//    @DELETE("reservation/{id}?api_key=keycPi0WBRNXVMtiW")
//    Call<patient > deleteReservation();
        @DELETE("reservation/{id}?api_key=keycPi0WBRNXVMtiW")
        Call<patient> deleteReservation(@Path("id") String id);
//
//    @PATCH("hi/recOzDwjVrTf2rj5b?api_key=keyKsJNFtZhy4rUjk")
//    @Headers({
//            "Accept: application/json; charset=utf-8",
//            "Content-Type: application/json; charset=utf-8"
//    })
//    Call<patient> changePat(@Body Req fields);
}



