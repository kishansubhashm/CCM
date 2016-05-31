package com.ccm.android.ccm;

import java.io.Serializable;

/**
 * Created by KishanSubhashMiryala on 5/12/2016.
 */
public class User implements Serializable {
    private String mobileNo;
    private Integer lvl;

    public User(String mobileNo, Integer lvl){
        this.mobileNo=mobileNo;
        this.lvl=lvl;
    }

    protected String getMobile(){
        return this.mobileNo;
    }
    protected Integer getLvl(){
        return this.lvl;
    }
}
