package br.uece.appshub.cardapio;

import java.util.Date;

/**
 * Created by emerson on 02/11/15.
 */
public class CardapioMockDAO {
    public Cardapio loadByDate(Date data) {
        Cardapio c = new Cardapio();
        c.setData(data);
        c.create("Bife ao Molho", 0);
        c.create("Isca de Pernil Suíno com Pimentões", 4);
        c.create("Arroz, Feijão de  Corda com legumes, Farofa", 5);
        c.create("Ervilha,Repolho Branco Refogado com Uva Passas", 6);
        c.create("Laranja", 7);
        c.create("Risoto Vegetariano", 8);
        return c;
    }
}
