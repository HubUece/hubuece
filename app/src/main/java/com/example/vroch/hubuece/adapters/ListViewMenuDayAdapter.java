package com.example.vroch.hubuece.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.data.model.MenuItem;
import com.example.vroch.hubuece.data.model.WeekDay;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vroch on 14/12/2015.
 */
public class ListViewMenuDayAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<MenuItem> menuItems;

    public ListViewMenuDayAdapter(Context context,ArrayList<MenuItem> menuItems) {

        this.context = context;
        this.menuItems = menuItems;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.list_view_menu_day,parent,false);

        TextView txtItemName = (TextView) convertView.findViewById(R.id.txtMenuOptionDescription);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.img_menu_option);


        MenuItem item = menuItems.get(position);


        txtItemName.setText(item.name);
        imgView.setImageResource(item.image);


        return convertView;
    }
}
