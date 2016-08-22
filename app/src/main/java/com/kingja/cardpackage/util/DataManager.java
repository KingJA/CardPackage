package com.kingja.cardpackage.util;

/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DataManager {
    private static final String TOKEN="TOKEN";
    private static final String USER_ID="USER_ID";
    private static final String USER_PHONE="USER_PHONE";
    private static final String EMPTY="";

    /*================================GET================================*/
    public static String getToken() {
        return (String) SpUtils.get(TOKEN, EMPTY);
    }

    public static String getUserId() {
        return (String) SpUtils.get(USER_ID,EMPTY);
    }

    public static String getUserPhone() {
        return (String) SpUtils.get(USER_PHONE, EMPTY);
    }

    /*================================PUT================================*/

    public static void putToken(String token) {
      SpUtils.put(TOKEN, token);
    }
    public static void putUserId(String userId) {
        SpUtils.put(USER_ID, userId);
    }

    public static void  putUserPhone(String userPhone) {
        SpUtils.put(USER_PHONE, userPhone);
    }

}
