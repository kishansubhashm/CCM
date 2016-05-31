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

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class ActivitySignup extends AppCompatActivity {

    private CustomRequest signupJsonReq;
    private Context mContext;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.mContext=this;
        this. mActivity=(Activity)this;
        final EditText signup_phone= (EditText) findViewById(R.id.signup_phone);
        final EditText signup_pan= (EditText) findViewById(R.id.signup_pan);
        final EditText signup_secret= (EditText) findViewById(R.id.signup_secret);

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

                String username = signup_phone.getText().toString().trim();
                String pan = signup_pan.getText().toString().trim();
                String secret = signup_secret.getText().toString().trim();
                String signupURL = "http://ccm.viveninfomedia.com/verifyuser";

                HashMap<String, String> signupParams = new HashMap<String, String>();
                signupParams.put("id", username);
                signupParams.put("pan", pan);
                signupParams.put("password", secret);

                signupJsonReq = new CustomRequest(Request.Method.POST, signupURL, signupParams, loginSuccessListener(), loginErrorListener());
                Volley.newRequestQueue(mContext).add(signupJsonReq);
            }
        });
    }


    private Response.Listener<JSONObject> loginSuccessListener() {
        final TextView login_name= (TextView)findViewById(R.id.login_name);
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject singupResponse) {
                Log.d("response", singupResponse.toString());
                try {
                    if (singupResponse.getInt("status") == 0){
                        Toast.makeText(mContext, "Could not find your account. Please check your credentials.", Toast.LENGTH_SHORT).show();
                    }
                    else if (singupResponse.getInt("status") == 1){
                        SessionHandler session= new SessionHandler(mContext,mActivity);
                        session.createLoginSession(login_name.getText().toString().trim());
                        gotoHome();
                        Toast.makeText(ActivitySignup.this, "You have been successfully verified and registered.", Toast.LENGTH_SHORT).show();
                    }

                }catch(Exception e){
                    Toast.makeText(mContext, "Server error. Please contact admin.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    private Response.ErrorListener loginErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NoConnectionError) {
                    Toast.makeText(mContext, "Could not connect to Internet. Check connection.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Server error. Please contact admin.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void gotoHome(){
        Intent mIntent= new Intent(ActivitySignup.this,MainActivity.class);
        startActivity(mIntent);
        finish();
    }

    private void gotoLogin(){
        Intent mIntent= new Intent(ActivitySignup.this,ActivityLogin.class);
        startActivity(mIntent);
        finish();
    }
}
