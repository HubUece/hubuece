package com.luna9.swconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SWConnection {

    public static final int SWConnectionResultStandard = 1;
    public static final int SWConnectionResultGeneric = 2;

    public static final String SWSendMethodGET = "GET";
    public static final String SWSendMethodPOST = "POST";

    private SWResult resultObject;
    private int resultType;
    private Exception systemError;
    private String authorizationHeaderValue;
    private String contentType;

    public String url;
    public String identifier;
    public String sendMethod;
    public HashMap<String,Object> variables;
    public int responseCode;

    private String encryptionKey;

    public SWConnectionDelegate delegate;

    public SWConnection(int resultType, String url, String identifier) {
        this.resultType = resultType;
        this.url = url;
        this.identifier = identifier;
    }

    public SWConnection(String url, String identifier) {
        this.url = url;
        this.identifier = identifier;
        this.resultType = SWConnectionResultStandard;
    }


    public void setAuthorizationHeader(String authorizationHeader) {
        authorizationHeaderValue = authorizationHeader;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }


    public void sendAndLoad() {
        String vars = "";

        if ( this.variables != null && sendMethod == SWSendMethodGET ) {
            int count = 0;

            Iterator<String> iterator = this.variables.keySet().iterator();
            while ( iterator.hasNext() ) {
                String key = iterator.next();
                Object value = this.variables.get(key);

                if ( count == 0 ) vars = vars.concat("?");
                else vars = vars.concat("&");

                String str = String.format( "%s=%s", key, value);
                vars = vars.concat(str);

                count++;
            }
        }

        String query = "";
        try {
            query = URLEncoder.encode(vars, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = this.url.concat(query);

        ConnectionTask task = new ConnectionTask();
        task.setVariables(variables);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    private HttpURLConnection connect(String urlArquivo, HashMap<String, Object> variables) throws IOException, JSONException {

        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setReadTimeout(60 * SEGUNDOS);
        conn.setConnectTimeout(30 * SEGUNDOS);
        conn.setRequestMethod(sendMethod);
        conn.setDoInput(true);
        conn.setDoOutput(false);

        if (authorizationHeaderValue != null)
            conn.setRequestProperty("Authorization", authorizationHeaderValue);

        if (this.sendMethod.equals("POST")) {
            String postData;

            if (this.contentType != null) {
                //Build the post params.
                Uri.Builder uriBuilder = new Uri.Builder();

                for (Map.Entry<String, Object> entry : variables.entrySet()) {
                    String param = entry.getKey();
                    String value = entry.getValue().toString();
                    uriBuilder.appendQueryParameter(param, value);
                }

                //We don't want the question mark at the beginning.
                postData = uriBuilder.build().toString().substring(1);

                conn.setRequestProperty("Content-Type", contentType);
            }
            else {
                //Build the JSON object.
                JSONObject jsonObject = new JSONObject(variables);
                postData = jsonObject.toString();

                conn.setRequestProperty("Content-Type", "text/json");
            }

            DataOutputStream printout;
            printout = new DataOutputStream(conn.getOutputStream());
            printout.writeBytes(postData);
            printout.flush();
            printout.close();
        }

        return conn;
    }

    public static boolean hasConnection(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        return ( netinfo != null && netinfo.isConnected() );
    }

    public static String bytesToString(InputStream is) throws IOException {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferOutput = new ByteArrayOutputStream();
        int bytesRead;
        while ( (bytesRead = is.read(buffer)) != -1) {
            bufferOutput.write(buffer, 0, bytesRead);
        }

        return new String( bufferOutput.toByteArray(), "UTF-8" );
    }


    public interface SWConnectionDelegate {
        void dataSentAndLoadedSuccessfully(SWConnection connection, SWResult result);
        void dataSentAndLoadFail(SWConnection connection, Exception systemError);
    }

    private class ConnectionTask extends AsyncTask<String, Void, String> {
        private HashMap<String, Object> variables;
        private Exception lastException;

        public void setVariables(HashMap<String, Object> variables) {
            this.variables = variables;
        }

        @Override
        protected String doInBackground(String... params) {
            /* Warning: Delegate methods should not be called from this method.
            Delegate methods should be called from the UI thread. */
            InputStream istream = null;
            String response = null;
            HttpURLConnection connection = null;
            try {

                connection = connect(params[0], variables);

                responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //Parse the response.
                    istream = connection.getInputStream();
                    response = bytesToString(istream);
                }

            } catch (Exception e) {
                e.printStackTrace();
                lastException = e;
            } finally {
                if (istream != null) {
                    try {
                        istream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (connection != null)
                    connection.disconnect();
            }
            
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            /* If an exception occurs in doInBackground the response will be null. */
            if (response == null) {
                /* The delegate method has to be called from the UI thread. */
                if (delegate != null)
                    delegate.dataSentAndLoadFail(SWConnection.this, lastException);
                return;
            }

            if (responseCode != HttpURLConnection.HTTP_OK) {
                if (delegate != null)
                    delegate.dataSentAndLoadFail(SWConnection.this, null);
                return;
            }

            try {
                if (resultType == SWConnectionResultStandard ) {
                    resultObject = new SWResult(new JSONObject(response));
                }
                else {
                    SWResult res = new SWResult();
                    res.isEncrypted = false;
                    res.isTrue = true;
                    res.title = "Perfect!";
                    res.message = "Works!";
                    res.resultCode = 1;
                    res.resultMessage = "Works!";
                    res.dataObject = response;

                    resultObject = res;
                }

                if (resultObject.isEncrypted) {

                    // see swframework documentation for more details
                }

                if (delegate != null ) {
                    delegate.dataSentAndLoadedSuccessfully(SWConnection.this, resultObject);
                }

            }
            catch(JSONException e){
                if (delegate != null )
                    delegate.dataSentAndLoadFail(SWConnection.this, e);
            }
        }
    }
}