package com.example.sa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MyListAdapter extends BaseAdapter {
    private sch schh;
    private String[][] data;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int index;
    private Switch sw;
    private Button bb;

    MyListAdapter(String[][] data, sch context, int count) {
        this.index = count;
        this.data = data;
        this.schh = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return index;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView title, doctor, period, date, num, process;
        public Button bb;
        public Switch sw;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.activity_show_reservation, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.r1);
            holder.doctor = (TextView) convertView.findViewById(R.id.r1_doctor);
            holder.period = (TextView) convertView.findViewById(R.id.r1_time);
            holder.date = (TextView) convertView.findViewById(R.id.r1_date);
            holder.num = (TextView) convertView.findViewById(R.id.r1_number);
            holder.process = (TextView) convertView.findViewById(R.id.r1_schedule);
            holder.sw = (Switch) convertView.findViewById(R.id.r1_switch);
            holder.bb = (Button) convertView.findViewById(R.id.r1_cancel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.doctor.setText(data[position][1]);
        holder.period.setText(data[position][2]);
        holder.date.setText(data[position][3]);
        holder.num.setText(data[position][4]);
        holder.process.setText(data[position][5]);
        holder.title.setText(data[position][6]);
        holder.bb.setOnClickListener(new ItemButton_Click(this.schh,position));
        holder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    schh.show(true);
                } else {
                    schh.show(false);
                }


            }
        });

        return convertView;


    }

    class ItemButton_Click implements View.OnClickListener {
        private int position;
        private sch mainActivity;

        ItemButton_Click(sch context, int pos) {
            this.mainActivity = context;
            position = pos;
        }

        public void onClick(View v) {

            this.mainActivity.myDialog(data[position][0]);
        }
    }


}