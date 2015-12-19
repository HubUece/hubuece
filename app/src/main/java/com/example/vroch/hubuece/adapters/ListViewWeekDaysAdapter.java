package com.example.vroch.hubuece.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.data.model.WeekDay;

import java.util.ArrayList;

/**
 * Created by vroch on 12/12/2015.
 */
public class ListViewWeekDaysAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WeekDay> weekDaysList;

    public ListViewWeekDaysAdapter(Context context,ArrayList<WeekDay> list) {

        this.context = context;
        this.weekDaysList = list;

    }

    @Override
    public int getCount() {
        return weekDaysList.size();
    }

    @Override
    public Object getItem(int position) {
        return weekDaysList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.list_view_week_days,parent,false);

        TextView txtWeekDay = (TextView) convertView.findViewById(R.id.txtWeekDay);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);

        WeekDay weekDay = weekDaysList.get(position);

        txtWeekDay.setText(weekDay.dayName);
        txtDate.setText(weekDay.date);

        return convertView;
    }
}
