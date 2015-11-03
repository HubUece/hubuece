package br.uece.appshub.cardapio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.uece.appshub.R;

/**
 * Created by emerson on 08/02/15.
 */
public class CardapioAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private final Cardapio mCardapio;

    public CardapioAdapter(Context context, Cardapio cardapio) {
        mCardapio = cardapio;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mCardapio.getItens().size();
    }

    @Override
    public Object getItem(int position) {
        return mCardapio.getItens().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_cardapio, null);
            ViewHolder holder = new ViewHolder();
            holder.txtDescricao = (TextView) convertView.findViewById(R.id.descricao);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        ItemCardapio item = (ItemCardapio) getItem(position);
        holder.txtDescricao.setText(item.getDescricao());
        return convertView;
    }

    static class ViewHolder {
        public TextView txtDescricao;
    }
}
