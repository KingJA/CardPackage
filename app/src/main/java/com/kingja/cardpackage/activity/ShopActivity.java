package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.adapter.ShopAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.ShangPu_List;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.SharedPreferencesUtils;
import com.kingja.cardpackage.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/8/4 16:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShopActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,BackTitleActivity.OnMenuClickListener{
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private ShopAdapter mShopAdapter;
    private List<ShangPu_List.ContentBean> mShopList =new ArrayList<>();


    @Override
    protected void initVariables() {

    }


    @Override
    protected void initContentView() {
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mShopAdapter = new ShopAdapter(this, mShopList);
        mLvTopContent.setAdapter(mShopAdapter);

        mSrlTopContent.setColorSchemeResources(R.color.bg_black);
        mSrlTopContent.setProgressViewOffset(false, 0, AppUtil.dp2px(24));

    }

    @Override
    protected int getBackContentView() {
        return R.layout.sigle_lv;
    }

    @Override
    protected void initNet() {
        mSrlTopContent.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("TaskID", "1");
        param.put("PageSize", "100");
        param.put("PageIndex", "0");
        new ThreadPoolTask.Builder()
                .setGeneralParam((String) SharedPreferencesUtils.get("TOKEN", ""), 0,"ShangPu_List", param)
                .setBeanType(ShangPu_List.class)
                .setCallBack(new WebServiceCallBack<ShangPu_List>() {
                    @Override
                    public void onSuccess(ShangPu_List bean) {
                        mSrlTopContent.setRefreshing(false);
                        mShopList = bean.getContent();
                        mShopAdapter.setData(mShopList);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrlTopContent.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mLvTopContent.setOnItemClickListener(this);
        mSrlTopContent.setOnRefreshListener(this);
        setOnMenuClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle("我的店");
        setTopColor(TopColor.WHITE);
    }


    @Override
    public void onRefresh() {
        mSrlTopContent.setRefreshing(false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShangPu_List.ContentBean bean= (ShangPu_List.ContentBean) parent.getItemAtPosition(position);
        DetailShopActivity.goActivity(this,bean);
    }

    @Override
    public void onMenuClick() {
        ToastUtil.showToast("菜单");
    }
}
