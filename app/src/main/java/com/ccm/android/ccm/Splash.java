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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext=this;
        Activity mActivity=(Activity)this;
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
        Intent mIntent= new Intent(Splash.this,ActivityLoginOptions.class);
        startActivity(mIntent);
        finish();
    }


    private void gotoHome(){
        Intent mIntent= new Intent(Splash.this,MainActivity.class);
        startActivity(mIntent);
        finish();
    }
}
