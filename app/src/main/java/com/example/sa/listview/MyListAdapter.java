package com.example.sa.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sa.R;

public class MyListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    MyListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView tvRoom, tvDoctor, tvTime, tvDate, tvNumber, tvSchedule;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
//            holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvRoom = (TextView) convertView.findViewById(R.id.r1);
            holder.tvDoctor = (TextView) convertView.findViewById(R.id.r1_doctor);
            holder.tvTime = (TextView) convertView.findViewById(R.id.r1_time);
            holder.tvDate = (TextView) convertView.findViewById(R.id.r1_date);
            holder.tvNumber = (TextView) convertView.findViewById(R.id.r1_number);
            holder.tvSchedule = (TextView) convertView.findViewById(R.id.r1_schedule);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvRoom.setText("87診間");
        holder.tvDoctor.setText("白爛貓");
        holder.tvTime.setText("清晨");
        holder.tvDate.setText("2019-03-30");
        holder.tvNumber.setText("87");
        holder.tvSchedule.setText("9487");
        //Glide.with(mContext).load("https://pic.pimg.tw/heartisdie/1485176139-3346578719_b.jpg").into(holder.imageView);
        return convertView;
    }
}
