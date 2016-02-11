package com.example.vroch.hubuece.rest;

import android.content.Context;
import android.util.Log;

import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.data.dao.MapLocationDao;
import com.example.vroch.hubuece.data.model.MapLocation;
import com.example.vroch.hubuece.data.provider.AppPreferences;
import com.example.vroch.hubuece.data.provider.DbContract;
import com.luna9.swconnection.SWConnection;
import com.luna9.swconnection.SWResult;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MapLocationsService extends BaseService {

    public static final String TAG = "MapLocationService";

    public static final String SERVICE_NAME = "getMapLocations";

    private static final String SERVICE_URL = BASE_SERVICE_URL + SERVICE_NAME;

    private Context mContext;
    private OnMapLocationsServiceFinishedListener mServiceListener;
    private AppPreferences mPrefs;

    public MapLocationsService(Context context) {
        super();
        mContext = context.getApplicationContext();
        mPrefs = AppPreferences.getInstance(mContext);
    }

    public void setOnAuthenticationServiceFinishedListener(OnMapLocationsServiceFinishedListener l) {
        this.mServiceListener = l;
    }

    public void getMapLocations() {

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("parameter",1);

        createDefaultSWConnection(SERVICE_URL, "", variables).sendAndLoad();
    }

    @Override
    public void dataSentAndLoadedSuccessfully(SWConnection connection, SWResult result) {
        super.dataSentAndLoadedSuccessfully(connection, result);

        saveMapLocations( result.dataObject.toString());

        if (mServiceListener != null) {
            mServiceListener.onMapLocationsServiceSuccessful();
        }
    }

    @Override
    public void dataSentAndLoadFail(SWConnection connection, Exception systemError) {
        super.dataSentAndLoadFail(connection, systemError);

        String title = mContext.getString(R.string.dlg_network_error_title);
        String message = mContext.getString(R.string.dlg_network_error_message);

        if (mServiceListener != null) {
            mServiceListener.onMapLocationsServiceFailed(title, message, true);
        }

    }

    @Override
    public String getServiceTag() {
        return TAG;
    }

    private void saveMapLocations(String dataObject) {



        try {
            JSONObject jsonObject = new JSONObject(dataObject);
            JSONArray objectArray = jsonObject.getJSONArray("objectArray");

            int jsonArrayLength = objectArray.length();

            MapLocationDao dao = new MapLocationDao(mContext);

            for (int i = 0; i < jsonArrayLength; i++ ) {

                MapLocation newLocation = new MapLocation(objectArray.getJSONObject(i));
                dao.save(newLocation);

            }

            mPrefs.setMapTableIsUpdated(true);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public interface OnMapLocationsServiceFinishedListener {
        void onMapLocationsServiceSuccessful();
        void onMapLocationsServiceFailed(String title, String message, boolean networkError);
    }
}
