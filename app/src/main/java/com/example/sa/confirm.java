package com.example.sa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
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
    String d_id = "reckNpldTswjhpaZl";
    String vis_id = "reccySMSdrLwsHWQc";
    //
//
//    //將值轉成陣列
    String[] patient_id = new String[]{p_id};
    String[] doctor = new String[]{d_id};
    String[] visit_time_id = new String[]{vis_id};
    //int num = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Button b1 = (Button) findViewById(R.id.button1);
        //b1.setOnClickListener(confirm);
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(cancel);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String P_name = sharedPreferences.getString("patient_name", null);

        SharedPreferences sharedPreferences1 = getSharedPreferences("User", MODE_PRIVATE);
        final String P_id1 = sharedPreferences.getString("patient_id", null);
        final String[] P_id = {P_id1};
        //postRes("recXD2Gaj9GDjAd5e");

        //接前一頁的值
        //Bundle bundle =this.getIntent().getExtras();
        //b的例子
        String Name = "hello";
        Bundle bundle = this.getIntent().getExtras();
        final String V_id1 = bundle.getString("id");
        final String[] V_id = {V_id1};
        String Div_name = bundle.getString("division");
        final String Docname = bundle.getString("doctor");
        final String Doc_name1 = bundle.getString("doctor_id");
        final String Doc_name[] = {Doc_name1};

        String Office = bundle.getString("office");
        String Date = bundle.getString("date");
        int Period = bundle.getInt("time");
        String[] doctor_id;

        TextView tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setText("預約門診: " + Div_name);

        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText("看診醫師: " + Docname);

        TextView tv3 = (TextView) findViewById(R.id.textView3);
        tv3.setText("診間號碼: " + Office);

        TextView tv4 = (TextView) findViewById(R.id.textView4);
        tv4.setText("看診日期: " + Date);

        if (Period == 0) {
            TextView tv5 = (TextView) findViewById(R.id.textView5);
            tv5.setText("看診時段: " + "上午診");
        } else if (Period == 1) {
            TextView tv5 = (TextView) findViewById(R.id.textView5);
            tv5.setText("看診時段: " + "下午診");
        } else if (Period == 2) {
            TextView tv5 = (TextView) findViewById(R.id.textView5);
            tv5.setText("看診時段: " + "晚間診");
        }

//        TextView tv5 = (TextView)findViewById(R.id.textView5);
//        tv5.setText("看診時段: "+Period);

        TextView tv7 = (TextView) findViewById(R.id.textView6);
        tv7.setText("掛號病人姓名: " + P_name);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReservation(Doc_name, V_id, P_id);
                Intent intent = new Intent();
                intent.setClass(confirm.this, home.class);
                startActivity(intent);
            }
        });
//        private View.OnClickListener confirm = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                getReservation();
//                Intent intent = new Intent();
//                intent.setClass(confirm.this, home.class);
//                startActivity(intent);
//            }
//
//
//        };
    }


    private View.OnClickListener cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Toast.makeText(confirm.this, "已取消預約", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(confirm.this, choose_division.class);
            startActivity(intent);

        }


    };


    public void getReservation(final String[] Doc_name, final String[] V_id, final String[] patient_id) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<patient> call = MyAPIService.getReservation();
        call.enqueue(new Callback<patient>() {
            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {
                int len = response.body().getRecords().length;
                //int num = 1;
                int i = 0;
                while (i < len) {
                    if (response.body().getFields(i).getVisit_time_id()[0].equals(V_id[0]) &&
                            response.body().getFields(i).getDoctor()[0].equals(Doc_name[0])) {
                        num = response.body().getFields(i).getNumber()+1;
                    }
                    i++;
                }

                Reqconfirm q = new Reqconfirm(new fieldsconfirm(Doc_name, V_id, patient_id, num, "unregistered"));
                postReservation(q);

            }

            @Override
            public void onFailure(Call<patient> call, Throwable t) {

            }
        });
    }

    public void postReservation(final Reqconfirm q) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        // 建立要POST的物件
        Call<Reqconfirm> call = MyAPIService.PostReservation(q);
        call.enqueue(new Callback<Reqconfirm>() {
            @Override
            public void onResponse(Call<Reqconfirm> call, Response<Reqconfirm> response) {
                Toast.makeText(confirm.this, "預約成功!", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<Reqconfirm> call, Throwable t) {

            }
        });


    }

}
