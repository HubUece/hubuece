package br.uece.appshub.mapa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import br.uece.appshub.R;

public class TelaInfo extends ListActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // if extending Activity
        //setContentView(R.layout.activity_main);

        // 1. pass context and data to the custom adapter
        LugarCursorAdapter adapter = new LugarCursorAdapter(this, generateData());

        // if extending Activity 2. Get ListView from activity_main.xml
        //ListView listView = (ListView) findViewById(R.id.listview);

        // 3. setListAdapter
        //listView.setAdapter(adapter); if extending Activity
        setListAdapter(adapter);
    }


    private ArrayList<ModeloTelaInfo> generateData(){
        ArrayList<ModeloTelaInfo> models = new ArrayList<ModeloTelaInfo>();
        Intent i = getIntent();
        Lugar lugar = (Lugar) i.getSerializableExtra("lugar");
        models.add(new ModeloTelaInfo(R.mipmap.ic_help_black_24dp,lugar.getNome(),lugar.getDescricao()));
        models.add(new ModeloTelaInfo(R.mipmap.ic_call_black_24dp, "Contato", Long.toString(lugar.getContato())));

        return models;
    }

}
