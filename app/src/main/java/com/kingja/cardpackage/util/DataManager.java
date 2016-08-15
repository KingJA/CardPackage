package com.kingja.cardpackage.util;

/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DataManager {
    public static String getToken() {
        return (String) SpUtils.get("TOKEN", "");
    }
}
