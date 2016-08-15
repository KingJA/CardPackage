package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.entiy.ChuZuWu_ListByRenter;
import com.kingja.cardpackage.util.ToastUtil;

/**
 * Description：TODO
 * Create Time：2016/8/5 16:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailHouseActivity extends BackTitleActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private TextView mTvHouseName;
    private TextView mTvAddress;
    private ImageView mIvSign;
    private ImageView mIvClose;
    private TextView mTvRead;
    private TextView mTvMsg;
    private CheckBox mCbHouse;
    private CheckBox mCbWarmTip;
    private RelativeLayout mRlInfo;
    private TextView mTvCount;
    private RelativeLayout mRlMessage;
    private RelativeLayout mRlFangdao;
    private RelativeLayout mRlDeviceInfo;




    private ChuZuWu_ListByRenter.ContentBean entiy;

    @Override
    protected void initVariables() {
        entiy = (ChuZuWu_ListByRenter.ContentBean) getIntent().getSerializableExtra("ENTIY");
    }

    @Override
    protected void initContentView() {
        mTvHouseName = (TextView) findViewById(R.id.tv_houseName);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mIvSign = (ImageView) findViewById(R.id.iv_sign);
        mRlMessage = (RelativeLayout) findViewById(R.id.rl_message);
        mIvClose = (ImageView) findViewById(R.id.iv_close);
        mTvRead = (TextView) findViewById(R.id.tv_read);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mCbHouse = (CheckBox) findViewById(R.id.cb_house);
        mCbWarmTip = (CheckBox) findViewById(R.id.cb_warm_tip);
        mRlInfo = (RelativeLayout) findViewById(R.id.rl_info);
        mTvCount = (TextView) findViewById(R.id.tv_count);
        mRlFangdao = (RelativeLayout) findViewById(R.id.rl_fangdao);
        mRlDeviceInfo = (RelativeLayout) findViewById(R.id.rl_deviceInfo);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_house;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mCbHouse.setOnCheckedChangeListener(this);
        mCbWarmTip.setOnCheckedChangeListener(this);
        mRlDeviceInfo.setOnClickListener(this);
        mRlFangdao.setOnClickListener(this);
        mIvSign.setOnClickListener(this);
        mTvRead.setOnClickListener(this);
        mRlInfo.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        setTopColor(TopColor.BLUE);
        mTvHouseName.setText(entiy.getHOUSENAME());
        mTvAddress.setText(entiy.getADDRESS());
    }

    public static void goActivity(Context context, ChuZuWu_ListByRenter.ContentBean entiy) {
        Intent intent = new Intent(context, DetailHouseActivity.class);
        intent.putExtra("ENTIY",entiy);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //设备信息
            case R.id.rl_deviceInfo:
                ToastUtil.showToast("设备信息");
                break;
            //居家防盗
            case R.id.rl_fangdao:
                ToastUtil.showToast("居家防盗");
                break;
            //签到
            case R.id.iv_sign:
                ToastUtil.showToast("签到");
                break;
            //点击查看
            case R.id.tv_read:
                ToastUtil.showToast("点击查看");
                break;
            //预警信息
            case R.id.rl_info:
                ToastUtil.showToast("预警信息");
                break;
            //点击关闭
            case R.id.iv_close:
                ToastUtil.showToast("点击关闭");
                break;
            default:
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_house:
                ToastUtil.showToast(isChecked+"房屋撤布防");
                break;
            case R.id.cb_warm_tip:
                ToastUtil.showToast(isChecked+"预警信息提示");
                break;
            default:
                break;

        }
    }
}
