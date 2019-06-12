package com.example.sa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class personalDocument extends AppCompatActivity {

    private MyAPIService MyAPIService;
    private TextView put_name;
    private TextView put_id;
    private TextView put_gender;
    private TextView put_birthday;
    private TextView put_phone;
    private TextView put_emer_per;
    private TextView put_emer_relate;
    private TextView put_emer_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences("User" , MODE_PRIVATE);
        String patient_id = sharedPreferences.getString("patient_id" , null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_document);

        put_name = (TextView) findViewById(R.id.put_name);
        put_id = (TextView) findViewById(R.id.put_id);
        put_gender = (TextView) findViewById(R.id.put_gender);
        put_birthday = (TextView) findViewById(R.id.put_birthday);
        put_phone = (TextView) findViewById(R.id.put_phone);
        put_emer_per = (TextView) findViewById(R.id.put_emer_per);
        put_emer_relate = (TextView) findViewById(R.id.put_emer_relate);
        put_emer_phone = (TextView) findViewById(R.id.put_emer_phone);

        getPatientinfo(patient_id);
    }

    public void getPatientinfo(final String id){
        MyAPIService = RetrofitManager.getInstance().getAPI();
        Call<Req> call = MyAPIService.getPatientById(id);
        call.enqueue(new Callback<Req>() {
            @Override
            public void onResponse(Call<Req> call, Response<Req> response) {

                put_name.setText(response.body().getFields().getName());
                put_id.setText(response.body().getFields().getId());
                put_gender.setText(response.body().getFields().getGender());
                put_birthday.setText(response.body().getFields().getBirthday());
                put_phone.setText(response.body().getFields().getPhone());
                put_emer_per.setText(response.body().getFields().getEmergency_name());
                put_emer_relate.setText(response.body().getFields().getEmergency_relationship());
                put_emer_phone.setText(response.body().getFields().getEmergency_phone());

            }

            @Override
            public void onFailure(Call<Req> call, Throwable t) {

            }

        });

    }
}
