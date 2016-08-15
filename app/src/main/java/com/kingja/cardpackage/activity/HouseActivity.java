package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.adapter.HouseAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_ListByRenter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.Constants;
import com.kingja.cardpackage.util.DataManager;

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
public class HouseActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private List<ChuZuWu_ListByRenter.ContentBean> mHouseList=new ArrayList<>();
    private HouseAdapter mHouseAdapter;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mHouseAdapter = new HouseAdapter(this, mHouseList);
        mLvTopContent.setAdapter(mHouseAdapter);
        mSrlTopContent.setColorSchemeResources(R.color.bg_black);
        mSrlTopContent.setProgressViewOffset(false, 0, AppUtil.dp2px(24));

    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_lv;
    }

    @Override
    protected void initNet() {
        mSrlTopContent.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("TaskID", "1");
        param.put("PageSize", "100");
        param.put("PageIndex", "0");
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), Constants.CARD_TYPE_HOUSE,Constants.ChuZuWu_ListByRenter, param)
                .setBeanType(ChuZuWu_ListByRenter.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_ListByRenter>() {
                    @Override
                    public void onSuccess(ChuZuWu_ListByRenter bean) {
                        mSrlTopContent.setRefreshing(false);
                        mHouseList = bean.getContent();
                        mHouseAdapter.setData(mHouseList);
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
    }

    @Override
    protected void setData() {
        setTitle("我的住房");
        setTopColor(TopColor.WHITE);

    }


    @Override
    public void onRefresh() {
        mSrlTopContent.setRefreshing(false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChuZuWu_ListByRenter.ContentBean bean= (ChuZuWu_ListByRenter.ContentBean) parent.getItemAtPosition(position);
        DetailHouseActivity.goActivity(this,bean);
    }
}
