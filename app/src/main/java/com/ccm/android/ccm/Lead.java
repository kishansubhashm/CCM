package com.ccm.android.ccm;

/**
 * Created by KishanSubhashMiryala on 5/16/2016.
 */
public class Lead {



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getOccupationLevel() {
        return occupationLevel;
    }

    public int getOccupationPos() {
        return occupationPos;
    }

    public String[] getSELF_EMP() {
        return SELF_EMP;
    }

    public String[] getEMP() {
        return EMP;
    }

    public String[] getPROFESSIONAL() {
        return PROFESSIONAL;
    }

    public int getIncomePos() {
        return incomePos;
    }

    public String[] getANUAL_INCOME() {
        return ANUAL_INCOME;
    }

    public String getPanCard() {
        return panCard;
    }

    public String getAadharCard() {
        return aadharCard;
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

    public String getOfficeOrBusinessAddress() {
        return officeOrBusinessAddress;
    }

    private String name;
    private String address;
    private int status;
    private boolean isOpened;
    private final String occupation[]={
            "Self Employed at a",
            "Salaried at a",
            "Annual Income"
    };

    private int occupationLevel;
    private int occupationPos;

    private final String SELF_EMP[]= {"Proprietary company",
                        "Partnership company","Private Limited company"};
    private final String EMP[]= {"Proprietary company",
                        "Private Limited company","Government"};
    private final String PROFESSIONAL[]= {"Doctor",
                        "Architect","CA"};

    private int incomePos;
    private final String ANUAL_INCOME[]={"< 5 lakh per annum",
                                        "5-10 lakh per annum",
                                        "10-25 lakh per annum",
                                        "25-50 lakh per annum",
                                        "> 50 lakh per annum"};
    private String panCard;
    private String aadharCard;
    private String officeOrBusinessAddress;

    public Lead(String name, String address, int occupationLevel, int occupationPos, int incomePos, String panCard, String aadharCard,
                String officeOrBusinessAddress, int status, boolean isOpened){
        this.name=name;
        this.address=address;
        this.occupationLevel=occupationLevel;
        this.occupationPos=occupationPos;
        this.incomePos=incomePos;
        this.panCard=panCard;
        this.aadharCard=aadharCard;
        this.officeOrBusinessAddress=officeOrBusinessAddress;
        this.status=status;
        this.isOpened=isOpened;

    }
}
