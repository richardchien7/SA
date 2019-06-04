package com.example.sa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
    private Button hospital_infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button res = (Button) findViewById(R.id.reserve);
        Button sch = (Button) findViewById(R.id.schedule);
        hospital_infor = findViewById(R.id.hospital_info);

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
                Intent intent = new Intent(home.this, res.class);
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

    }

}
