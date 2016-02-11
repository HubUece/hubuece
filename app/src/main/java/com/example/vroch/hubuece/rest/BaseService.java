package com.example.vroch.hubuece.rest;

import android.util.Log;


import com.luna9.swconnection.SWConnection;
import com.luna9.swconnection.SWResult;

import java.util.HashMap;


public abstract class BaseService implements SWConnection.SWConnectionDelegate {
    public static final String TAG = "BaseService";

    protected static final String BASE_SERVICE_URL = "http://192.168.1.120:8080/HubUeceServelet/rest/appServelet/";

    public SWConnection createDefaultSWConnection(String url, String connectionId, HashMap<String, Object> variables) {
        SWConnection connection = new SWConnection(SWConnection.SWConnectionResultStandard, url, connectionId);

        connection.variables    = variables;
        connection.delegate     = this;
        connection.sendMethod   = SWConnection.SWSendMethodPOST;
        connection.setContentType("application/x-www-form-urlencoded");

        return connection;
    }


    @Override
    public void dataSentAndLoadFail(SWConnection connection, Exception systemError) {
        Log.d(getServiceTag(),"Error: "+systemError.toString());
    }

    @Override
    public void dataSentAndLoadedSuccessfully(SWConnection connection, SWResult result) {
        Log.d(getServiceTag(), result.resultCode + ": " + result.resultMessage);
    }

    public String getServiceTag() {
        return TAG;
    }
}