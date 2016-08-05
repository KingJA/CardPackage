package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.cardpackage.adapter.HouseAdapter;
import com.kingja.cardpackage.adapter.RentAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.User_Login;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.ActivityUtil;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.base.BaseActivity;
import com.kingja.cardpackage.R;
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
public class RentActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener,AdapterView.OnItemClickListener{
    private RelativeLayout mRlTopBack;
    private RelativeLayout mRlTopMenu;
    private TextView mTvTopTitle;
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private List<ChuZuWu_List.ContentBean> mChuZuWuList=new ArrayList<>();
    private RentAdapter mRentAdapter;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        mRlTopBack = (RelativeLayout) findViewById(R.id.rl_top_back);
        mRlTopMenu = (RelativeLayout) findViewById(R.id.rl_top_menu);
        mTvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mRentAdapter = new RentAdapter(this, mChuZuWuList);
        mLvTopContent.setAdapter(mRentAdapter);

        mSrlTopContent.setColorSchemeResources(R.color.bg_black);
        mSrlTopContent.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        mSrlTopContent.setOnRefreshListener(this);
    }

    @Override
    protected void initNet() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("TaskID", "1");
        param.put("PageSize", "100");
        param.put("PageIndex", "0");
        new ThreadPoolTask.Builder()
                .setGeneralParam((String) SharedPreferencesUtils.get("TOKEN", ""), 0,"ChuZuWu_List", param)
                .setBeanType(ChuZuWu_List.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_List>() {
                    @Override
                    public void onSuccess(ChuZuWu_List bean) {
                        setProgressDialog(false);
                        mChuZuWuList = bean.getContent();
                        mRentAdapter.setData(mChuZuWuList);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mRlTopBack.setOnClickListener(this);
        mRlTopMenu.setOnClickListener(this);
        mRlTopMenu.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setData() {
        mTvTopTitle.setText("我的出租屋");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_top;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_top_back:
                finish();
                break;
            case R.id.rl_top_menu:
                ToastUtil.showToast("菜单");
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
