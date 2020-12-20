package com.umeng.soexample.ui.tongpao.Homefreshutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.umeng.soexample.R;

/**
 * 自定义帧动画
 * @author wapchief
 * @date 2018/1/30
 */
@SuppressLint("RestrictedApi")
public class MRefreshloading extends LinearLayout implements RefreshHeader {

    private ImageView mAnimationImg;
    private AnimationDrawable mAnimationDrawable;
    Context mContext;
    public MRefreshloading(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }



    /**注意不能为null*/
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }


    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }


    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        start();
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        stop();
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }



    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate( R.layout.layout_header_anim_refresh, this);
        mAnimationImg = (ImageView) view.findViewById(R.id.header_anim_img);
        mAnimationDrawable=(AnimationDrawable) ContextCompat.getDrawable(mContext,R.drawable.header_anim_fresh);
        mAnimationDrawable.setOneShot(true);
        mAnimationImg.setImageDrawable(mAnimationDrawable);
    }


    /**开始*/
    protected void start() {
        if (mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }
    /**结束*/
    protected void stop() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }

}
