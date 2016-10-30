package com.alick.ntp_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alick on 2016/10/31.
 */

public class TimeAdapter extends BaseAdapter{
    private List<TimeBean> timeBeanList;

    public TimeAdapter(List<TimeBean> timeBeanList){
        this.timeBeanList=timeBeanList;
    }


    @Override
    public int getCount() {
        return timeBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return timeBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TimeBean timeBean = timeBeanList.get(position);
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time,parent,false);
        }
        TextView tv_serviceHost = ViewHolder.get(convertView, R.id.tv_serviceHost);
        TextView tv_serviceTime=ViewHolder.get(convertView,R.id.tv_serviceTime);
        TextView tv_localTime=ViewHolder.get(convertView,R.id.tv_localTime);
        TextView tv_differ=ViewHolder.get(convertView,R.id.tv_differ);

        tv_serviceHost.setText(timeBean.getServiceHost());
        if(timeBean.getServiceTime()==0){
            tv_serviceTime.setText("获取失败");
            tv_localTime.setText("---");
            tv_differ.setText("---");
        }else{
            tv_serviceTime.setText(TimeUtils.parseLongToString(timeBean.getServiceTime(),TimeUtils.format10));
            tv_localTime.setText(TimeUtils.parseLongToString(timeBean.getLocalTime(),TimeUtils.format10));
            tv_differ.setText(String.valueOf(timeBean.getServiceTime()-timeBean.getLocalTime()));
        }

        return convertView;
    }

}
