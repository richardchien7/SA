package com.example.sa.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sa.R;

public class ContainerActivity extends AppCompatActivity {
    private com.example.ch1.fragment.AFragment aFragment;
    private BFragment bFragment;
    private com.example.ch1.fragment.CFragment cFragment;
    private Button pass;
    private Button next;
    private int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        pass = (Button) findViewById(R.id.pass);

        next = (Button) findViewById(R.id.next);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aFragment == null) {
                    aFragment = new com.example.ch1.fragment.AFragment();
                }
                if (bFragment == null) {
                    bFragment = new BFragment();
                }
                if(count == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, aFragment).commitAllowingStateLoss();
                    count--;
                }else if(count == 3){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, bFragment).commitAllowingStateLoss();
                    count--;
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aFragment == null) {
                    aFragment = new com.example.ch1.fragment.AFragment();
                }
                if (bFragment == null) {
                    bFragment = new BFragment();
                }
                if (cFragment == null) {
                    cFragment = new com.example.ch1.fragment.CFragment();
                }
                if(count == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, bFragment).commitAllowingStateLoss();
                    count++;
                }else if(count == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, cFragment).commitAllowingStateLoss();
                    count++;
                }
            }
        });
        //實例化AFragment
        aFragment = new com.example.ch1.fragment.AFragment();
        //把AFragment添加到Activity中，記得調用commit
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, aFragment).commitAllowingStateLoss();
    }
}
