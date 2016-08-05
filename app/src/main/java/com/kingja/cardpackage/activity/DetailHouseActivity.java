package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.entiy.ChuZuWu_ListByRenter;

/**
 * Description：TODO
 * Create Time：2016/8/5 16:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailHouseActivity extends BackTitleActivity {
    private TextView mTvHouseName;
    private TextView mTvAddress;


    private ChuZuWu_ListByRenter.ContentBean entiy;

    @Override
    protected void initVariables() {
        entiy = (ChuZuWu_ListByRenter.ContentBean) getIntent().getSerializableExtra("ENTIY");
    }

    @Override
    protected void initContentView() {
        mTvHouseName = (TextView) findViewById(R.id.tv_houseName);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
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
}
