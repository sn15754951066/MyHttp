package com.umeng.soexample.ui.tongpao.home;

import android.content.Context;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.module.data.tongpao.BannerBean;
import com.umeng.soexample.module.data.tongpao.DiscussedBean;
import com.umeng.soexample.module.data.tongpao.HotUserBean;
import com.umeng.soexample.module.data.tongpao.PersonalBean;
import com.umeng.soexample.module.data.tongpao.RecommendBean;
import com.umeng.soexample.persenter.tongpao.RecommendPersenter;
import com.umeng.soexample.ui.tongpao.HomeAdapter.DiscussedAdapter;
import com.umeng.soexample.ui.tongpao.HomeAdapter.HotUserAdapter;
import com.umeng.soexample.ui.tongpao.HomeAdapter.RecommmendAdapter;

import com.umeng.soexample.ui.tongpao.Homefreshutil.MRefreshFooter;
import com.umeng.soexample.ui.tongpao.Homefreshutil.MRefreshloading;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecommendFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerview_talk)
    RecyclerView recyclerviewTalk;
    @BindView(R.id.recyclerview_recommend)
    RecyclerView recyclerviewRecommend;
    @BindView(R.id.recyclerview_hotuser)
    RecyclerView recyclerviewHotuser;
    @BindView(R.id.re_down)
    TextView reDown;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout rocommendSmart;;


    private DiscussedAdapter discussedAdapter;
    private ArrayList<DiscussedBean.DataBean> discussedList;

    private ArrayList<RecommendBean.DataBean> recommendList;
    private RecommmendAdapter recommmendAdapter;

    private ArrayList<HotUserBean.DataBean> HotuserList;
    private HotUserAdapter hotuserAdapter;
    private List<BannerBean.DataBean.ListBean> bannerList;
    private MRefreshloading mRefreshAnimHeader;
    private MRefreshFooter mRefreshFooter;


    @Override
    public int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {
        discussedList = new ArrayList<>();
        discussedAdapter = new DiscussedAdapter( discussedList, getActivity() );
        recyclerviewTalk.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );
        recyclerviewTalk.setAdapter( discussedAdapter );
        discussedAdapter.setClick( new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int positions) {
                //跳转页面
            }
        } );

        recommendList = new ArrayList<>();
        recommmendAdapter = new RecommmendAdapter( recommendList, getActivity() );
        recyclerviewRecommend.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerviewRecommend.setAdapter( recommmendAdapter );

        HotuserList = new ArrayList<>();
        hotuserAdapter = new HotUserAdapter( HotuserList, getActivity() );
        recyclerviewHotuser.setLayoutManager( new LinearLayoutManager( getActivity(), RecyclerView.HORIZONTAL, false ) );
        recyclerviewHotuser.setAdapter( hotuserAdapter );

        String str = reDown.getText().toString();
        SpannableString spannableString = new SpannableString( str );
        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan( 1.2f );
        spannableString.setSpan( sizeSpan06, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE );
        reDown.setText( spannableString );

        SuperscriptSpan superscriptSpan1 = new SuperscriptSpan();
        spannableString.setSpan( superscriptSpan1, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE );
        reDown.setText( spannableString );

        initSmart();


    }

    private void initSmart() {

        //初始化header
        mRefreshAnimHeader = new MRefreshloading(getActivity());
        setHeader(mRefreshAnimHeader);
        mRefreshFooter = new MRefreshFooter(getActivity());
        setmRefreshFooter( mRefreshFooter );

        rocommendSmart.setOnRefreshLoadMoreListener( new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //延迟3秒关闭
                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                }, 3000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                discussedList.clear();
                HotuserList.clear();
                recommendList.clear();
                bannerList.clear();
                initData();

            }
        } );

    }

    /**
     * 设置刷新header风格
     * @param header
     */
    private void setHeader(RefreshHeader header) {
        if (rocommendSmart.isNestedScrollingEnabled()){
            rocommendSmart.finishRefresh();
        }
        rocommendSmart.setRefreshHeader(header);
    }

    public void setmRefreshFooter(MRefreshFooter mRefreshFooter) {
        this.mRefreshFooter = mRefreshFooter;
        if (rocommendSmart.isNestedScrollingEnabled()){
            rocommendSmart.finishRefresh();
        }
        //rocommendSmart.setRefreshFooter(mRefreshFooter);
    }


    @Override
    public RecommendPersenter createPersenter() {
        return new RecommendPersenter( this );
    }

    @Override
    public void initData() {
        persenter.getRecommend();
        persenter.getDiscuessed();
        persenter.getBanner();
        persenter.getHotUser();
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {
        recommendList.clear();
        RecommendBean.DataBean data = result.getData();
        recommendList.add( data );
        recommmendAdapter.notifyDataSetChanged();

        //实现适配器 点击图片方法
        recommmendAdapter.setIOnClickItem( new RecommmendAdapter.IOnClickItem() {
            @Override
            public void iOnClickItem(float v) {
                //设置透明度
                setBackground( v );
            }
        } );

        //关闭加载
        rocommendSmart.finishLoadMore();
        rocommendSmart.finishRefresh();



    }

    public void setBackground(float v) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = v;
        getActivity().getWindow().setAttributes( attributes );

    }

    @Override
    public void getBannerReturn(BannerBean result) {

        bannerList = result.getData().getList();

        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            BannerBean.DataBean.ListBean listBean = bannerList.get( i );
            String banner = listBean.getBanner();
            images.add( banner );
        }
        banner.setImages( images ).setImageLoader( new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                String images = (String) path;
                Glide.with( context ).load( images ).into( imageView );

            }
        } ).start();

        //关闭加载
        rocommendSmart.finishLoadMore();
        rocommendSmart.finishRefresh();


    }

    @Override
    public void getDiscussedReturn(DiscussedBean result) {
        discussedList.clear();
        discussedList.addAll( result.getData() );
        Log.e( "TAG", "getDiscussedReturn: " + result );
        discussedAdapter.notifyDataSetChanged();

        //关闭加载
        rocommendSmart.finishLoadMore();
        rocommendSmart.finishRefresh();
    }

    @Override
    public void getHotUserReturn(HotUserBean result) {
        HotuserList.clear();
        List<HotUserBean.DataBean> data = result.getData();
        HotuserList.addAll( data );
        hotuserAdapter.notifyDataSetChanged();

        //关闭加载
        rocommendSmart.finishLoadMore();
        rocommendSmart.finishRefresh();

    }

    @Override
    public void getPersonalReturn(PersonalBean result) {

    }
}
