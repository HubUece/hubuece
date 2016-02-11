package com.luna9.swconnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SWResult {

    public boolean isTrue;
    public boolean isEncrypted;
    public String title;
    public String message;
    public String resultMessage;
    public int resultCode;
    public Object dataObject;

    public SWResult(boolean isTrue, String title, String message, String resultMessage, int resultCode, JSONObject dataObject) {
        this.isTrue = isTrue;
        this.title = title;
        this.message = message;
        this.resultMessage = resultMessage;
        this.resultCode = resultCode;
        this.dataObject = dataObject;
    }

    public SWResult() {

    }

    public SWResult(JSONObject data) throws JSONException {
        //Data will be null when a timeout occurs.
        if (data != null) {

            this.isEncrypted = data.getBoolean("isEncrypted");
            this.isTrue = data.getBoolean("isTrue");
            this.title = data.getString("title");
            this.message = data.getString("message");
            //this.resultCode = data.getInt("resultCode");
            this.resultMessage = data.getString("resultMessage");
            try {
                this.dataObject = data.get("dataObject");
            } catch (Exception e) {
                HashMap<String, String> variables = new HashMap<>();
                variables.put("invalidFormat", "The server sent an invalid dataObject.");
                this.dataObject = new JSONObject(variables);
            }

        }

    }
}
