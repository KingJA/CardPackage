package com.kingja.cardpackage.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.ui.SystemBarTint.StatusBarCompat;
import com.kingja.cardpackage.util.ActivityManager;

public abstract class BaseActivity extends FragmentActivity {
	protected String TAG = getClass().getSimpleName();
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		StatusBarCompat.initStatusBar(this,getStatusBarColor()==-1? R.color.bg_blue:getStatusBarColor());
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

}
