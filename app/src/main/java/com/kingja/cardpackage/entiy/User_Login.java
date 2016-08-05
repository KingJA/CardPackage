package com.kingja.cardpackage.entiy;

/**
 * Description：TODO
 * Create Time：2016/8/5 10:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class User_Login {


    /**
     * ResultCode : 0
     * ResultText : 注册成功
     * DataTypeCode : User_Login
     * TaskID : 1
     * Content : {"Token":"XXX","USERID":"XXX","NAME":"张三","PHONE":"13805771234","IDENTITYCARD":"330303199909091234"}
     */

    private int ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * Token : XXX
     * USERID : XXX
     * NAME : 张三
     * PHONE : 13805771234
     * IDENTITYCARD : 330303199909091234
     */

    private ContentBean Content;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultText() {
        return ResultText;
    }

    public void setResultText(String ResultText) {
        this.ResultText = ResultText;
    }

    public String getDataTypeCode() {
        return DataTypeCode;
    }

    public void setDataTypeCode(String DataTypeCode) {
        this.DataTypeCode = DataTypeCode;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        private String Token;
        private String USERID;
        private String NAME;
        private String PHONE;
        private String IDENTITYCARD;

        public String getToken() {
            return Token;
        }

        public void setToken(String token) {
            this.Token = token;
        }

        public String getUSERID() {
            return USERID;
        }

        public void setUSERID(String USERID) {
            this.USERID = USERID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getIDENTITYCARD() {
            return IDENTITYCARD;
        }

        public void setIDENTITYCARD(String IDENTITYCARD) {
            this.IDENTITYCARD = IDENTITYCARD;
        }
    }
}
