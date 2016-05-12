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
        final EditText firstname= (EditText) findViewById(R.id.firstname);
        final EditText lastname= (EditText) findViewById(R.id.lastname);
        final EditText username= (EditText) findViewById(R.id.username);
        final EditText mobile= (EditText) findViewById(R.id.mobile);
        final EditText secret= (EditText) findViewById(R.id.secret);
        final TextView login_here= (TextView) findViewById(R.id.login_here);
        final EditText re_secret= (EditText) findViewById(R.id.re_secret);
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
                if(secret.getText().toString().trim().length()!=0&&re_secret.getText().toString().trim().length()!=0){
                    if(secret.getText().toString().trim().equals(re_secret.getText().toString().trim())){
                        SessionHandler session= new SessionHandler(mContext,mActivity);
                        session.createLoginSession(username.getText().toString().trim());
                        gotoHome(firstname.getText().toString().trim(),
                                lastname.getText().toString().trim(),
                                username.getText().toString().trim(),
                                mobile.getText().toString().trim(),
                                mobile.getText().toString().trim()
                        );
                        Toast.makeText(ActivitySignup.this, "successfully Logged in as "+username.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext,"Entered passwords are not same\nPlease Re-enter Passwords and try again",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(mContext,"Please fill all fields and try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void gotoHome(String fname, String lname, String uname, String mobile, String pass){
        try {
            Splash.userBundle.add(new User(fname, lname,uname,mobile,pass));
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
