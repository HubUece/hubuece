package br.uece.appshub.cardapio;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Date;

import br.uece.appshub.R;

/**
 * Created by emerson on 02/11/15.
 * blah blah
 */
public class CardapioDetalheActivity extends AppCompatActivity {

    private ListView mLista;
    private CardapioMockDAO mCardapioDAO = new CardapioMockDAO();
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhecardapio);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Date data = (Date) getIntent().getSerializableExtra("item");

        mLista = (ListView) findViewById(R.id.lista);
        Cardapio cardapio = mCardapioDAO.loadByDate(data);
        mAdapter = new ItemCardapioAdapter(this, cardapio);
        mLista.setAdapter(mAdapter);
    }

}
