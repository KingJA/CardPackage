package com.kingja.cardpackage.activity;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.base.BaseActivity;

/**
 * Description：TODO
 * Create Time：2016/8/5 15:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BackTitleActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout mRlTopRoot;
    private RelativeLayout mRlTopBack;
    private ImageView mIvTopBack;
    private RelativeLayout mRlTopMenu;
    private TextView mTvTopTitle;
    private FrameLayout mFlContent;


    @Override
    protected abstract void initVariables();

    @Override
    protected void initView() {
        mRlTopRoot = (RelativeLayout) findViewById(R.id.rl_top_root);
        mRlTopBack = (RelativeLayout) findViewById(R.id.rl_top_back);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mRlTopMenu = (RelativeLayout) findViewById(R.id.rl_top_menu);
        mTvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);

        mRlTopBack.setOnClickListener(this);
        View content = View.inflate(this,getBackContentView(),null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            mFlContent.addView(content, params);
        }
        initContentView();

    }

    protected abstract void initContentView();

    public enum TopColor {
        WHITE, BLUE
    }

    public void setTopColor(TopColor topColor) {
        mRlTopRoot.setBackgroundColor(ContextCompat.getColor(this, topColor==TopColor.WHITE?R.color.bg_white:R.color.bg_blue));
        mIvTopBack.setBackgroundResource(topColor==TopColor.WHITE?R.drawable.sel_back_black:R.drawable.sel_back_white);
        mTvTopTitle.setTextColor(ContextCompat.getColor(this, topColor==TopColor.WHITE?R.color.bg_blue:R.color.bg_white));
    }

    public void setTitle(String title) {
        mTvTopTitle.setVisibility(View.VISIBLE);
        mTvTopTitle.setText(title);
    }


    protected abstract int getBackContentView();

    @Override
    protected abstract void initNet();

    @Override
    protected abstract void initData();

    @Override
    protected abstract void setData();

    @Override
    protected int getContentView() {
        return R.layout.activity_back;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_top_back:
                finish();
                break;
            default:
                break;
        }
    }
}
