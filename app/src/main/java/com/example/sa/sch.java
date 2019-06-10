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
                    int len = response.body().getFields().getRes_id().length;
                    String[][] data2 = new String[len][7];
                    count = len;
                    int j = 0;
                    while (j < len) {
                        data2[j][0] = response.body().getFields().getRes_id()[j];
                        data2[j][1] = response.body().getFields().getDoctor_name()[j];
                        int period = response.body().getFields().getVisit_period()[j];
                        if (period == 0)
                            data2[j][2] = "上午診";
                        else if (period == 1)
                            data2[j][2] = "下午診";
                        else if (period == 2)
                            data2[j][2] = "夜間診";
                        String date = response.body().getFields().getVisit_date()[j];
                        data2[j][3] = date;
                        data2[j][4] = "10";

                        //定義好時間字串的格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
                        data2[j][6] = response.body().getFields().getDivision_name()[j] + "  診間:" + response.body().getFields().getOffice()[j];
                        j++;
                    }
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


    public void myDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定是否刪除此筆預約紀錄?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteReservation(id);
                        Toast.makeText(sch.this, "預約紀錄刪除成功!", Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(sch.this, "預約紀錄刪除失敗!", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog ad = builder.create();
        ad.show();
    }

    public void show(final boolean b,final String id) {
        if (b) {
            Toast.makeText(sch.this, id+"開啟看診預先提醒", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(sch.this, id+"關閉看診預先提醒", Toast.LENGTH_SHORT).show();
        }
    }


    private void refresh() {
        finish();
        Intent intent = new Intent(sch.this, sch.class);
        startActivity(intent);
    }

}
