package com.umeng.soexample.ui.tongpao.Homefreshutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.umeng.soexample.R;

/**
 * @author : zlf
 * date    : 2019-04-19
 * github  : https://github.com/mamumu
 * blog    : https://www.jianshu.com/u/281e9668a5a6
 */
@SuppressLint("RestrictedApi")
public class MRefreshFooter extends LinearLayout implements RefreshHeader {

    private ImageView mImage;
    private AnimationDrawable mAnim;
    private ProgressBar mPro;

    public MRefreshFooter(Context context) {
        this(context, null, 0);
    }

    public MRefreshFooter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MRefreshFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.layout_footer_anim_refresh, this);
        mPro = view.findViewById(R.id.footer_anim_pro);

//        mImage = view.findViewById(R.id.footer_anim_img);
////        mAnim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_round_rotate);
//        mAnim = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.footer_anim_refresh);
////        LinearInterpolator linearInterpolator = new LinearInterpolator();
////        mAnim.setInterpolator(linearInterpolator);
//        mAnim.setOneShot(true);
//        mImage.setImageDrawable(mAnim);

    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
//        //控制是否稍微上滑动就刷新
//        kernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        if (mAnim != null && !mAnim.isRunning()) {
            mAnim.start();
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.stop();
            mImage.clearAnimation();
        }
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }





}
