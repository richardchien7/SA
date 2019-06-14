package com.example.sa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class choose_division extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] type = new String[] {"內科門診", "外科門診", "獨立部科"};
    private String[] internal = new String[]{"心臟內科","胃腸肝膽內科","腎臟內科","胸腔內科","神經科","新陳代謝科","血液腫瘤科","感染科","一般內科"};
    private String[][] type2 = new String[][]{{"心臟內科","胃腸肝膽內科","腎臟內科","胸腔內科","神經科","新陳代謝科","血液腫瘤科","感染科","一般內科"},{"心臟血管外科","一般外科","乳房外科","大腸直腸外科","胸腔外科","泌尿科","神經外科","整形外科"},{"職業醫學科","兒科","健兒門診","婦產科","牙科","口腔顎面","骨科","運動醫學中心O","運動醫學中心R","家庭醫學科","精神科","放射腫瘤科","皮膚科","耳鼻喉科","眼科","復健科","疼痛/麻醉科","營養諮詢","腦血管介入治療門診"}};
    private Spinner sp;//第一個下拉選單
    private Spinner sp2;//第二個下拉選單
    private Context context;

    private Button mBtn;
    private String str;

    ArrayAdapter<String> adapter ;

    ArrayAdapter<String> adapter2;

    public OnItemSelectedListener selectListener = new OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){
            //讀取第一個下拉選單是選擇第幾個
            int pos = sp.getSelectedItemPosition();
            //重新產生新的Adapter，用的是二維陣列type2[pos]
            adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, type2[pos]);
            //載入第二個下拉選單Spinner
            sp2.setAdapter(adapter2);
            int pos2 = sp2.getSelectedItemPosition();
            str= type2[pos][pos2];

        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_division);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Button bt = findViewById(R.id.confirm);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.app_bar_choose_division);

        context = this;

        //程式剛啟始時載入第一個下拉選單
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp = (Spinner) findViewById(R.id.type);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(selectListener);

        //因為下拉選單第一個為茶類，所以先載入茶類群組進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, internal);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2 = (Spinner) findViewById(R.id.type2);
        sp2.setAdapter(adapter2);

        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //拿到被选择项的值
                str = (String) sp2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        mBtn = (Button) findViewById(R.id.confirm);
        mBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose_division.this,visit.class);   //連結選擇科別與醫生時段之button, for阿寶的時段及醫生
                Bundle bundle = new Bundle();
                bundle.putString("division",str);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }


    //第一個下拉類別的監看式



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
            Intent intent = new Intent(choose_division.this, home.class);
            startActivity(intent);
        } else if (id == R.id.nav_res) {
            Intent intent = new Intent(choose_division.this, choose_division.class);
            startActivity(intent);
        } else if (id == R.id.nav_schedule) {
            Intent intent = new Intent(choose_division.this, sch.class);
            startActivity(intent);
        } else if (id == R.id.nav_docInfo) {
            Intent intent = new Intent(choose_division.this,doctor_info.class);
            startActivity(intent);
        } else if (id == R.id.nav_personal) {
            Intent intent = new Intent(choose_division.this, personalPage.class);
            startActivity(intent);
        } else if (id == R.id.nav_ask) {

        } else if (id == R.id.nav_question) {
            Intent intent = new Intent(choose_division.this, about_system.class);
            startActivity(intent);
        } else if (id == R.id.nav_hospital) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}