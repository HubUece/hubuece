package br.uece.appshub.cardapio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by emerson on 08/02/15.
 */
public class Cardapio {

    private int mId;
    private Date mDate;
    private List<ItemCardapio> mItens = new ArrayList<ItemCardapio>();
    private String mObservacoes;

    public int getId() {
        return mId;
    }
    public void setId(int id) {
        mId = id;
    }

    public Date getData() {
        return mDate;
    }
    public void setData(Date data) {
        mDate = data;
    }

    public List<ItemCardapio> getItens() {
        return mItens;
    }
    public void addItem(ItemCardapio item) {
        mItens.add(item);
    }
    public ItemCardapio create(String descricao, int id) {
        ItemCardapio aux = new ItemCardapio();
        aux.setDescricao(descricao);
        aux.setImagemId(id);
        mItens.add(aux);
        return aux;
    }

    public void setObservacoes(String observacoes) {
        mObservacoes = observacoes;
    }

    public String getObservacoes() {
        return mObservacoes;
    }

}
