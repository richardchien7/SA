package com.example.sa;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private String haha;
    private Button login;
    MyAPIService myAPIService;
    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    check();//這是拿取資料
                } catch (Exception e) {

                    Log.e("MainActivity", e.getMessage());//
                }//這是拿取資料
            }
        });
//        try {
//            check();//這是拿取資料
//        } catch (Exception e) {
//
//            Log.e("MainActivity", e.getMessage());//
//        }//這是拿取資料
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, home.class);
//                startActivity(intent);
//            }
//        });
    }
    public void check(){

        account =(EditText) findViewById(R.id.account);
        final String Account = account.getText().toString();
        password = (EditText)findViewById(R.id.password);
        final String Password = password.getText().toString();

        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<Infor> call = myAPIService.getInfor();
        //mtext_view_result.setText(call.toString());

        call.enqueue(new Callback<Infor>() {//成功透過onresponse回傳 失敗用onfailure回傳
            @Override
            public void onResponse(Call<Infor> call, Response<Infor> response) {
                int j = 0;
                int x = 0;
                for (int i = 0; i < response.body().getRecords().length; i++) {
                    if(Account.equalsIgnoreCase(response.body().getfieldsid(i)) && Password.equalsIgnoreCase(response.body().getfieldspassword(i))){
                        j++;

                    }
                }
                if(j != 0){
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                }else{
                    text.setText(response.body().getfieldsid(1) + "hi");
                    Toast.makeText(MainActivity.this,"error" + j,Toast.LENGTH_SHORT).show();
                }

                //Infor infor = new Infor(response.body().getId(), response.body().getFields(), response.body().getCreateTime());
                //array.add(infor);
                //mtext_view_result.setText(infor.getId()+"hi");
                String content = "";
//                for(Infor word: array){
//                    mtext_view_result.append(word.getfieldsName()+"\n");
//                }
            }

            @Override
            public void onFailure(Call<Infor> call, Throwable t) {

            }
        });
    }
}
