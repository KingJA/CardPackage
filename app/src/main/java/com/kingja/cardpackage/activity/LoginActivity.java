package com.kingja.cardpackage.activity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.base.BaseActivity;
import com.kingja.cardpackage.db.DatebaseManager;
import com.kingja.cardpackage.db.DownloadDbManager;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.LoginInfo;
import com.kingja.cardpackage.entiy.PhoneInfo;
import com.kingja.cardpackage.entiy.User_Login;
import com.kingja.cardpackage.net.PoolManager;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.ActivityUtil;
import com.kingja.cardpackage.util.AppInfoUtil;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.PhoneUtil;
import com.kingja.cardpackage.util.SpUtils;

/**
 * Description：TODO
 * Create Time：2016/8/4 16:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginActivity extends BaseActivity {
    private EditText mEtUserName;
    private EditText mEtPassword;
    private CheckBox mCbRemember;
    private String mPassword;
    private String mUserName;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        mEtUserName = (EditText) findViewById(R.id.et_userName);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mCbRemember = (CheckBox) findViewById(R.id.cb_remember);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        PoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                DatebaseManager.getInstance(getApplicationContext()).copyDataBase(DownloadDbManager.DB_NAME);
            }
        });

    }

    @Override
    protected void setData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    public void onLogin(View view) {
        mUserName = mEtUserName.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();

        if (CheckUtil.checkEmpty(mUserName, "用户名不能为空") && CheckUtil.checkEmpty(mPassword, "密码不能为空")) {
            loadNet();
        }
    }

    private void loadNet() {
        LoginInfo mInfo = new LoginInfo();
        PhoneInfo phoneInfo = new PhoneUtil(LoginActivity.this).getInfo();
        mInfo.setUSERNAME(mUserName);
        mInfo.setPASSWORD(mPassword);
        mInfo.setPHONEINFO(phoneInfo);
        mInfo.setSOFTTYPE(1);
        Log.e(TAG, "AppInfoUtil.getVersionName(): "+AppInfoUtil.getVersionName() );
        mInfo.setSOFTVERSION(AppInfoUtil.getVersionName());
        mInfo.setTaskID("1");
        new ThreadPoolTask.Builder()
                .setGeneralParam("", "","User_Login", mInfo)
                .setBeanType(User_Login.class)
                .setCallBack(new WebServiceCallBack<User_Login>() {
                    @Override
                    public void onSuccess(User_Login bean) {
                        setProgressDialog(false);
                        DataManager.putToken(bean.getContent().getToken());
                        DataManager.putUserId(bean.getContent().getUserID());
                        DataManager.putUserPhone(bean.getContent().getPhone());
                        ActivityUtil.goActivityAndFinish(LoginActivity.this, MainActivity.class);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }
}
