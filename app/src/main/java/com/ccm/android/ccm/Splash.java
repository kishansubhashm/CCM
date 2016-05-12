package com.ccm.android.ccm;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class Splash extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    public static List<User> userBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext=this;
        Activity mActivity=(Activity)this;
        userBundle=new ArrayList<User>();
//        readLocalData();
        final SessionHandler session= new SessionHandler(mContext,mActivity);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (session.isLoggedIn()) {
                        Log.d("Login", "NOT required");
                        gotoHome();
                    } else {
                        Log.d("Login", "required");
                        session.logoutUser();
                        gotoSignin();
                    }
                }
            }, 3000);
    }

    private void gotoSignin() {
        Intent mIntent= new Intent(Splash.this,ActivityLogin.class);
        startActivity(mIntent);
        finish();
    }

    private void readLocalData(){
        try {
            Log.d("CacheList", "reading");

            List<userList> userList = (List<userList>)
                    Cache.readObject(getApplicationContext(), getResources().getString( R.string.serialization_KEY));
//            userBundle = new ArrayList<BluetoothDevice>(userList.get(0).getBTDeviceList());

            List<User> cachedBTList = (List<User>)
                    Cache.readObject(mContext, mContext.getResources().getString( R.string.serialization_KEY));
            userBundle.addAll(cachedBTList);
            for (int i=0;i<userBundle.size();++i){
                Log.d("user name",userBundle.get(i).getuName());
                Log.d("password",userBundle.get(i).getPassword());
                Log.d("mobile", userBundle.get(i).getMobile());

            }
            Log.d("In readCacheList", "" + userBundle.size());
        }catch (ClassNotFoundException e) {
            Log.d("Cache", " Class Not Found");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void gotoHome(){
        Intent mIntent= new Intent(Splash.this,MainActivity.class);
        startActivity(mIntent);
        finish();
    }
}
