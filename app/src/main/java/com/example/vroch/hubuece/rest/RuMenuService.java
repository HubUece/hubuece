package com.example.vroch.hubuece.rest;


import android.content.Context;

import com.example.vroch.hubuece.data.provider.AppPreferences;
import com.luna9.swconnection.SWConnection;
import com.luna9.swconnection.SWResult;


import java.util.HashMap;

/**
 * Created by vroch on 06/01/2016.
 */
public class RuMenuService extends BaseService {


    public static final String TAG = "RuMenuService";

    public static final String SERVICE_NAME = "";

    private static final String SERVICE_URL = BASE_SERVICE_URL + SERVICE_NAME;

    private Context mContext;
    private OnRuMenuServiceFinishedListener mServiceListener;
    private AppPreferences mPrefs;

    public RuMenuService(Context context) {

        this.mContext = context.getApplicationContext();
        mPrefs = AppPreferences.getInstance(mContext);

    }


    public void setOnRuMenuServiceFinishedListener(OnRuMenuServiceFinishedListener l) {
        this.mServiceListener = l;
    }


    public void getRuMenu() {

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("parameter",1);

        createDefaultSWConnection(SERVICE_URL, "", variables).sendAndLoad();

    }

    @Override
    public void dataSentAndLoadedSuccessfully(SWConnection connection, SWResult result) {
        super.dataSentAndLoadedSuccessfully(connection, result);
    }

    @Override
    public void dataSentAndLoadFail(SWConnection connection, Exception systemError) {
        super.dataSentAndLoadFail(connection, systemError);
    }

    public interface OnRuMenuServiceFinishedListener {
        void onRuMenuServiceSuccessful();
        void onRuMenuServiceFailed(String title, String message, boolean networkError);
    }
}
