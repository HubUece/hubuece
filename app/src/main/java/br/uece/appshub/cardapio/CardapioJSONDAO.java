package br.uece.appshub.cardapio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by emerson on 08/02/15.
 */
public class CardapioJSONDAO {

    private static final String UECE_JSON_URI = "http://jeri.larces.uece.br/lotus/cardapio/uece.json";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

    public Cardapio loadByDate(Date dia) throws IOException, ParseException, JSONException {
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpResponse response = httpClient.execute(new HttpGet(UECE_JSON_URI));
//        StatusLine statusLine = response.getStatusLine();
//        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            response.getEntity().writeTo(out);
//            byte[] responseBytes = out.toByteArray();
//            out.close();
//            List<Cardapio> cardapios = parseCardapios(new JSONObject(new String(responseBytes)));
//            return cardapios.get(0);
//        } else {
//            response.getEntity().getContent().close();
//            throw new IOException(statusLine.getReasonPhrase());
//        }
        return null;
    }

    private List<Cardapio> parseCardapios(JSONObject json) throws JSONException, ParseException {
        List<Cardapio> cardapios = new ArrayList<>();
//        Iterator<String> keys = json.keys();
//        while(keys.hasNext()) {
//            String dia = keys.next();
//            Cardapio cardapio = new Cardapio(formatter.parse(dia));
//            JSONArray itens = json.getJSONArray(dia);
//            for (int i = 0, n = itens.length(); i < n; i++) {
//                cardapio.getItens().add(new ItemCardapio(itens.getString(i)));
//            }
//            cardapios.add(cardapio);
//        }
        return cardapios;
    }
}
