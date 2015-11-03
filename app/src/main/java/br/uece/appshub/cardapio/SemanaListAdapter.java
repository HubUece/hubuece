package br.uece.appshub.cardapio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.uece.appshub.R;

/**
 * Created by emerson on 02/11/15.
 */
public class SemanaListAdapter extends BaseAdapter {

    private static final String FORMATO_DATA = "dd-MM-yyyy";
    private final Context mContext;
    private final SimpleDateFormat mFormatter = new SimpleDateFormat(FORMATO_DATA);
    private final LayoutInflater mLayoutInflater;
    private List<Date> mDatas = new ArrayList<>();

    public SemanaListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        prencherSemana();
    }

    private void prencherSemana() {
        Date hoje = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(hoje);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mDatas.add(c.getTime());
        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        mDatas.add(c.getTime());
        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        mDatas.add(c.getTime());
        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        mDatas.add(c.getTime());
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        mDatas.add(c.getTime());
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date d = (Date) getItem(position);
        Holder h = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.listitem_cardapio, null);
            h = new Holder();
            h.txtTitulo = (TextView) convertView.findViewById(R.id.titulo);
            convertView.setTag(h);
        } else {
            h = (Holder) convertView.getTag();
        }
        h.txtTitulo.setText(mFormatter.format(d));
        return convertView;
    }

    private class Holder {
        public TextView txtTitulo;
    }
}
