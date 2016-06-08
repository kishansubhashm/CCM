package com.ccm.android.ccm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by KishanSubhashMiryala on 5/16/2016.
 */
public class Lead {



    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getPanCard() {
        return panCard;
    }
    public String getAadharCard() {
        return aadhaarCard;
    }
    public String getAddress() {
        return address;
    }

    public int getOccupation() {
        return occupation;
    }
    public int getOccupationdetail() {
        return occupationdetail;
    }

    public String[] getSELF_EMP() {
        return SELF;
    }
    public String[] getEMP() {
        return EMP;
    }
    public String[] getPROFESSIONAL() {
        return PROF;
    }

    public String getIncome() {
        return income;
    }

    public String[] getANUAL_INCOME() {
        return ANUAL_INCOME;
    }

    public int getStatus() {
        return status;
    }

    public boolean getIsOpened() {
        return isOpened;
    }
    public void setIsOpened(boolean isOpened) {
        this.isOpened=isOpened;
    }

    public void setStatus(int status){
        this.status=status;
    }

    private String name, address, phone, panCard, aadhaarCard;
    private String bizcards, bizsales, bizsince, bizpremises, biznature, bizphone;

    private int status;
    private boolean isOpened;

    private int occupation, occupationdetail;
    private final String occupationMap[]={"Self Employed at a", "Salaried at a", "Annual Income"};
    private final String SELF[]= {"Proprietary company", "Partnership company", "Private Limited company"};
    private final String EMP[]= {"Proprietary company", "Private Limited company", "Government"};
    private final String PROF[]= {"Doctor", "Architect", "CA"};

    private String income;
    private final String ANUAL_INCOME[]={"< 5 lakh per annum",
                                        "5-10 lakh per annum",
                                        "10-25 lakh per annum",
                                        "25-50 lakh per annum",
                                        "> 50 lakh per annum"};

    public Lead(String name, String phone, String panCard, String aadharCard, String addr, int occupation,
                int occupationdetail, String income, JSONObject biz, int sts, boolean isOpened){
        this.name=name;
        this.phone=phone;
        this.panCard=panCard;
        this.aadhaarCard=aadharCard;
        this.address=addr;
        this.occupation=occupation;
        this.occupationdetail=occupationdetail;
        this.income=income;

        try{
            this.bizcards = biz.getString("bizcards").toString();
            this.bizsales = biz.getString("bizsales").toString();
            this.bizsince = biz.getString("bizsince").toString();
            this.bizpremises = biz.getString("bizpremises").toString();
            this.biznature = biz.getString("biznature").toString();
            this.bizphone = biz.getString("bizphone").toString();
        }catch(Exception pe){
            System.out.println(pe);
        }
        this.status=sts;
        this.isOpened=isOpened;
    }
}
