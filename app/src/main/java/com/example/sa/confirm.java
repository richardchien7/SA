package com.example.sa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class confirm extends AppCompatActivity {

    private MyAPIService MyAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        postRes("recXD2Gaj9GDjAd5e");

        //接前一頁的值
        Bundle bundle =this.getIntent().getExtras();
        //b的例子
        String Name = bundle.getString("Name");

        TextView tv1 = (TextView)findViewById(R.id.textView1);
        tv1.setText("預約門診: "+Name);

        TextView tv2 = (TextView)findViewById(R.id.textView2);
        tv2.setText("看診醫師: "+Name);

        TextView tv3 = (TextView)findViewById(R.id.textView3);
        tv3.setText("診間號碼: "+Name);

        TextView tv4 = (TextView)findViewById(R.id.textView4);
        tv4.setText("看診日期: "+Name);

        TextView tv5 = (TextView)findViewById(R.id.textView5);
        tv5.setText("看診時段: "+Name);

        TextView tv6 = (TextView)findViewById(R.id.textView6);
        tv6.setText("預估預約編號: "+Name);

        TextView tv7 = (TextView)findViewById(R.id.textView7);
        tv7.setText("掛號病人姓名: "+Name);

    }

    public void postRes(final String id) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        // 建立要POST的物件
        

    }


}
