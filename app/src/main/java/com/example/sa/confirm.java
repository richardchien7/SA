package com.example.sa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class confirm extends AppCompatActivity {
private int num = 1;
    private MyAPIService MyAPIService;

    //接值
    String p_id = "recPrpA5pZVkqJhzL";
    String d_id = "reckNpldTswjhpaZl" ;
    String vis_id = "reccySMSdrLwsHWQc";


    //將值轉成陣列
    String[] patient_id = new String[]{p_id};
    String[] doctor = new String[]{d_id};
    String[] visit_time_id = new String[]{vis_id};
    //int num = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(confirm);
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(cancel);

        SharedPreferences sharedPreferences = getSharedPreferences("User" , MODE_PRIVATE);
        String P_name = sharedPreferences.getString("patient_name" , null);
        //postRes("recXD2Gaj9GDjAd5e");

        //接前一頁的值
        //Bundle bundle =this.getIntent().getExtras();
        //b的例子
        String Name = "hello";

        Bundle bundle =this.getIntent().getExtras();
        String Div_name = bundle.getString("Name");
        String Doc_name = bundle.getString("doctor");
        String Office = bundle.getString("Name");
        String Date= bundle.getString("Name");
        String Period = bundle.getString("Name");
        String Num = bundle.getString("Name");




        TextView tv1 = (TextView)findViewById(R.id.textView1);
        tv1.setText("預約門診: "+Div_name);

        TextView tv2 = (TextView)findViewById(R.id.textView2);
        tv2.setText("看診醫師: "+Doc_name);

        TextView tv3 = (TextView)findViewById(R.id.textView3);
        tv3.setText("診間號碼: "+Office);

        TextView tv4 = (TextView)findViewById(R.id.textView4);
        tv4.setText("看診日期: "+Date);

        TextView tv5 = (TextView)findViewById(R.id.textView5);
        tv5.setText("看診時段: "+Period);

        TextView tv7 = (TextView)findViewById(R.id.textView6);
        tv7.setText("掛號病人姓名: "+P_name);


    }

    private View.OnClickListener confirm = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            getReservation();
            Intent intent = new Intent();
            intent.setClass(confirm.this, home.class);
            startActivity(intent);
        }


    };

    private View.OnClickListener cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Toast.makeText(confirm.this, "已取消預約", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(confirm.this, choose_division.class);
            startActivity(intent);

        }


    };


    public void getReservation()
    {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<patient> call = MyAPIService.getReservation();
        call.enqueue(new Callback<patient>() {
            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {
                int len = response.body().getRecords().length;
                //int num = 1;
                int i = 0;
                while(i<len){

                    if(response.body().getFields(i).getVisit_time_id().equals(visit_time_id) &&
                            response.body().getFields(i).getDoctor().equals(doctor)){
                        num++;
                    }
                    i++;
                }

                Req q = new Req(new fields(doctor,visit_time_id , patient_id, num, "finished"));
                postReservation(q);

            }

            @Override
            public void onFailure(Call<patient> call, Throwable t) {

            }
        });
    }

    public void postReservation(final Req q) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        // 建立要POST的物件
        Call<Req> call = MyAPIService.PostReservation(q);
        call.enqueue(new Callback<Req>() {
            @Override
            public void onResponse(Call<Req> call, Response<Req> response) {
                Toast.makeText(confirm.this, response.toString(), Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<Req> call, Throwable t) {

            }
        });


    }

}
