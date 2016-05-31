package com.ccm.android.ccm;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import org.json.JSONObject;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class ActivityLogin extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private CustomRequest loginJsonReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext=this;
        mActivity= (Activity)this;
        final TextView login_name= (TextView)findViewById(R.id.login_name);
        final TextView login_secret=(TextView)findViewById(R.id.login_secret);
        final TextView register=(TextView)findViewById(R.id.register);
        final String Destination = getIntent().getExtras().getString("Destination");


        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gotoSignUp();
            }
        });
        Button signIn=(Button)findViewById(R.id.login_btn);
        signIn.setText(Destination);
        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = login_name.getText().toString().trim();
                String password = login_secret.getText().toString().trim();
                String loginUrl = "ccm.viveninfomedia.com/signin";

                HashMap<String, String> loginParams = new HashMap<String, String>();
                loginParams.put("id", username);
                loginParams.put("password", password);

                loginJsonReq = new CustomRequest(Request.Method.POST, loginUrl, loginParams, loginSuccessListener(), loginErrorListener());
                Volley.newRequestQueue(mContext).add(loginJsonReq);
            }
        });
    }

    private Response.Listener<JSONObject> loginSuccessListener() {
        final TextView login_name= (TextView)findViewById(R.id.login_name);
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject loginResponse) {
                Log.d("response", loginResponse.toString());
                try {
                    if (loginResponse.getInt("status") == 0){
                        Toast.makeText(mContext, "Invalid Credentials, Try again", Toast.LENGTH_SHORT).show();
                    }
                    else if (loginResponse.getInt("status") == 1){
                        SessionHandler session= new SessionHandler(mContext,mActivity);
                        session.createLoginSession(login_name.getText().toString().trim());
                        gotoHome();
                        Toast.makeText(ActivityLogin.this, "successfully Logged in as "+login_name.getText().toString().trim(), Toast.LENGTH_SHORT).show();
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
        Intent mIntent= new Intent(ActivityLogin.this,MainActivity.class);
        startActivity(mIntent);
        finish();
    }

    private void gotoSignUp(){
        Intent mIntent= new Intent(ActivityLogin.this,ActivitySignup.class);
        startActivity(mIntent);
    }
}
