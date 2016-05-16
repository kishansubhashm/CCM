package com.ccm.android.ccm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class ActivitySignup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final Context mContext=this;
        final Activity mActivity=(Activity)this;
        final EditText mobile= (EditText) findViewById(R.id.mobile);
        final TextView login_here= (TextView) findViewById(R.id.login_here);
        final Button signup_btn= (Button) findViewById(R.id.signup_btn);

        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                        SessionHandler session= new SessionHandler(mContext,mActivity);
                        session.createLoginSession(mobile.getText().toString().trim());
                        gotoHome(mobile.getText().toString().trim(),

                                mobile.getText().toString().trim(),
                                mobile.getText().toString().trim(),
                                mobile.getText().toString().trim()
                        );
                        Toast.makeText(ActivitySignup.this, "successfully Logged in as ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoHome(String fname, String uname, String mobile, String pass){
        try {
            Splash.userBundle.add(new User(fname, "",uname,mobile,pass));
            Cache.writeObject(getApplicationContext(), getResources().getString(R.string.serialization_KEY), Splash.userBundle);
        } catch (IOException e) {
            Log.d("Cache", " read/write");
            e.printStackTrace();
        }

        Intent mIntent= new Intent(ActivitySignup.this,MainActivity.class);
        startActivity(mIntent);
        finish();
    }private void gotoLogin(){

        Intent mIntent= new Intent(ActivitySignup.this,ActivityLogin.class);
        startActivity(mIntent);
        finish();
    }
}
