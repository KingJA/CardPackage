package com.kingja.cardpackage.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.R;
import com.kingja.cardpackage.ui.SystemBarTint.StatusBarCompat;
import com.kingja.cardpackage.util.ActivityManager;

public abstract class BaseActivity extends FragmentActivity {
    protected String TAG = getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    protected FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mSupportFragmentManager = getSupportFragmentManager();
        StatusBarCompat.initStatusBar(this, getStatusBarColor() == -1 ? R.color.bg_black : getStatusBarColor());
        ActivityManager.getAppManager().addActivity(this);
        setContentView(getContentView());
        initConmonView();
        initVariables();
        initView();
        initNet();
        initData();
        setData();
    }

    protected int getStatusBarColor() {
        return -1;
    }

    private void initConmonView() {
        mProgressDialog = new ProgressDialog(this);

    }

    protected abstract void initVariables();

    protected abstract void initView();

    protected abstract void initNet();

    protected abstract void initData();

    protected abstract void setData();

    protected abstract int getContentView();

    protected void setProgressDialog(boolean show) {
        if (show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);

    }

    protected void showQuitDialog() {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.isTitleShow(false)
                .content("是否要退出当前页面")
                .contentTextSize(15f)
                .bgColor(ContextCompat.getColor(this, R.color.bg_white))
                .cornerRadius(5)
                .contentGravity(Gravity.CENTER)
                .widthScale(0.85f)
                .contentTextColor(ContextCompat.getColor(this, R.color.font_3))
                .dividerColor(ContextCompat.getColor(this, R.color.bg_divider))
                .btnTextSize(15f, 15f)
                .btnTextColor(ContextCompat.getColor(this, R.color.bg_blue), ContextCompat.getColor(this, R.color.bg_blue))
                .btnPressColor(ContextCompat.getColor(this, R.color.bg_press))
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        finish();
                    }
                });
    }

}
