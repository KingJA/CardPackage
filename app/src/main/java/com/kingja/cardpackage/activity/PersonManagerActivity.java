package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.adapter.PersonManagerAdapter;
import com.kingja.cardpackage.adapter.RoomListAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ChuZuWu_ListByRenter;
import com.kingja.cardpackage.entiy.ChuZuWu_MenPaiAuthorizationList;
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
 * Create Time：2016/8/15 16:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonManagerActivity extends BackTitleActivity{
    public static String HOUSE_ID="HOUSE_ID";
    public static String ROOM_ID="ROOM_ID";
    public static String ROOM_NUM="ROOM_NUM";
    private String mHouseId;
    private String mRoomId;
    private String mRoomNum;

    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private List<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean> personList=new ArrayList<>();
    private PersonManagerAdapter mPersonManagerAdapter;


    @Override
    protected void initVariables() {
        mHouseId = getIntent().getStringExtra(HOUSE_ID);
        mRoomId = getIntent().getStringExtra(ROOM_ID);
        mRoomNum = getIntent().getStringExtra(ROOM_NUM);
    }

    @Override
    protected void initContentView() {
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mPersonManagerAdapter = new PersonManagerAdapter(this, personList);
        mLvTopContent.setAdapter(mPersonManagerAdapter);

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
        param.put("HOUSEID", mHouseId);
        param.put("ROOMID", mRoomId);
        param.put("TaskID", "1");
        param.put("PageSize", "100");
        param.put("PageIndex", "0");
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), Constants.CARD_TYPE_HOUSE,Constants.ChuZuWu_MenPaiAuthorizationList, param)
                .setBeanType(ChuZuWu_MenPaiAuthorizationList.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_MenPaiAuthorizationList>() {
                    @Override
                    public void onSuccess(ChuZuWu_MenPaiAuthorizationList bean) {
                        mSrlTopContent.setRefreshing(false);
                        personList = bean.getContent().getPERSONNELINFOLIST();
                        mPersonManagerAdapter.setData(personList);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrlTopContent.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {
        setTitle("房间"+mRoomNum);

    }

    public static void goActivity(Context context, String houseId,String roomId,String roomNum) {
        Intent intent = new Intent(context, PersonManagerActivity.class);
        intent.putExtra(HOUSE_ID, houseId);
        intent.putExtra(ROOM_ID, roomId);
        intent.putExtra(ROOM_NUM, roomNum);
        context.startActivity(intent);
    }
}
