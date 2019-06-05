package com.example.sa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class schh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Switch sw;
        sw = (Switch) findViewById(R.id.r1_switch);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schh);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    Toast.makeText(schh.this, "開啟看診預先提醒", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(schh.this, "關閉看診預先提醒", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
