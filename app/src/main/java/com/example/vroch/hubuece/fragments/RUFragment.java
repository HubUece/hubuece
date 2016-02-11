package com.example.vroch.hubuece.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.activities.WeekDayMenuActivity;
import com.example.vroch.hubuece.adapters.ListViewWeekDaysAdapter;
import com.example.vroch.hubuece.data.model.MenuItem;
import com.example.vroch.hubuece.data.model.WeekDay;
import com.example.vroch.hubuece.rest.RuMenuService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vroch on 12/12/2015.
 */
public class RUFragment extends Fragment implements RuMenuService.OnRuMenuServiceFinishedListener {

    private ProgressDialog mProgressDialog;
    private List<WeekDay> mList;

    public static RUFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RUFragment fragment = new RUFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ru,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<WeekDay> listWeekDays = new ArrayList<>();

        ArrayList<MenuItem> jsonObjects = new ArrayList<>();

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(this.getResources().getString(R.string.waiting));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);


        MenuItem meet = new MenuItem("Carne",R.drawable.ic_menu_ru);
        MenuItem rice = new MenuItem("Arroz",R.drawable.ic_menu_ru);
        MenuItem bean = new MenuItem("Feijão",R.drawable.ic_menu_ru);
        MenuItem salad = new MenuItem("Salada",R.drawable.ic_menu_ru);
        MenuItem orange = new MenuItem("laranja",R.drawable.ic_menu_ru);

        jsonObjects.add(meet);
        jsonObjects.add(rice);
        jsonObjects.add(bean);
        jsonObjects.add(salad);
        jsonObjects.add(orange);

        listWeekDays.add(new WeekDay("Segunda-Feira","(14/12)",jsonObjects));
        listWeekDays.add(new WeekDay("Terça-Feira","(15/12)",jsonObjects));
        listWeekDays.add(new WeekDay("Quarta-Feira","(16/12)",jsonObjects));
        listWeekDays.add(new WeekDay("Quinta-Feira","(17/12)",jsonObjects));
        listWeekDays.add(new WeekDay("Sexta-Feira","(18/12)",jsonObjects));

        ListViewWeekDaysAdapter listViewAdapter = new ListViewWeekDaysAdapter(getActivity(),listWeekDays);

        ListView listVieWeekDays = (ListView) getView().findViewById(R.id.list_view_week_days);
        listVieWeekDays.setAdapter(listViewAdapter);

        listVieWeekDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                WeekDay clickedItem = (WeekDay) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), WeekDayMenuActivity.class);
                intent.putExtra("itemClicked", clickedItem);
                startActivity(intent);

            }
        });

        getRuMenuFromServer();

    }

    private void getRuMenuFromServer() {

    }

    @Override
    public void onRuMenuServiceSuccessful() {

        hideProgressDialog();
    }

    @Override
    public void onRuMenuServiceFailed(String title, String message, boolean networkError) {
        hideProgressDialog();
    }



    private void showProgressDialog() {
        mProgressDialog.show();

    }

    private void hideProgressDialog() {
        mProgressDialog.hide();

    }


}
