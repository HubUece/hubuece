package br.uece.appshub.cardapio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.uece.appshub.R;

/**
 * Created by emerson on 02/11/15.
 */
public class ItemCardapioAdapter extends BaseAdapter {

    private final List<ItemCardapio> mItemCardapios;
    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    private final int[] mImagens = {
            R.drawable.carne1,
            R.drawable.carne2,
            R.drawable.carne3,
            R.drawable.carne4,
            R.drawable.carne5,
            R.drawable.graos,
            R.drawable.repolho,
            R.drawable.laranja,
            R.drawable.vegetariano
    };

    public ItemCardapioAdapter(Context context, Cardapio cardapio) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemCardapios = cardapio.getItens();
    }

    @Override
    public int getCount() {
        return mItemCardapios.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemCardapios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItemCardapios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemCardapio item = mItemCardapios.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_cardapio, null);
            ViewHolder holder = new ViewHolder();
            holder.txtDescricao = (TextView) convertView.findViewById(R.id.descricao);
            holder.imgImagem = (ImageView) convertView.findViewById(R.id.imagem);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txtDescricao.setText(item.getDescricao());
        holder.imgImagem.setImageResource(mImagens[item.getImagemId()]);
        return convertView;
    }

    static class ViewHolder {
        public TextView txtDescricao;
        public ImageView imgImagem;
    }

}
