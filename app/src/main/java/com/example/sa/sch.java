package com.example.sa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class sch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Switch sw;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sch);
        sw = (Switch)findViewById(R.id.r1_switch);
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    Toast.makeText(sch.this,"開啟看診預先提醒",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(sch.this,"關閉看診預先提醒",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
