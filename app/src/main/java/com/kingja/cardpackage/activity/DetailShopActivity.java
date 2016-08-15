package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.entiy.ShangPu_List;

/**
 * Description：TODO
 * Create Time：2016/8/6 15:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailShopActivity extends BackTitleActivity {
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {
        setTitle("我的店");
    }

    public static void goActivity(Context context, ShangPu_List.ContentBean entiy) {
        Intent intent = new Intent(context, DetailShopActivity.class);
        intent.putExtra("ENTIY",entiy);
        context.startActivity(intent);
    }
}
