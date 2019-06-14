package com.example.sa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sch extends AppCompatActivity {
    private MyAPIService MyAPIService;

    private ListView listView;
    static int Numbering = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("User" , MODE_PRIVATE);
        String patient_id = sharedPreferences.getString("patient_id" , null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sch);
        listView = (ListView) findViewById(R.id.lv1);
        getPatinetById(patient_id);

    }

    public void getPatinetById(final String id) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<Req> call = MyAPIService.getPatientById(id);
        call.enqueue(new Callback<Req>() {//成功透過onresponse回傳 失敗用onfailure回傳
            @Override
            public void onResponse(Call<Req> call, Response<Req> response) {
                int count = 0;

                if(response.body().getFields().getRes_id() != null) {
                    //回傳預約紀錄筆數
                    int len = response.body().getFields().getRes_id().length;
                    //用此筆數給陣列設定大小
                    String[][] data2 = new String[len][8];
                    count = len;
                    int j = 0;
                    while (j < len) {
                        //持續將資料加入陣列

                        //預約紀錄的亂碼ID
                        data2[j][0] = response.body().getFields().getRes_id()[j];
                        //醫生的姓名
                        data2[j][1] = response.body().getFields().getDoctor_name()[j];
                        //判斷時段
                        int period = response.body().getFields().getVisit_period()[j];
                        if (period == 0)
                            data2[j][2] = "上午診";
                        else if (period == 1)
                            data2[j][2] = "下午診";
                        else if (period == 2)
                            data2[j][2] = "夜間診";
                        //看診日期
                        String date = response.body().getFields().getVisit_date()[j];
                        data2[j][3] = date;
                        //預約紀錄
                        data2[j][4] = response.body().getFields().getNumbering()[j]+"";

                        //定義好時間字串的格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
                        //抓到今天日期
                        Date dt = new Date();

                        //將日期的形式轉換為 年-月-日
                        String dts = sdf.format(dt);

                        if (date.compareTo(dts) < 0) {
                            data2[j][5] = "看診日期已過!";
                        } else if (date.compareTo(dts) > 0) {
                            data2[j][5] = "看診日期未到!";
                        } else {
                            data2[j][5] = "目前看診進度為 :"+Numbering;
                        }
                        //科室名稱
                        data2[j][6] = response.body().getFields().getDivision_name()[j];
                        //診間
                        data2[j][7] = response.body().getFields().getOffice()[j];
                        j++;
                    }
                    //若無看診紀錄 則不顯示
                    if (data2.length > 0){
                        listView.setAdapter(new MyListAdapter(data2, sch.this, count));
                        Numbering++;
                    }
                }
            }

            @Override
            public void onFailure(Call<Req> call, Throwable t) {
            }
        });
    }

    //利用唯一亂碼ID 刪除此筆預約紀錄
    public void deleteReservation(final String id) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<patient> call = MyAPIService.deleteReservation(id);
        call.enqueue(new Callback<patient>() {
            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {

            }

            @Override
            public void onFailure(Call<patient> call, Throwable t) {
            }
        });
    }

    //點擊取消按鈕後，做的後續動作
    public void myDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //跳出提醒視窗
        builder.setMessage("確定是否刪除此筆預約紀錄?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //如點選是，則刪除此筆紀錄並跳出通知，並重新整理此頁面
                        deleteReservation(id);
                        Toast.makeText(sch.this, "預約紀錄刪除成功!", Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //如點選否，則不做動作，並跳出通知
                        Toast.makeText(sch.this, "預約紀錄刪除失敗!", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog ad = builder.create();
        ad.show();
    }

    public void show(final boolean b,final String office,final String doctor) {
        //如果開啟提醒
        if (b) {
            Toast.makeText(sch.this, "醫師 :"+doctor+" 診間為:"+office+"\n開啟看診預先提醒", Toast.LENGTH_SHORT).show();
        }
        //如關閉提醒
        else {
            Toast.makeText(sch.this, "醫師 :"+doctor+" 診間為:"+office+"\n關閉看診預先提醒", Toast.LENGTH_SHORT).show();
        }
    }


    private void refresh() {
        //先結束此畫面，並跳回原畫面
        finish();
        Intent intent = new Intent(sch.this, sch.class);
        startActivity(intent);
    }

}
