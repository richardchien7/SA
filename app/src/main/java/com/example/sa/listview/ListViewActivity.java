package com.example.sa.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sa.R;

public class ListViewActivity extends AppCompatActivity {
    private ListView mlv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mlv1 = (ListView) findViewById(R.id.lv_1);
        mlv1.setAdapter(new MyListAdapter(ListViewActivity.this));
        mlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"pos:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        mlv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id){
                Toast.makeText(ListViewActivity.this,"長按"+position,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
