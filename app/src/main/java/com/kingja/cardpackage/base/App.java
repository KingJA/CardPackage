package com.kingja.cardpackage.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kingja.cardpackage.util.Constants;

import org.xutils.BuildConfig;
import org.xutils.x;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：应用Application
 * 创建人：KingJA
 * 创建时间：2016/3/25 16:31
 * 修改备注：
 */
public class App extends Application {

    private static Context mAppContext;
    private static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        mSharedPreferences = getSharedPreferences(Constants.APPLICATION_NAME,
                MODE_PRIVATE);
        initXutils3();
    }
    private void initXutils3() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    public static Context getContext() {
        return mAppContext;
    }
    public static SharedPreferences getSP() {
        return mSharedPreferences;
    }


}
