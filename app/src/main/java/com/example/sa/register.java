package com.example.sa;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class register extends AppCompatActivity {
    private EditText birthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        birthday = (EditText) findViewById(R.id.edit_birthday);

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

    }
    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog () {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(register.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                birthday.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }


}
