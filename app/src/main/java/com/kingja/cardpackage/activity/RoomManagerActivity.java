package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.db.DbDaoXutils3;
import com.kingja.cardpackage.entiy.Basic_Dictionary_Kj;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ChuZuWu_RoomInfo;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.Constants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.TempConstants;
import com.kingja.cardpackage.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/8/16 16:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RoomManagerActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener, View.OnClickListener {

    private String mRoomId;
    private String mRoomNum;

    private RelativeLayout rlHouseType;
    private RelativeLayout rlHouseArea;
    private RelativeLayout rlHouseDecorate;
    private RelativeLayout rlHousePrice;
    private RelativeLayout rlHousePay;
    private RelativeLayout rlHousePersons;
    private RelativeLayout rlHouseConfig;
    private RelativeLayout rlHouseTitle;
    private RelativeLayout rlHousePs;

    private TextView tvHouseType;
    private TextView tvHouseArea;
    private TextView tvHouseDecorate;
    private TextView tvHousePrice;
    private TextView tvHousePay;
    private TextView tvHousePersons;
    private TextView tvHouseConfig;
    private CheckBox cbHouseAuto;
    private TextView tvHouseTitle;
    private TextView tvHousePs;
    private ChuZuWu_RoomInfo.ContentBean content;


    @Override
    protected void initVariables() {
        mRoomId = getIntent().getStringExtra(TempConstants.ROOMID);
        mRoomNum = getIntent().getStringExtra(TempConstants.ROOMNUM);


    }

    @Override
    protected void initContentView() {
        rlHouseType = (RelativeLayout) findViewById(R.id.rl_houseType);
        rlHouseArea = (RelativeLayout) findViewById(R.id.rl_houseArea);
        rlHouseDecorate = (RelativeLayout) findViewById(R.id.rl_houseDecorate);
        rlHousePay = (RelativeLayout) findViewById(R.id.rl_housePay);
        rlHousePersons = (RelativeLayout) findViewById(R.id.rl_housePersons);
        rlHouseConfig = (RelativeLayout) findViewById(R.id.rl_houseConfig);
        rlHouseTitle = (RelativeLayout) findViewById(R.id.rl_houseTitle);
        rlHousePs = (RelativeLayout) findViewById(R.id.rl_housePs);
        rlHousePrice = (RelativeLayout) findViewById(R.id.rl_housePrice);


        tvHouseType = (TextView) findViewById(R.id.tv_houseType);
        tvHouseArea = (TextView) findViewById(R.id.tv_houseArea);
        tvHouseDecorate = (TextView) findViewById(R.id.tv_houseDecorate);
        tvHousePrice = (TextView) findViewById(R.id.tv_housePrice);
        tvHousePay = (TextView) findViewById(R.id.tv_housePay);
        tvHousePersons = (TextView) findViewById(R.id.tv_housePersons);
        tvHouseConfig = (TextView) findViewById(R.id.tv_houseConfig);
        cbHouseAuto = (CheckBox) findViewById(R.id.cb_houseAuto);
        tvHouseTitle = (TextView) findViewById(R.id.tv_houseTitle);
        tvHousePs = (TextView) findViewById(R.id.tv_housePs);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_room_manager;
    }

    @Override
    protected void initNet() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put(TempConstants.TaskID, "1");
        param.put(TempConstants.ROOMID, mRoomId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), Constants.CARD_TYPE_RENT, Constants.ChuZuWu_RoomInfo, param)
                .setBeanType(ChuZuWu_RoomInfo.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_RoomInfo>() {
                    @Override
                    public void onSuccess(ChuZuWu_RoomInfo bean) {
                        setProgressDialog(false);
                        content = bean.getContent();
                        fillData(content);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();

    }

    private void fillData(ChuZuWu_RoomInfo.ContentBean content) {
        tvHouseType.setText(content.getSHI() + "室" + content.getTING() + "厅" + content.getWEI() + "卫" + content.getYANGTAI() + "阳台");
        tvHouseArea.setText(content.getSQUARE() + TempConstants.UNIT_SQUARE);
        tvHouseDecorate.setText(content.getFIXTURE() + "");
        tvHousePrice.setText(content.getPRICE() + TempConstants.UNIT_MOUTH);
        tvHousePay.setText(content.getDEPOSIT() + "");
        tvHousePersons.setText(content.getGALLERYFUL() + TempConstants.UNIT_PERSON);
        cbHouseAuto.setChecked(content.getISAUTOPUBLISH() == 1);
        tvHouseConfig.setText("配置...");
        tvHouseTitle.setText(content.getTITLE());
        tvHousePs.setText("备注...");
    }

    @Override
    protected void initData() {
        rlHouseType.setOnClickListener(this);
        rlHouseArea.setOnClickListener(this);
        rlHouseDecorate.setOnClickListener(this);
        rlHousePrice.setOnClickListener(this);
        rlHousePay.setOnClickListener(this);
        rlHousePersons.setOnClickListener(this);
        rlHouseConfig.setOnClickListener(this);
        rlHouseTitle.setOnClickListener(this);
        rlHousePs.setOnClickListener(this);


    }

    @Override
    protected void setData() {
        setTopColor(TopColor.WHITE);
        setOnRightClickListener(this, "保存");
        setTitle("房间" + mRoomNum);
    }

    @Override
    public void onRightClick() {
        ToastUtil.showToast("保存");

    }

    public static void goActivity(Context context, String roomId, String roomNum) {
        Intent intent = new Intent(context, RoomManagerActivity.class);
        intent.putExtra(TempConstants.ROOMID, roomId);
        intent.putExtra(TempConstants.ROOMNUM, roomNum);
        context.startActivity(intent);
    }

    public static final int HOUSE_TYPE = 1;
    public static final int HOUSE_AREA = 2;
    public static final int HOUSE_DECORATE = 3;
    public static final int HOUSE_PRICE = 4;
    public static final int HOUSE_PAY = 5;
    public static final int HOUSE_PERSONS = 6;
    public static final int HOUSE_CONFIG = 7;
    public static final int HOUSE_TITLE = 8;
    public static final int HOUSE_PS = 9;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_houseType://房型,室厅卫阳1
                EditRoomActivity.goActivity(this, content.getSHI()+"", content.getTING()+"" , content.getWEI()+"", content.getYANGTAI()+"", HOUSE_TYPE);
                break;
            case R.id.rl_houseArea://面积2
                EditTextActivity.goActivity(this, "面积", content.getSQUARE() + "", "平方米", HOUSE_AREA);
                break;
            case R.id.rl_houseDecorate://装修程度3
                break;
            case R.id.rl_housePrice://房租4
                EditTextActivity.goActivity(this, "房租", content.getPRICE() + "", "元/月", HOUSE_PRICE);
                break;
            case R.id.rl_housePay://支付方式5
                break;
            case R.id.rl_housePersons://容纳人数6
                EditTextActivity.goActivity(this, "容纳人数", content.getGALLERYFUL() + "", "人", HOUSE_PERSONS);
                break;
            case R.id.rl_houseConfig://房间配置7
                break;
            case R.id.rl_houseTitle://发布标题8
                break;
            case R.id.rl_housePs://备注9
                break;
        }
    }
