package com.example.sa;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
    private EditText birthday;
    private EditText name;
    private EditText ID;
    private EditText password;
    private EditText passwordcheck;
    private EditText address;
    private EditText phone;
    private EditText Emergencyname;
    private EditText Emergencyrelation;
    private EditText Emergencyphone;
    static String p_birthday;
    private  MyAPIService myAPIService;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        birthday = (EditText) findViewById(R.id.edit_birthday);

        name = (EditText) findViewById(R.id.edit_name);
        ID = (EditText) findViewById(R.id.edit_ID);
        password = (EditText) findViewById(R.id.edit_password);
        passwordcheck = (EditText) findViewById(R.id.edit_password_check);
        address = (EditText) findViewById(R.id.edit_address);
        phone = (EditText) findViewById(R.id.edit_phone);
        Emergencyname = (EditText) findViewById(R.id.edit_emergency_name);
        Emergencyrelation = (EditText) findViewById(R.id.edit_emergency_relationship);
        Emergencyphone = (EditText) findViewById(R.id.edit_emergency_phone);
        submit = findViewById(R.id.submit);

        birthday.setInputType(InputType.TYPE_NULL); //不显示系统输入键盘</span>
        birthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    showDatePickerDialog();
                }
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePickerDialog();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_name = name.getText().toString().trim();
                String p_ID = ID.getText().toString().trim();
                String p_password = password.getText().toString().trim();
                String p_password_check = passwordcheck.getText().toString().trim();
                String p_address = address.getText().toString().trim();
                String p_phone = phone.getText().toString().trim();
                String p_emergencyname = Emergencyname.getText().toString().trim();
                String p_emergencyrelation = Emergencyrelation.getText().toString().trim();
                String p_emergencyphone = Emergencyphone.getText().toString().trim();

                if(p_password.equals(p_password_check))
                {
                    PostRegister(p_birthday, p_name, p_ID, p_password, p_password_check, p_address, p_phone, p_emergencyname, p_emergencyrelation, p_emergencyphone);
                }
                else
                    {
                        Toast.makeText(register.this, "二次輸入的密碼不一致!", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(register.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                birthday.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                p_birthday = year + "-" + (monthOfYear + 1)+"-"+dayOfMonth;
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }
    public void PostRegister(final String p_bithday, final String p_name, final String p_ID, final String p_password, final String p_password_check, final String p_address, final String p_phone, final String p_emergencyname, final String p_emergencyrelation, final String p_emergencyphone){

        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<Req> call = myAPIService.PostPatient(new Req(new fields(p_ID, p_name, "M", p_birthday, p_phone, p_emergencyname, p_emergencyphone, p_emergencyrelation, p_password)));
        call.enqueue(new Callback<Req>() {
            @Override
            public void onResponse(Call<Req> call, Response<Req> response) {
                Toast.makeText(register.this,"註冊成功!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Req> call, Throwable t) {
                Toast.makeText(register.this,"註冊失敗!",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
