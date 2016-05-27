package com.ccm.android.ccm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class ActivityLogin extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
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
                if((login_name.getText().toString().trim().equals("admin") && login_secret.getText().toString().trim().equals("admin"))||
                (login_name.getText().toString().trim().equals("pallav") && login_secret.getText().toString().trim().equals("pallav"))){
                    SessionHandler session= new SessionHandler(mContext,mActivity);
                    session.createLoginSession(login_name.getText().toString().trim());
                    gotoHome();
                    Toast.makeText(ActivityLogin.this, "successfully Logged in as "+login_name.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
