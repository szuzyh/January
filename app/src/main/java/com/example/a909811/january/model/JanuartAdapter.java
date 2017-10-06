package com.example.a909811.january.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.a909811.january.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 909811 on 2017/10/6.
 */
public class JanuartAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public JanuartAdapter(ArrayList<HashMap<String, Object>> data, Context context) {
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        January january = null;
        if(convertView == null){
            january = new January();
            convertView = layoutInflater.inflate(R.layout.january_view,null);
            january.msg_tv = convertView.findViewById(R.id.msg_tv);
            january.date_tv = convertView.findViewById(R.id.date_tv);
            january.relativeLayout = convertView.findViewById(R.id.layout_view);
            convertView.setTag(january);
        }else {
            january = (January) convertView.getTag();
        }
        january.msg_tv.setText((String)data.get(position).get("message"));
        january.date_tv.setText((String)data.get(position).get("date"));
        january.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //整个View的点击事件
            }
        });
        return convertView;
    }
}
