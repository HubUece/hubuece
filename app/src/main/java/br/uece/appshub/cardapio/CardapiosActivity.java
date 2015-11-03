package br.uece.appshub.cardapio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;

import br.uece.appshub.R;

/**
 * Created by emerson on 02/11/15.
 */
public class CardapiosActivity extends AppCompatActivity {

    private AdapterView.OnItemClickListener mAoClicarItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Date data = (Date) mListView.getItemAtPosition(position);
            Intent it = new Intent(CardapiosActivity.this, CardapioDetalheActivity.class);
            it.putExtra("item", data);
            CardapiosActivity.this.startActivity(it);
        }
    };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapios);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mListView = (ListView) findViewById(R.id.lista);
        mListView.setAdapter(new SemanaListAdapter(this));
        mListView.setOnItemClickListener(mAoClicarItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_cardapios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sobre:
                return true;

            case R.id.action_sincronizar:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
