package com.kingja.cardpackage.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.kingja.cardpackage.activity.LoginActivity;
import com.kingja.cardpackage.util.ActivityManager;
import com.kingja.cardpackage.base.App;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.util.ToastUtil;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：WebService管理类
 * 创建人：KingJA
 * 创建时间：2016/3/22 13:08
 * 修改备注：
 */
public class ThreadPoolTask implements Runnable {


    private String token;
    private String dataTypeCode;
    private String cardType;
    private Object privateParam;
    private Class clazz;

    private WebServiceCallBack callBack;
    private int resultCode;
    private ErrorResult errorResult;

    public ThreadPoolTask(Builder builder) {
        this.token = builder.token;
        this.dataTypeCode = builder.dataTypeCode;
        this.cardType = builder.cardType;
        this.privateParam = builder.privateParam;
        this.clazz = builder.clazz;
        this.callBack = builder.callBack;
    }

    public void execute() {
        PoolManager.getInstance().execute(this);
    }


    @Override
    public void run() {
        Map<String, Object> generalParam = getGeneralParam(token, cardType, dataTypeCode, privateParam);
        try {
            final String json = WebServiceManager.getInstance().load(generalParam);
            JSONObject rootObject = new JSONObject(json);
            resultCode = rootObject.getInt("ResultCode");
            if (resultCode != 0) {
                Log.i("UNSUCCESS", json);
//                Logger.json(json);
                String resultText = rootObject.getString("ResultText");
                String dataTypeCode1 = rootObject.getString("DataTypeCode");
                errorResult = new ErrorResult();
                errorResult.setResultCode(resultCode);
                errorResult.setResultText(resultText);
                errorResult.setDataTypeCode(dataTypeCode1);
                if (callBack != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast(errorResult.getResultText());
                            callBack.onErrorResult(errorResult);
                            if (resultCode == 2) {
                                ActivityManager.getAppManager().finishAllActivity();
                                Intent intent = new Intent(App.getContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                App.getContext().startActivity(intent);
//                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                            ToastUtil.showToast(errorResult.getResultText());
                        }
                    });
                }
            } else {
                Log.i("SUCCESS", json);
//                Logger.json(json);

                if (callBack != null) {

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(json2Bean(json, clazz));
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast("连接超时");
                }
            });
        }
    }

/*
    1001 我的住房#
    1002 我家出租房#
    1003 我的车
    1004 我的店#
    1005 亲情关爱
    1006 服务商城
    1007 出租房代管#*/

    public Map<String, Object> getGeneralParam(String token, String cardType, String dataTypeCode, Object privateParam) {
        Gson gson = new Gson();
        String json = gson.toJson(privateParam);
        Log.i("PARAM_JSON", json);
//        Logger.json(json);
        Map<String, Object> generalParam = new HashMap<>();
        generalParam.put("token", token);
        generalParam.put("encryption", "0");
        generalParam.put("dataTypeCode", dataTypeCode);
        generalParam.put("content", json);
        generalParam.put("cardType", cardType);
        generalParam.put("taskId", "1");
        return generalParam;

    }


    public static class Builder {
        private String token;
        private String cardType;
        private String dataTypeCode;
        private Object privateParam;
        private Class clazz;
        private WebServiceCallBack callBack;

        public ThreadPoolTask.Builder setGeneralParam(String token, String cardType, String dataTypeCode, Object privateParam) {
            this.token = token;
            this.cardType = cardType;
            this.dataTypeCode = dataTypeCode;
            this.privateParam = privateParam;
            return this;
        }

        public <T> ThreadPoolTask.Builder setBeanType(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public <T> ThreadPoolTask.Builder setCallBack(WebServiceCallBack<T> callBack) {
            this.callBack = callBack;
            return this;


        }


        public ThreadPoolTask build() {
            return new ThreadPoolTask(this);
        }
    }

    private <T> T json2Bean(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

}
