package com.ccm.android.ccm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class ActivityLoginOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        final TextView register=(TextView)findViewById(R.id.register);
        final Button EmpLogin = (Button) findViewById(R.id.login_emp);
        final Button RCPLogin = (Button) findViewById(R.id.login_rcp);

        EmpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin(v);
            }
        });

        RCPLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin(v);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignUp();
            }
        });


    }

    private void gotoLogin(View v){
        Log.d("Sign In Options","Someone clicked Login");
        String dest = "NONE";
        Intent mIntent= new Intent(ActivityLoginOptions.this,ActivityLogin.class);

        if(v.getId() == R.id.login_emp)
            dest = "Login as Employee";
        else if (v.getId() == R.id.login_rcp)
            dest = "Login as Partner";

        mIntent.putExtra("Destination", dest);
        startActivity(mIntent);
        finish();
    }

    private void gotoSignUp(){
        Intent mIntent= new Intent(ActivityLoginOptions.this,ActivitySignup.class);
        startActivity(mIntent);
    }

}
