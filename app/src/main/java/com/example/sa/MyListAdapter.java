package com.example.sa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class MyListAdapter extends BaseAdapter {
    private String[][] data;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private  int index ;

    MyListAdapter(String[][] data,Context context,int count) {
        this.index = count;
        this.data = data;
        this.mContext = context;
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
        public ImageView imageView;
        public TextView title,doctor, period, date,num,process;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.activity_schh, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.r1);
            holder.doctor= (TextView) convertView.findViewById(R.id.r1_doctor);
            holder.period = (TextView) convertView.findViewById(R.id.r1_time);
            holder.date = (TextView) convertView.findViewById(R.id.r1_date);
            holder.num = (TextView) convertView.findViewById(R.id.r1_number);
            holder.process = (TextView) convertView.findViewById(R.id.r1_schedule);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

            holder.doctor.setText(data[position][0]);
            holder.period.setText(data[position][1]);
            holder.date.setText(data[position][2]);
            holder.num.setText(data[position][3]);
            holder.process.setText(data[position][4]);
            holder.title.setText(data.length+ "");
            return convertView;


    }
}
