package com.ccm.android.ccm;

/**
 * Created by KishanSubhashMiryala on 2/24/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class SessionHandler {
    // Shared Preferences
    static SharedPreferences pref;

    // Editor for Shared preferences
    static Editor editor;

    // Context
    //static Context _context;

    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "CCMprefName!";

    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_FNAME = "name";
    private static final String KEY_UNAME = "userName";
    private static final String KEY_USER_AUTH = "authToken";
    private static final String KEY_USER_ID = "uid";



    private static String regid;
    private static Context mContext;
    private static Activity mActivity;



    /**
     * Tag used on log messages.
     */

    public SessionHandler(Context context, Activity activity){
//        this._context = context;

        this.mContext=context;
        this.mActivity=activity;

        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public static void createLoginSession(String name){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_FNAME, name);

//        // Storing userID in pref
//        editor.putString(KEY_USER_ID, userID);
//
//        // Storing auth token in pref
//        editor.putString(KEY_USER_AUTH, auth_token);
//
//        editor.putString(KEY_UNAME,userName);

        // commit changes
        editor.commit();



    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, ActivityLogin.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            mContext.startActivity(i);
            return false;
        }else{
            return true;
        }

    }


    public static String getUserFullName(){
        return pref.getString(KEY_FNAME, null);
    }
    public static String getUserName(){
        return pref.getString(KEY_UNAME, null);
    }

    public static String getUserID(){
        return pref.getString(KEY_USER_ID, null);
    }

    public static String getUserAuth(){
        return pref.getString(KEY_USER_AUTH, null);
    }


    /**
     * Clear session details
     * */
    public static void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


        Bundle mBundle= new Bundle();
        mBundle.putBoolean("isSignOut", true);
        //
        // After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, Splash.class);
        i.putExtras(mBundle);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }




}