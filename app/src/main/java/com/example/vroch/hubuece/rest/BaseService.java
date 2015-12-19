package com.example.vroch.hubuece.rest;

import android.util.Log;

import com.example.vroch.hubuece.framework.SWConnection;
import com.example.vroch.hubuece.framework.SWResult;

import java.util.HashMap;


public abstract class BaseService implements SWConnection.SWConnectionDelegate {
    public static final String TAG = "BaseService";

    protected static final String BASE_SERVICE_URL = "";

    public SWConnection createDefaultSWConnection(String url, String connectionId, HashMap<String, Object> variables) {
        SWConnection connection = new SWConnection(SWConnection.SWConnectionResultGeneric, url, connectionId);

        connection.variables    = variables;
        connection.delegate     = this;
        connection.sendMethod   = SWConnection.SWSendMethodPOST;
        connection.setContentType("application/x-www-form-urlencoded");

        return connection;
    }


    @Override
    public void dataSentAndLoadFail(SWConnection connection, Exception systemError) {

    }

    @Override
    public void dataSentAndLoadedSuccessfully(SWConnection connection, SWResult result) {
        Log.d(getServiceTag(), result.resultCode + ": " + result.resultMessage);
    }

    public String getServiceTag() {
        return TAG;
    }
}