package com.kingja.cardpackage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.kingja.cardpackage.R;
import com.kingja.cardpackage.activity.PersonApplyActivity;
import com.kingja.cardpackage.adapter.RoomListAdapter;
import com.kingja.cardpackage.base.BaseFragment;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.ui.DialogUtil;
import com.kingja.cardpackage.util.ToastUtil;

/**
 * Description：设备申报
 * Create Time：2016/8/19 14:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ApplyFragment extends BaseFragment implements View.OnClickListener,PersonApplyActivity.OnSaveClickListener ,OnOperItemClickL{
    private LinearLayout mLlSelectRoom;
    private TextView mTvApplyRoomNum;
    private TextView mTvApplyName;
    private ImageView mIvApplyCamera;
    private TextView mTvApplyCardId;
    private EditText mEtApplyPhone;
    private PersonApplyActivity mPersonApplyActivity;
    private ChuZuWu_List.ContentBean entiy;
    private NormalListDialog mNormalListDialog;


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
        mLlSelectRoom = (LinearLayout) view.findViewById(R.id.ll_selectRoom);
        mIvApplyCamera = (ImageView) view.findViewById(R.id.iv_apply_camera);
        mTvApplyRoomNum = (TextView) view.findViewById(R.id.tv_apply_roomNum);
        mTvApplyName = (TextView) view.findViewById(R.id.tv_apply_name);
        mTvApplyCardId = (TextView) view.findViewById(R.id.tv_apply_cardId);
        mEtApplyPhone = (EditText) view.findViewById(R.id.et_apply_phone);
    }

    @Override
    public void initFragmentVariables() {
        mPersonApplyActivity = (PersonApplyActivity) getActivity();
        entiy = (ChuZuWu_List.ContentBean) getArguments().getSerializable("ENTIY");
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
                ToastUtil.showToast("选择房间");
                mNormalListDialog = DialogUtil.getListDialog(getActivity(), "房间号", new RoomListAdapter(getActivity(), entiy.getRoomList()));
                mNormalListDialog.setOnOperItemClickL(this);
                break;
            case R.id.iv_apply_camera:
                ToastUtil.showToast("OCR");
                break;

        }
    }

    @Override
    public void onSaveClick() {
        ToastUtil.showToast("在ApplyFragmentApplyFragment关闭");
        mPersonApplyActivity.finish();
    }

    @Override
    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChuZuWu_List.ContentBean.RoomListBean bean= (ChuZuWu_List.ContentBean.RoomListBean) parent.getItemAtPosition(position);
        mTvApplyRoomNum.setText(bean.getROOMNO()+"");
        mNormalListDialog.dismiss();
    }

}
