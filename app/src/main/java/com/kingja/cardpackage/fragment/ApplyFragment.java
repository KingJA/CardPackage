package com.kingja.cardpackage.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.kingja.cardpackage.R;
import com.kingja.cardpackage.activity.PersonApplyActivity;
import com.kingja.cardpackage.adapter.RoomListAdapter;
import com.kingja.cardpackage.base.BaseFragment;
import com.kingja.cardpackage.entiy.ChuZuWu_LKSelfReportingIn;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.ActivityUtil;
import com.kingja.cardpackage.util.Constants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.StringUtil;
import com.kingja.cardpackage.util.TempConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.yunmai.android.engine.OcrEngine;
import com.yunmai.android.vo.IDCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.kingja.ocr.ACamera;

/**
 * Description：设备申报
 * Create Time：2016/8/19 14:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ApplyFragment extends BaseFragment implements View.OnClickListener, PersonApplyActivity.OnSaveClickListener, OnOperItemClickL {
    private LinearLayout mLlSelectRoom;
    private TextView mTvApplyRoomNum;
    private TextView mTvApplyName;
    private TextView mTvApplyCardId;
    private ImageView mIvApplyCamera;
    private EditText mEtApplyPhone;
    private ChuZuWu_List.ContentBean entiy;
    private NormalListDialog mNormalListDialog;
    private PersonApplyActivity mPersonApplyActivity;
    private ProgressDialog mProgressDialog;
    private String mRoomId;
    public static final int REQ_OCR = 100;
    private String name;
    private String cardId;
    private String phone;
    private List<ChuZuWu_List.ContentBean.RoomListBean> mRoomList;

    public static ApplyFragment newInstance(ChuZuWu_List.ContentBean bean) {
        ApplyFragment mApplyFragment = new ApplyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ENTIY", bean);
        mApplyFragment.setArguments(bundle);
        return mApplyFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_apply;
    }

    @Override
    public void initFragmentView(View view, Bundle savedInstanceState) {
        mPersonApplyActivity = (PersonApplyActivity) getActivity();
        mProgressDialog = new ProgressDialog(getActivity());
        mLlSelectRoom = (LinearLayout) view.findViewById(R.id.ll_selectRoom);
        mIvApplyCamera = (ImageView) view.findViewById(R.id.iv_apply_camera);
        mTvApplyRoomNum = (TextView) view.findViewById(R.id.tv_apply_roomNum);
        mTvApplyName = (TextView) view.findViewById(R.id.tv_apply_name);
        mTvApplyCardId = (TextView) view.findViewById(R.id.tv_apply_cardId);
        mEtApplyPhone = (EditText) view.findViewById(R.id.et_apply_phone);
    }

    @Override
    public void initFragmentVariables() {
        entiy = (ChuZuWu_List.ContentBean) getArguments().getSerializable("ENTIY");
        mRoomList = entiy.getRoomList();
    }

    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentData() {
        mLlSelectRoom.setOnClickListener(this);
        mIvApplyCamera.setOnClickListener(this);
        mPersonApplyActivity.setOnSaveClickListener(this);
    }

    @Override
    public void setFragmentData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_selectRoom:
                if (mRoomList.size() > 0) {
                    mNormalListDialog = DialogUtil.getListDialog(getActivity(), "房间号", new RoomListAdapter(getActivity(), mRoomList));
                    mNormalListDialog.setOnOperItemClickL(ApplyFragment.this);
                }else{
                    ToastUtil.showToast("该出租屋暂时没有房间");
                }
                break;
            case R.id.iv_apply_camera:
                ActivityUtil.goActivityForResult(getActivity(), ACamera.class, REQ_OCR);
                break;

        }
    }

    @Override
    public void onSaveClick() {
        name = mTvApplyName.getText().toString().trim();
        cardId = mTvApplyCardId.getText().toString().trim();
        phone = mEtApplyPhone.getText().toString().trim();
        if (CheckUtil.checkEmpty(mTvApplyRoomNum.getText().toString(), "请选择房间号")
                && CheckUtil.checkEmpty(name, "请通过相机获取姓名")
                && CheckUtil.checkEmpty(cardId, "请通过相机获取身份证号")
                && CheckUtil.checkPhoneFormat(phone)) {
            onApply();
        }

    }

    /**
     * 自主申报
     */
    private void onApply() {
        mProgressDialog.show();
        Map<String, Object> param = new HashMap<>();
        param.put(TempConstants.TaskID, "1");
        param.put(TempConstants.HOUSEID, entiy.getHOUSEID());
        param.put(TempConstants.ROOMID, mRoomId);
        param.put("LISTID", StringUtil.getUUID());
        /*通过OCR获取*/
        param.put("NAME", name);
        param.put("IDENTITYCARD", cardId);
        param.put("PHONE", phone);
        /**/
        param.put("REPORTERROLE", "2");
        param.put("OPERATOR", DataManager.getUserId());
        param.put("STANDARDADDRCODE", entiy.getSTANDARDADDRCODE());
        param.put("TERMINAL", "2");
        param.put("XQCODE", entiy.getXQCODE());
        param.put("PCSCODE", entiy.getPCSCODE());
        param.put("JWHCODE", entiy.getJWHCODE());
        param.put("OPERATORPHONE", "0");
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), Constants.CARD_TYPE_HOUSE, Constants.ChuZuWu_LKSelfReportingIn, param)
                .setBeanType(ChuZuWu_LKSelfReportingIn.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_LKSelfReportingIn>() {
                    @Override
                    public void onSuccess(ChuZuWu_LKSelfReportingIn bean) {
                        mProgressDialog.dismiss();
                        final NormalDialog doubleDialog = DialogUtil.getDoubleDialog(getActivity(), "是否要继续进行人员申报", "离开", "继续");
                        doubleDialog.setOnBtnClickL(new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                doubleDialog.dismiss();
                                mPersonApplyActivity.finish();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                mTvApplyName.setText("");
                                mTvApplyCardId.setText("");
                                mEtApplyPhone.setText("");
                                doubleDialog.dismiss();

                            }
                        });
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mProgressDialog.dismiss();
                    }
                }).build().execute();
    }

    @Override
    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChuZuWu_List.ContentBean.RoomListBean bean = (ChuZuWu_List.ContentBean.RoomListBean) parent.getItemAtPosition(position);
        mRoomId = bean.getROOMID();
        mTvApplyRoomNum.setText(bean.getROOMNO() + "");
        mNormalListDialog.dismiss();
    }

    private IDCard idCard;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final Intent result = data;
        mProgressDialog.show();
        new Thread() {
            @Override
            public void run() {
                OcrEngine ocrEngine = new OcrEngine();
                try {
                    byte[] bytes = result.getByteArrayExtra("idcardA");
                    idCard = ocrEngine.recognize(getActivity(), bytes, null, "");
                    if (idCard.getRecogStatus() == OcrEngine.RECOG_OK) {
                        mProgressDialog.dismiss();
                        mOcrHandler.sendMessage(mOcrHandler.obtainMessage(OcrEngine.RECOG_OK, ""));
                    } else {
                        mProgressDialog.dismiss();
                        mOcrHandler.sendEmptyMessage(idCard.getRecogStatus());
                    }
                } catch (Exception e) {
                    mProgressDialog.dismiss();
                    mOcrHandler.sendEmptyMessage(OcrEngine.RECOG_FAIL);
                }
            }
        }.start();
    }

    private Handler mOcrHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            mProgressDialog.dismiss();
            switch (msg.what) {
                case OcrEngine.RECOG_FAIL:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_blur, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_BLUR:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_blur, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_OK:
                    Log.e("RECOG_OK", "getName" + idCard.getName());
                    mTvApplyName.setText(idCard.getName());
                    mTvApplyCardId.setText(idCard.getCardNo());
                    break;
                case OcrEngine.RECOG_IMEI_ERROR:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_imei, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_FAIL_CDMA:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_cdma, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_LICENSE_ERROR:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_licens, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_TIME_OUT:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_time_out, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_ENGINE_INIT_ERROR:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_engine_init, Toast.LENGTH_SHORT).show();
                    break;
                case OcrEngine.RECOG_COPY:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_fail_copy, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(mPersonApplyActivity, R.string.reco_dialog_blur, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };
}
