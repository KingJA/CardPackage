package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.cardpackage.adapter.RentAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.R;
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
public class RentActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private List<ChuZuWu_List.ContentBean> mChuZuWuList=new ArrayList<>();
    private RentAdapter mRentAdapter;


    @Override
    protected void initVariables() {

    }


    @Override
    protected void initContentView() {
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mRentAdapter = new RentAdapter(this, mChuZuWuList);
        mLvTopContent.setAdapter(mRentAdapter);

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
                .setGeneralParam(DataManager.getToken(), Constants.CARD_TYPE_RENT,Constants.ChuZuWu_List, param)
                .setBeanType(ChuZuWu_List.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_List>() {
                    @Override
                    public void onSuccess(ChuZuWu_List bean) {
                        mSrlTopContent.setRefreshing(false);
                        mChuZuWuList = bean.getContent();
                        mRentAdapter.setData(mChuZuWuList);
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
        setTitle("我的出租屋");
        setTopColor(TopColor.WHITE);
    }


    @Override
    public void onRefresh() {
        mSrlTopContent.setRefreshing(false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChuZuWu_List.ContentBean bean= (ChuZuWu_List.ContentBean) parent.getItemAtPosition(position);
        DetailRentActivity.goActivity(this,bean);
    }
}
