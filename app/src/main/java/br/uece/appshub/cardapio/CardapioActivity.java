package br.uece.appshub.cardapio;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.Date;


public class CardapioActivity extends ListActivity {

    private CardapioDAO cardapioDAO = new CardapioDAO();
    private CardapioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Cardapio cardapio = cardapioDAO.loadByDate(new Date());
                    adapter = new CardapioAdapter(CardapioActivity.this, cardapio);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CardapioActivity.this.setListAdapter(adapter);
                        }
                    });
                } catch (Exception e) {
                    Log.e("ERRO", e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        });
        t.start();
    }

}