package com.example.sa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class visit_next extends AppCompatActivity {

    private MyAPIService myAPIService;
    private TextView Mon,Tue,Wed,Thu,Fri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_next);

        Bundle bundle0311 =this.getIntent().getExtras();
        final String choose = bundle0311.getString("division");
        //抓到設定的textview
        setTextView();

        //定義好時間字串的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //抓到今天日期
        Date dt = new Date();
        //抓到今天的星期
        int week_td = dt.getDay();
        //計算下一週從哪一天開始
        int week_start = 7-week_td;

        //將日期的形式轉換為 年-月-日
        String dts =sdf.format(dt);

        //把date轉為Calendar型態，並加上5天， 用來限制抓下來的資料
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dt);
        calendar1.add(Calendar.DATE, week_start);//下一週開始日期

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(dt);
        calendar2.add(Calendar.DATE, week_start+5);//下一週結束日期

        //將Calendar轉換回Date
        Date tdt1 =calendar1.getTime();
        Date tdt2 =calendar2.getTime();

        //將加五天後的日期轉換為 年-月-日
        String wa1 =sdf.format(tdt1);
        String wa2 =sdf.format(tdt2);


        //將兩天日期放進函式
        getVis(tdt1,tdt2,choose,week_td);

        setNullWeek();

        Button last = (Button) findViewById(R.id.last);
        last.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(visit_next.this,visit.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("division",choose);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });


    }

    public void getVis(final  Date  nw,final Date aw, final String choose,final int week_td){
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<visit_time> call = myAPIService.getVis();

        call.enqueue(new Callback<visit_time>() {
            @Override
            public void onResponse(Call<visit_time> call, Response<visit_time> response) {

                //抓到所有資料共有幾筆
                int len = response.body().getRecords().length;
                int i = 0;
                //跑一迴圈判斷排班日期是否在所設定的範圍內
                while(i < len)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    //將丟進來的Date 日期轉換為 String
                    String w = sdf.format(nw);
                    String nw = sdf.format(aw);
                    //獲取此筆日期
                    String date = response.body().getFields(i).getDate();
                    //設定條件式
                    if(date.compareToIgnoreCase(w) >= 0 && date.compareToIgnoreCase(nw)<=0) {
                        try {
                            //將此筆String的日期轉換為Date的日期
                            Date t = sdf.parse(date);
                            //抓此筆資料的的星期 範圍為 : 1~7
                            int week = t.getDay();
                            //抓此筆資料的時段(上午、下午、夜間)
                            int time = response.body().getFields(i).getTime();

                            //抓到有幾名醫生排班
                            int qua = response.body().getFields(i).getDoctor_id().length;
                            int j = 0;
                            //假如此時段(visit_id)有多名醫師排班，做多次print的動作

                            //加到第幾個欄位
                            int num = 0;
                            while (j < qua)
                            {
                                //獲取此看診時間id
                                final int id = response.body().getFields(i).getVisit_id();

                                //獲取醫生姓名
                                String doctor_name = response.body().getFields(i).getDoctor_name()[j];

                                //獲取診間號碼
                                String office = response.body().getFields(i).getDoctor_office()[j];

                                //抓出科室
                                String division = response.body().getFields(i).getDivision_name()[j];
                                if(division.equals(choose)){
                                    //丟入此方法 print中
                                    print(week, time, date, doctor_name,id,office,division);
                                }

                                j++;

                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




                    }
                    i++;
                }

            }

            @Override
            public void onFailure(Call<visit_time> call, Throwable t) {
                Mon.setText(t.getMessage());

            }
        });

    }



    public void print(int week,int time,String date,String dn,int id, String office,String division)
    {


        LinearLayout Mon0 = (LinearLayout) findViewById(R.id.Mon0);
        LinearLayout Mon1 = (LinearLayout) findViewById(R.id.Mon1);
        LinearLayout Mon2 = (LinearLayout) findViewById(R.id.Mon2);
        LinearLayout Tue0 = (LinearLayout) findViewById(R.id.Tue0);
        LinearLayout Tue1 = (LinearLayout) findViewById(R.id.Tue1);
        LinearLayout Tue2 = (LinearLayout) findViewById(R.id.Tue2);
        LinearLayout Wed0 = (LinearLayout) findViewById(R.id.Wed0);
        LinearLayout Wed1 = (LinearLayout) findViewById(R.id.Wed1);
        LinearLayout Wed2 = (LinearLayout) findViewById(R.id.Wed2);
        LinearLayout Thu0 = (LinearLayout) findViewById(R.id.Thu0);
        LinearLayout Thu1 = (LinearLayout) findViewById(R.id.Thu1);
        LinearLayout Thu2 = (LinearLayout) findViewById(R.id.Thu2);
        LinearLayout Fri0 = (LinearLayout) findViewById(R.id.Fri0);
        LinearLayout Fri1 = (LinearLayout) findViewById(R.id.Fri1);
        LinearLayout Fri2 = (LinearLayout) findViewById(R.id.Fri2);



        //week為星期 time為時段 date為排班當天日期 dn為排班醫生的姓名
        //目前我沒做星期六、日的排班，可依需要增加或刪減
        String date1 = date.substring(5);
        if (week == 1) {
            Mon.setText(date1+"\n週一");
            if (time == 0){
                createButton(dn,Mon0,id,date,time,office,division);
            }

            else if (time == 1)
                createButton(dn,Mon1,id,date,time,office,division);
            else
                createButton(dn,Mon2,id,date,time,office,division);
        }
        else if (week == 2) {
            Tue.setText(date1+"\n週二");
            if (time == 0)
                createButton(dn,Tue0,id,date,time,office,division);
            else if (time == 1)
                createButton(dn,Tue1,id,date,time,office,division);
            else
                createButton(dn,Tue2,id,date,time,office,division);
        }
        else if (week == 3) {
            Wed.setText(date1+"\n週三");
            if (time == 0)
                createButton(dn,Wed0,id,date,time,office,division);
            else if (time == 1)
                createButton(dn,Wed1,id,date,time,office,division);
            else{
                createButton(dn,Wed2,id,date,time,office,division);

            }

        }
        else if (week == 4) {
            Thu.setText(date1+"\n週四");
            if (time == 0)
                createButton(dn,Thu0,id,date,time,office,division);
            else if (time == 1)
                createButton(dn,Thu1,id,date,time,office,division);
            else
                createButton(dn,Thu2,id,date,time,office,division);
        }
        else if (week == 5) {
            Fri.setText(date1+"\n週五");
            if (time == 0){
                createButton(dn,Fri0,id,date,time,office,division);
            }
            else if (time == 1){
              createButton(dn,Fri1,id,date,time,office,division);
            }
            else{
                createButton(dn,Fri2,id,date,time,office,division);

            }

        }
    }

    public void setTextView(){
        Mon = findViewById(R.id.Mon);
//        Mon0 = findViewById(R.id.Mon0);
//        Mon1 = findViewById(R.id.Mon1);
//        Mon2 = findViewById(R.id.Mon2);

        Tue = findViewById(R.id.Tue);
//        Tue0 = findViewById(R.id.Tue0);
//        Tue1 = findViewById(R.id.Tue1);
//        Tue2 = findViewById(R.id.Tue2);

        Wed = findViewById(R.id.Wed);
//        Wed0 = findViewById(R.id.Wed0);
//        Wed1 = findViewById(R.id.Wed1);
//        Wed2 = findViewById(R.id.Wed2);

        Thu = findViewById(R.id.Thu);
//        Thu0 = findViewById(R.id.Thu0);
//        Thu1 = findViewById(R.id.Thu1);
//        Thu2 = findViewById(R.id.Thu2);

        Fri = findViewById(R.id.Fri);
//        Fri0 = findViewById(R.id.Fri0);
//        Fri1 = findViewById(R.id.Fri1);
//        Fri2 = findViewById(R.id.Fri2);




    }

    public void setNullWeek(){

        if(TextUtils.isEmpty(Mon.getText())){
            Mon.setText("週一");
        }
        if(TextUtils.isEmpty(Tue.getText())){
            Tue.setText("週二");
        }
        if(TextUtils.isEmpty(Wed.getText())){
            Wed.setText("週三");
        }
        if(TextUtils.isEmpty(Thu.getText())){
            Thu.setText("週四");
        }
        if(TextUtils.isEmpty(Fri.getText())){
            Fri.setText("週五");
        }

    }

    private void createButton(final String txt, LinearLayout view,final int id,final String date,final int time,final String office,final String division) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        Button b1 = new Button(this);
        b1.setText(txt);
        b1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_border, 0, 0, 0);
        b1.setBackgroundColor(Color.WHITE);
        b1.setTextSize(20);
        b1.setLayoutParams(vlp);//设置TextView的布局
        view.addView(b1);//将TextView 添加到子View 中

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(visit_next.this,confirm.class);
                //Toast.makeText(getApplicationContext(),String.valueOf(id),Toast.LENGTH_SHORT).show();
                Bundle bundle_confirm = new Bundle();
                bundle_confirm.putInt("id",id);
                bundle_confirm.putString("doctor",txt);
                bundle_confirm.putString("date",date);
                bundle_confirm.putInt("time",time);
                bundle_confirm.putString("office",office);
                bundle_confirm.putString("division",division);
                intent.putExtras(bundle_confirm);
                startActivity(intent);

            }
        });

    }


}