//    tvHouseType.setText(content.getSHI()
//    tvHouseDecorate.setText(content.getFI
//    tvHousePay.setText(content.getDEPOSIT
//    cbHouseAuto.setChecked(content.getISA
//    tvHouseConfig.setText("配置...");
//    tvHouseTitle.setText(content.getTITLE
//    tvHousePs.setText("备注...");
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: ");
        if (resultCode == Activity.RESULT_OK && data != null) {
            Log.e(TAG, "RESULT_OK: ");
            switch (requestCode) {
                case 1:
                    tvHouseType.setText(data.getStringExtra(EditRoomActivity.SHI) + "室"
                            + data.getStringExtra(EditRoomActivity.TING) + "厅"
                            + data.getStringExtra(EditRoomActivity.WEI) + "卫"
                            + data.getStringExtra(EditRoomActivity.YANGTAI) + "阳台");
                    break;
                case HOUSE_AREA:
                    Log.e(TAG, "HOUSE_AREA: ");
                    tvHouseArea.setText(data.getStringExtra("VALUE"));
                    break;
                case 3:
                    break;
                case 4:
                    tvHousePrice.setText(data.getStringExtra("VALUE"));
                    break;
                case 5:
                    break;
                case 6:
                    tvHousePersons.setText(data.getStringExtra("VALUE"));
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;

            }
        }
    }
}
