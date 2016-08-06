package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/6 15:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShangPu_List {

    /**
     * ResultCode : 0
     * ResultText : 操作成功
     * DataTypeCode : ShangPu_List
     * TaskID :  1
     * Content : [{"SHOPID":"XXX","SHOPNAME":"商铺1","STARTTIME":"2015-10-01 09:00:00","ENDTIME":"2015-10-01 21:00:00","STARTWORKTIME":"09:00:00","ENDWORKTIME":"21:00:00","DEPLOYSTATUS":1}]
     */

    private int ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * SHOPID : XXX
     * SHOPNAME : 商铺1
     * STARTTIME : 2015-10-01 09:00:00
     * ENDTIME : 2015-10-01 21:00:00
     * STARTWORKTIME : 09:00:00
     * ENDWORKTIME : 21:00:00
     * DEPLOYSTATUS : 1
     */

    private List<ContentBean> Content;

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

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public static class ContentBean implements Serializable{
        private String SHOPID;
        private String SHOPNAME;
        private String STARTTIME;
        private String ENDTIME;
        private String STARTWORKTIME;
        private String ENDWORKTIME;
        private int DEPLOYSTATUS;

        public String getSHOPID() {
            return SHOPID;
        }

        public void setSHOPID(String SHOPID) {
            this.SHOPID = SHOPID;
        }

        public String getSHOPNAME() {
            return SHOPNAME;
        }

        public void setSHOPNAME(String SHOPNAME) {
            this.SHOPNAME = SHOPNAME;
        }

        public String getSTARTTIME() {
            return STARTTIME;
        }

        public void setSTARTTIME(String STARTTIME) {
            this.STARTTIME = STARTTIME;
        }

        public String getENDTIME() {
            return ENDTIME;
        }

        public void setENDTIME(String ENDTIME) {
            this.ENDTIME = ENDTIME;
        }

        public String getSTARTWORKTIME() {
            return STARTWORKTIME;
        }

        public void setSTARTWORKTIME(String STARTWORKTIME) {
            this.STARTWORKTIME = STARTWORKTIME;
        }

        public String getENDWORKTIME() {
            return ENDWORKTIME;
        }

        public void setENDWORKTIME(String ENDWORKTIME) {
            this.ENDWORKTIME = ENDWORKTIME;
        }

        public int getDEPLOYSTATUS() {
            return DEPLOYSTATUS;
        }

        public void setDEPLOYSTATUS(int DEPLOYSTATUS) {
            this.DEPLOYSTATUS = DEPLOYSTATUS;
        }
    }
}
