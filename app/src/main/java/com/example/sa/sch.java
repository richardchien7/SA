package com.example.sa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sch extends AppCompatActivity {
    private MyAPIService MyAPIService;
    static String[][] data2 = new String[10][6];
    private ListView listView;
    public static int index = 0;
     static int count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sch);

        listView = (ListView)findViewById(R.id.lv1);


        getPat("lee");


    }

    public void getPat(final String name) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<patient> call = MyAPIService.getPat();
        call.enqueue(new Callback<patient>() {//成功透過onresponse回傳 失敗用onfailure回傳
                         @Override
                         public void onResponse(Call<patient> call, Response<patient> response) {
                             int i ;
                             int len = response.body().getRecords().length;
                             for (i = 0; i < len; i++) {
                                 if (response.body().getFields(i).getName().equalsIgnoreCase(name)) {
                                     String p_id;
                                     int size = response.body().getFields(i).getRes().length;
                                     count = size;
                                     int j = 0;
                                     while (j < size) {
                                         p_id = response.body().getFields(i).getRes()[j];
                                         getReservationById(p_id);
                                         j++;
                                     }
                                     listView.setAdapter(new MyListAdapter(data2,sch.this,size));

                                 }
                             }
//
                         }

                         @Override
                         public void onFailure(Call<patient> call, Throwable t) {

                         }
                     }
        );
    }

    public void getReservationById(final String id)
    {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<Req> call = MyAPIService.getReservationById(id);

        call.enqueue(new Callback<Req>() {
            @Override
            public void onResponse(Call<Req> call, Response<Req> response) {

                String title = "212";
                String doctor = response.body().getFields().getDoctor_name()[0];
                String period = "";
                if (response.body().getFields().getVisit_period()[0] == 0)
                    period = "上午診";
                else if (response.body().getFields().getVisit_period()[0] == 1)
                    period = "下午診";
                else if (response.body().getFields().getVisit_period()[0] == 2)
                    period = "夜間診";
                String date = response.body().getFields().getVisit_date()[0];
                String num = "2";
                String process = "1";
                data2[index][0] =doctor;
                data2[index][1] =period;
                data2[index][2] =date;
                data2[index][3] =num;
                data2[index][4] =process;
                index++;
            }
            @Override
            public void onFailure(Call<Req> call, Throwable t) {
                //ddd.setText(t.toString());
            }
        });

    }
}

