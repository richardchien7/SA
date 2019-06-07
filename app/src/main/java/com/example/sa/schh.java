package com.example.sa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class schh extends AppCompatActivity {
    private Switch sw;
    private Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sw = (Switch) findViewById(R.id.r1_switch);
        bb = (Button) findViewById(R.id.r1_cancel);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schh);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(schh.this, "開啟看診預先提醒", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(schh.this, "關閉看診預先提醒", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
