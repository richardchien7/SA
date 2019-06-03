package com.example.sa;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface MyAPIService {
//我的"hi"是資料表rec...Ze9是我特定資料的id api_key是我的api(不要動ouo)
    @GET("patient?api_key=keyKsJNFtZhy4rUjk")
    Call<Infor> getInfor();

//    @GET("albums/{id}") // 用{}表示路徑參數，@Path會將參數帶入至該位置
//    Call<Infor> getAlbumsById(@Path("id") int id);

    @POST("hi?api_key=keyKsJNFtZhy4rUjk") // 用@Body表示要傳送Body資料
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    Call<Infor> postInfor(@Body Req fields);

    @DELETE("hi/recOzDwjVrTf2rj5b?api_key=keyKsJNFtZhy4rUjk")
    Call<Infor> deleteInfor();

    @PATCH("hi/recOzDwjVrTf2rj5b?api_key=keyKsJNFtZhy4rUjk")
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    Call<Infor> changeInfor(@Body Req fields);
}
