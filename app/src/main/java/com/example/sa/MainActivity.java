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
        Intent intent = new Intent(MainActivity.this,sch.class);
        startActivity(intent);

    }

}
