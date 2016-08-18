package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.adapter.DeviceRoomAdapter;
import com.kingja.cardpackage.entiy.ChuZuWu_DeviceLists;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.Constants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/8/18 14:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeviceInfoActivity extends BackTitleActivity implements AdapterView.OnItemClickListener, DeviceRoomAdapter.OnExplandListener {
    private ChuZuWu_List.ContentBean entiy;
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private List<ChuZuWu_List.ContentBean.RoomListBean> roomList = new ArrayList<>();
    private DeviceRoomAdapter mDeviceRoomAdapter;
    private List<ChuZuWu_DeviceLists.ContentBean> mDeviceList;


    @Override
    protected void initVariables() {
        entiy = (ChuZuWu_List.ContentBean) getIntent().getSerializableExtra("ENTIY");
        roomList = entiy.getRoomList();
    }

    @Override
    protected void initContentView() {
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl_top_content);
        mLvTopContent = (ListView) findViewById(R.id.lv_top_content);

        mDeviceRoomAdapter = new DeviceRoomAdapter(this, roomList);
        mLvTopContent.setAdapter(mDeviceRoomAdapter);

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
        mDeviceRoomAdapter.setOnExplandListener(this);
    }

    @Override
    protected void setData() {
        setTitle("设备信息");
        setTopColor(TopColor.WHITE);
    }


    public static void goActivity(Context context, ChuZuWu_List.ContentBean entiy) {
        Intent intent = new Intent(context, DeviceInfoActivity.class);
        intent.putExtra("ENTIY", entiy);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onExpland(final String roomid, final String roomno, final ListView lv, ImageView iv, final int position, final boolean expland) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("TaskID", "1");
        param.put("RoomID", roomid);
        param.put("PageSize", 20);
        param.put("PageIndex", 0);

        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), Constants.CARD_TYPE_HOUSE, Constants.ChuZuWu_DeviceLists, param)
                .setBeanType(ChuZuWu_DeviceLists.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_DeviceLists>() {
                    @Override
                    public void onSuccess(ChuZuWu_DeviceLists bean) {
                        setProgressDialog(false);
                        mDeviceList = bean.getContent();
                        if (mDeviceList.size() == 0) {
                            ToastUtil.showToast(roomno + "房间没有设备");
                            return;
                        }
//                        deviceListAdapter = new DeviceListAdapter(position, roomid, roomno, DeviceManagerActivity.this, DeviceManagerActivity.this, deviceList);
//                        deviceListAdapter.setOnDeviceChangeListener(DeviceManagerActivity.this);
                        DeviceInfoAdapter mDeviceInfoAdapter = new DeviceInfoAdapter(DeviceInfoActivity.this, mDeviceList);
                        mDeviceRoomAdapter.saveAdapter(position, mDeviceInfoAdapter);
                        lv.setAdapter(mDeviceInfoAdapter);
                        mDeviceRoomAdapter.setVisibility(!expland, position);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }
}
