package com.kingja.cardpackage.entiy;

/**
 * Created by Linus_Xie on 2016/6/27.
 */
public class LoginInfo {
    private String TaskID;
    private String USERNAME;
    private String PASSWORD;
    private String SOFTVERSION;
    private int SOFTTYPE;
    private PhoneInfo PHONEINFO;

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getSOFTVERSION() {
        return SOFTVERSION;
    }

    public void setSOFTVERSION(String SOFTVERSION) {
        this.SOFTVERSION = SOFTVERSION;
    }

    public int getSOFTTYPE() {
        return SOFTTYPE;
    }

    public void setSOFTTYPE(int SOFTTYPE) {
        this.SOFTTYPE = SOFTTYPE;
    }

    public PhoneInfo getPHONEINFO() {
        return PHONEINFO;
    }

    public void setPHONEINFO(PhoneInfo PHONEINFO) {
        this.PHONEINFO = PHONEINFO;
    }
}
