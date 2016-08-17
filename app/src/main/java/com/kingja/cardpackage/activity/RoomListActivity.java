package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.adapter.RoomListAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/15 14:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RoomListActivity extends BackTitleActivity implements AdapterView.OnItemClickListener {
    private ChuZuWu_List.ContentBean entiy;
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private List<ChuZuWu_List.ContentBean.RoomListBean> roomList = new ArrayList<>();
    private RoomListAdapter mRoomListAdapter;
    private int mRequestCode;


    @Override
    protected void initVariables() {
        entiy = (ChuZuWu_List.ContentBean) getIntent().getSerializableExtra("ENTIY");
        mRequestCode = getIntent().getIntExtra("REQUEST_CODE", 0);
        roomList = entiy.getRoomList();
    }

    @Override
    protected void initContentView() {
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mRoomListAdapter = new RoomListAdapter(this, roomList);
        mLvTopContent.setAdapter(mRoomListAdapter);

        mSrlTopContent.setColorSchemeResources(R.color.bg_black);
        mSrlTopContent.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_lv;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mLvTopContent.setOnItemClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle("房间号");
        setTopColor(TopColor.WHITE);
    }


    public static void goActivity(Context context, ChuZuWu_List.ContentBean entiy, int requestCode) {
        Intent intent = new Intent(context, RoomListActivity.class);
        intent.putExtra("ENTIY", entiy);
        intent.putExtra("REQUEST_CODE", requestCode);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChuZuWu_List.ContentBean.RoomListBean roomBean = (ChuZuWu_List.ContentBean.RoomListBean) parent.getItemAtPosition(position);
        if (mRequestCode == 0) {
            //跳转人员管理界面
            PersonManagerActivity.goActivity(this, entiy.getHOUSEID(), roomBean.getROOMID(), roomBean.getROOMNO() + "");
        }else{
            //跳转房间管理界面
            RoomManagerActivity.goActivity(this,  roomBean.getROOMID(), roomBean.getROOMNO() + "");
        }
    }
}
