package com.example.vroch.hubuece.data.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import java.util.Locale;


public class AppPreferences {

    private static final String PREFERENCES_FILE = "preferences";

    private static final String USER_EMAIL = "user_email";
    private static final String USER_NAME = "user_name";
    private static final String MAP_TABLE_IS_UPDATED = "map_table_is_updated";

    private static AppPreferences sInstance;
    private SharedPreferences sharedPrefs;

    public static synchronized AppPreferences getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new AppPreferences(context);
        }

        return sInstance;
    }

    private AppPreferences(Context context) {
        sharedPrefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getDeviceLanguage(){

        String currentLanguageString = Locale.getDefault().getLanguage();

        if (!currentLanguageString.isEmpty() && (currentLanguageString.equals("pt") || currentLanguageString.equals("pt_BR"))){
            return "pt";
        }else{
            return "en";
        }

    }

    public String getUserEmail() {
        return sharedPrefs.getString(USER_EMAIL, "");
    }

    public String getUserName() {
        return sharedPrefs.getString(USER_NAME, "");
    }
    public boolean getMapTableIsUpdated() {

        return sharedPrefs.getBoolean(MAP_TABLE_IS_UPDATED, false);
    }



    public void setUserEmail(String email) {
        sharedPrefs.edit().putString(USER_EMAIL, email).apply();
    }

    public void setUserName(String name) {
        sharedPrefs.edit().putString(USER_NAME, name).apply();
    }
    public void setMapTableIsUpdated(boolean isUpdated) {
        sharedPrefs.edit().putBoolean(MAP_TABLE_IS_UPDATED, isUpdated).apply();
    }

    public void clearUserData() {
        sharedPrefs.edit().clear().apply();
    }
}
