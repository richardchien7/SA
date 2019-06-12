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

    //可以使用我的金鑰，一樣能抓得到資料庫值，不用特地改成自己的金鑰

    //  資料表/顯示條件/金鑰  傳回所有病人紀錄
    @GET("patient?view=Grid%20view&api_key=keycPi0WBRNXVMtiW")
    Call<patient> getPat();
    //  資料表/顯示條件/金鑰  傳回所有預約紀錄
    @GET("reservation?view=Grid%20view&api_key=keycPi0WBRNXVMtiW")
    Call<patient> getReservation();
    // 資料表/病人唯一ID/金鑰   傳回此病人之紀錄
    @GET("patient/{id}?api_key=keycPi0WBRNXVMtiW") // 用{}表示路徑參數，@Path會將參數帶入至該位置
    Call<Req> getPatientById(@Path("id") String id);
    // 資料表/病人唯一ID/金鑰   傳回此預約紀錄之細項
    @GET("reservation/{id}?api_key=keycPi0WBRNXVMtiW") // 用{}表示路徑參數，@Path會將參數帶入至該位置
    Call<Req> getReservationById(@Path("id") String id);

    // 資料表/病人唯一ID/金鑰   刪除此筆ID之預約紀錄
    @DELETE("reservation/{id}?api_key=keycPi0WBRNXVMtiW")
    Call<patient> deleteReservation(@Path("id") String id);






    @POST("reservation?api_key=keycPi0WBRNXVMtiW")
    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json; charset=utf-8"
    })
    Call<Req> PostReservation(@Body Req Con);



    @POST("patient?api_key=keycPi0WBRNXVMtiW")
    @Headers({
                    "Accept: application/json; charset=utf-8",
                    "Content-Type: application/json; charset=utf-8"
            })
    Call<Req> PostPatient(@Body Req fields);

}