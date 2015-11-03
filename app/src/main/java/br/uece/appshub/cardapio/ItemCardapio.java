package br.uece.appshub.cardapio;

/**
 * Created by emerson on 08/02/15.
 */
public class ItemCardapio {

    private int mId;
    private String mDescricao;
    private int mImagemId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getDescricao() {
        return mDescricao;
    }

    public void setDescricao(String descricao) {
        mDescricao = descricao;
    }

    public int getImagemId() {
        return mImagemId;
    }

    public void setImagemId(int id) {
        mImagemId = id;
    }
}
