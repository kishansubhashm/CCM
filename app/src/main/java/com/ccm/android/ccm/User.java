package com.ccm.android.ccm;

import java.io.Serializable;

/**
 * Created by KishanSubhashMiryala on 5/12/2016.
 */
public class User implements Serializable {
    private String fName;
    private String lName;
    private String uName;
    private String mobileNo;
    private String passString;

    public User(String fName,
            String lName,
            String uName,
            String mobileNo,
            String passString){
        this.fName=fName;
        this.lName=lName;
        this.uName=uName;
        this.mobileNo=mobileNo;
        this.passString=passString;
    }

    protected String getuName(){
        return this.uName;
    }
    protected String getMobile(){
        return this.mobileNo;
    }
    protected String getPassword(){
        return this.passString;
    }
}
