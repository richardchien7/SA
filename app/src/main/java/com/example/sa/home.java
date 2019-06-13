package com.example.sa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button hospital_ask = (Button) findViewById(R.id.question);
        final Button hospital_infor = (Button) findViewById(R.id.hospital_info);
        Button res = (Button) findViewById(R.id.reserve);
        Button sch = (Button) findViewById(R.id.schedule);
        Button individual = (Button)findViewById(R.id.individual);
        Button aboutsystem = (Button) findViewById(R.id.system_info);
        Button doctor = (Button) findViewById(R.id.doctor_info);




        sch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, sch.class);
                startActivity(intent);
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, choose_division.class);
                startActivity(intent);
            }
        });

        hospital_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, hospital_information.class);
                startActivity(intent);
            }
        });
        hospital_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, hospital_ask.class);
                startActivity(intent);
            }
        });
        individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,personalPage.class);
                startActivity(intent);

            }
        });
        aboutsystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,about_system.class);
                startActivity(intent);

            }
        });
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,doctor_info.class);
                startActivity(intent);

            }
        });




    }

}
