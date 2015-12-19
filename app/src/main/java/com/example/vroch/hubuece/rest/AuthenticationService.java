package com.example.vroch.hubuece.rest;

import android.content.Context;

import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.framework.SWConnection;
import com.example.vroch.hubuece.framework.SWResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class AuthenticationService extends BaseService {
    public static final String TAG = "AuthenticationService";

    public static final String SERVICE_NAME = "login";

    private static final String SERVICE_URL = BASE_SERVICE_URL + SERVICE_NAME;

    private Context mContext;
    private OnAuthenticationServiceFinishedListener mServiceListener;
    //private AppPreferences mPrefs;

    public AuthenticationService(Context context) {
        super();
        mContext = context.getApplicationContext();
       // mPrefs = AppPreferences.getInstance(mContext);
    }

    public void setOnAuthenticationServiceFinishedListener(OnAuthenticationServiceFinishedListener l) {
        this.mServiceListener = l;
    }

    public void performAuth(String user, String password, OnAuthenticationServiceFinishedListener listener) {
        this.mServiceListener = listener;

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("usuario", user);
        variables.put("senha", password);

        createDefaultSWConnection(SERVICE_URL, "", variables).sendAndLoad();
    }

    @Override
    public void dataSentAndLoadedSuccessfully(SWConnection connection, SWResult result) {
        super.dataSentAndLoadedSuccessfully(connection, result);

        setUserInfo((String) result.dataObject);

        if (mServiceListener != null) {
            mServiceListener.onAuthenticationServiceSuccessful();
        }
    }

    @Override
    public void dataSentAndLoadFail(SWConnection connection, Exception systemError) {
        super.dataSentAndLoadFail(connection, systemError);

        String title;
        String message;

        if (connection.responseCode == 401) {
            //Authentication error.
            title = "Atenção";
            message = "Usuário ou senha incorretos.";
        }
        else {
            //Server error or any other error related to the server.
            title = mContext.getString(R.string.dlg_network_error_title);
            message = mContext.getString(R.string.dlg_network_error_message);
        }

        if (mServiceListener != null) {
            mServiceListener.onAuthenticationServiceFailed(title, message, true);
        }

    }

    @Override
    public String getServiceTag() {
        return TAG;
    }

    private void setUserInfo(String dataObject) {

        try {
            JSONObject jsonObject = new JSONObject(dataObject);

            String userName = jsonObject.getString("usuario");
            String password = jsonObject.getString("senha");

//            mPrefs.setUserName(userName);
//            mPrefs.setUserPassword(password);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public interface OnAuthenticationServiceFinishedListener {
        void onAuthenticationServiceSuccessful();
        void onAuthenticationServiceFailed(String title, String message, boolean networkError);
    }
}
