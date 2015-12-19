package com.example.vroch.hubuece.activities;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.adapters.ListViewMenuDayAdapter;
import com.example.vroch.hubuece.data.model.WeekDay;
import com.example.vroch.hubuece.dialogs.RateDialog;

import java.io.Serializable;

public class WeekDayMenuActivity extends AppCompatActivity implements RateDialog.OnRateDialogFinishedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day_menu);

        WeekDay itemClicked = (WeekDay) getIntent().getSerializableExtra("itemClicked");

        ListViewMenuDayAdapter listViewMenuDayAdapter = new ListViewMenuDayAdapter(this,itemClicked.menu);

        ListView listView = (ListView) findViewById(R.id.list_view_menu_day);
        listView.setAdapter(listViewMenuDayAdapter);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(itemClicked.dayName+" "+itemClicked.date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_day,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                break;

            case R.id.item_rate:

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");

                RateDialog rateDialog = new RateDialog();
                rateDialog.setOnRateDialogFinishedListener(this);
                rateDialog.show(ft,"dialog");

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRateDialogSave(String comment, float rating) {

        Log.i("Comment: ",comment);
    }
}
