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
    private MyAPIService MyAPIService;
    private EditText id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        id = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialogUtil.showProgressDialog(MainActivity.this);
                String patient_id = id.getText().toString().trim();//trim去除空白 getText獲取id裡的值
                String patient_password = password.getText().toString().trim();
                getPatient(patient_id, patient_password);

            }
        });


        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialogUtil.showProgressDialog(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);

            }
        });
    }

    public void getPatient(final String id, final String password) {
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<patient> call = MyAPIService.getPat();
        call.enqueue(new Callback<patient>() {
            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {//如果請求連接資料庫並成功抓到值
                int len = response.body().getRecords().length;
                int j = 0;
                boolean Successlogin = false;
                while (j < len) {
                    if (response.body().getFields(j).getId().equals(id) && response.body().getFields(j).getPassword().equals(password)) {
                        Successlogin = true;
                        Intent intent = new Intent(MainActivity.this, home.class);//成功後切換頁面
                        Bundle bundle = new Bundle();
                        bundle.putString("patient_id", response.body().getId(j));//抓到亂碼id
                        intent.putExtras(bundle);//切換頁面同時把值給傳過去
                        startActivity(intent);
                        break;
                    }
                    j++;
                }
                if (Successlogin == false) {
                    Toast.makeText(MainActivity.this, "帳號或密碼輸入錯誤!", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.dismiss();
                }
            }

            @Override
            public void onFailure(Call<patient> call, Throwable t) {
                text.setText(t.getMessage());
            }
        });

    }
}
