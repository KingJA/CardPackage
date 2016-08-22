package com.kingja.cardpackage.util;

/**
 * Description：TODO
 * Create Time：2016/8/4 16:53
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Constants {
    /**
     * Webservice参数
     */
    public static final String WEBSERVER_URL = "http://zafkapp.test.iotone.cn:12025/rentalestate.asmx";// WebServices访问地址，测试
    public static final String WEBSERVER_NAMESPACE = "http://tempuri.org/";// 命名空间
    public static final String WEBSERVER_REREQUEST = "RERequest";
    public static final String APPLICATION_NAME = "CardPackage";

    /*  ============================================  卡类型  ============================================*/

    /*我的住房*/
    public static final String CARD_TYPE_HOUSE = "1001";
    /*我家出租屋*/
    public static final String CARD_TYPE_RENT = "1002";
    /*我的店*/
    public static final String CARD_TYPE_SHOP = "1004";
    /*出租房代管*/
    public static final String CARD_TYPE_ADMIN = "1007";

    /*  ============================================  接口方法  ============================================*/

    /*我家出租屋列表*/
    public static final String ChuZuWu_List = "ChuZuWu_List";
    /*我的店列表*/
    public static final String ShangPu_List = "ShangPu_List";
    /*我的住房列表*/
    public static final String ChuZuWu_ListByRenter = "ChuZuWu_ListByRenter";
    /*人员管理列表*/
    public static final String ChuZuWu_MenPaiAuthorizationList = "ChuZuWu_MenPaiAuthorizationList";
    /*查询房间信息*/
    public static final String ChuZuWu_RoomInfo = "ChuZuWu_RoomInfo";
    /*修改房间信息*/
    public static final String ChuZuWu_ModifyRoom = "ChuZuWu_ModifyRoom";
    /*设备信息*/
    public static final String ChuZuWu_DeviceLists = "ChuZuWu_DeviceLists";
    /*申报列表*/
    public static final String ChuZuWu_LKSelfReportingList = "ChuZuWu_LKSelfReportingList";
    /*申报离开*/
    public static final String ChuZuWu_LKSelfReportingOut = "ChuZuWu_LKSelfReportingOut";
    /*自主申报*/
    public static final String ChuZuWu_LKSelfReportingIn = "ChuZuWu_LKSelfReportingIn";

}
