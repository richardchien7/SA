package com.example.sa;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MyAPIService MyAPIService;

    private TextView text;
    private String haha;
    private Button login;
    private TextView id;
    private TextView pw;
    //123

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        login = (Button) findViewById(R.id.login);
        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idd = id.getText().toString().trim();
                String pww = pw.getText().toString().trim();
                text.setText(idd + " " + pww);
                getPat("F120000001","19870328");
//
            }
        });
    }

    public void getPat(final String id, final String pw) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<patient> call = MyAPIService.getPat();

        call.enqueue(new Callback<patient>() {//成功透過onresponse回傳 失敗用onfailure回傳
            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {

                int i = 0;
                int len = response.body().getRecords().length;
                for (i = 0; i < len; i++) {
                    text.append("\n"+response.body().getFields(i).getId()+" "+response.body().getFields(i).getPassword());
                    if (response.body().getFields(i).getId().equals( id) && response.body().getFields(i).getPassword().equals(pw)) {
                        try {
                            Intent intent = new Intent(MainActivity.this, home.class);
                            startActivity(intent);
                        } catch (java.lang.NullPointerException e) {
                            text.append(e.getMessage());
                        }


                    }
                }
//
            }

            @Override
            public void onFailure(Call<patient> call, Throwable t) {
                //ddd.setText(t.getMessage());
            }
        });
    }
}
