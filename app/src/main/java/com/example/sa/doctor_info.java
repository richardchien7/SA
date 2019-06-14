package com.example.sa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class doctor_info extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] type = new String[] {"內科門診", "外科門診"};//載入第一下拉選單
    private String[] tea = new String[]{"心臟內科","腎臟內科","神經科"};//起始畫面時預先載入第二下拉選單
    private String[] tea2 = new String[]{"劉世奇","江亮霆","施振翔"};//起始畫面時預先載入第三下拉選單
    //第一下拉選取後載入第二下拉選單
    private String[][] type2 = new String[][]{{"心臟內科","腎臟內科","神經科"},{"胸腔外科","整型外科"}};
    //第二下拉選取後載入第三下拉選單
    private String[][][] type3 = new String[][][]{{{"劉世奇","江亮霆","施振翔"},{"吳義勇","盧建霖"},{"葉炳強","黃心樂"}},{{"張晃宙","曾穎凡"},{"李忠憲","劉昌杰"}}};
    private Spinner sp;//第一個下拉選單
    private Spinner sp2;//第二個下拉選單
    private Spinner sp3;//第三個下拉選單
    private Context context;

    private Button mBtn;
    private String str;

    ArrayAdapter<String> adapter ;

    ArrayAdapter<String> adapter2;

    ArrayAdapter<String> adapter3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        context = this;

        ////////////////////////
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Button bt = findViewById(R.id.confirm);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this); context = this;
        /////////////////////////////////////


        //程式剛啟始時載入第一個下拉選單
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp = (Spinner) findViewById(R.id.type_dep);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(selectListener);

        //因為下拉選單第一個為茶類，所以先載入茶類群組進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tea);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2 = (Spinner) findViewById(R.id.type_div);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(selectListener2);

        //因為下拉選單第二個為紅茶，所以先載入紅茶群組進第三個下拉選單
        adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tea2);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3 = (Spinner) findViewById(R.id.type_name);
        sp3.setAdapter(adapter3);



    }


    //第一個下拉類別的監看式
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){
            //讀取第一個下拉選單是選擇第幾個
            int pos = sp.getSelectedItemPosition();
            //重新產生新的Adapter，用的是二維陣列type2[pos]
            adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, type2[pos]);
            //載入第二個下拉選單Spinner
            sp2.setAdapter(adapter2);
        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };


    //第二個下拉類別的監看式
    private AdapterView.OnItemSelectedListener selectListener2 = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){
            //讀取第一個下拉選單是選擇第幾個
            int pos = sp.getSelectedItemPosition();
            //讀取第二個下拉選單是選擇第幾個
            int pos2 = sp2.getSelectedItemPosition();
            //重新產生新的Adapter，用的是三維陣列type3[pos][pos2]
            adapter3 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, type3[pos][pos2]);
            //載入第三個下拉選單Spinner
            sp3.setAdapter(adapter3);
        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(doctor_info.this, home.class);
            startActivity(intent);
        } else if (id == R.id.nav_res) {
            Intent intent = new Intent(doctor_info.this, choose_division.class);
            startActivity(intent);
        } else if (id == R.id.nav_schedule) {
            Intent intent = new Intent(doctor_info.this, sch.class);
            startActivity(intent);
        } else if (id == R.id.nav_docInfo) {
            Intent intent = new Intent(doctor_info.this,doctor_info.class);
            startActivity(intent);
        } else if (id == R.id.nav_personal) {
            Intent intent = new Intent(doctor_info.this, personalPage.class);
            startActivity(intent);
        } else if (id == R.id.nav_ask) {

        } else if (id == R.id.nav_question) {

        } else if (id == R.id.nav_hospital) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}