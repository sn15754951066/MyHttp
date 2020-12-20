package com.umeng.soexample.ui.tongpao.HomeDetailsPage;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.umeng.soexample.R;
import com.umeng.soexample.module.data.tongpao.RecommendBean;
import com.umeng.soexample.ui.tongpao.HomeAdapter.CommentsAdapter;
import com.umeng.soexample.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity  {

    private ImageView mIvHead;
    /**
     * 昵称
     */
    private TextView mTvName;
    /**
     * 时间
     */
    private TextView mTvTime;
    private ImageView mIvLabel;
    /**
     * +关注
     */
    private TextView mBtnFollow;
    private ConstraintLayout mUserLayout;
    /**
     * 内容
     */
    private TextView mTvContent;
    private NineGridImageView mIvNineGrid;
    private LinearLayout mSupporterLayout;
    private CollapsingToolbarLayout mToolabrLayout;
    private AppBarLayout mAppBar;
    private RecyclerView mRvComments;
    private CommentsAdapter commentsAdapter;
    private List<RecommendBean.DataBean.PostDetailBean.LikeDetailsBean> likeDetails;
    private RecommendBean.DataBean commends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details );
        commends = (RecommendBean.DataBean) getIntent().getSerializableExtra( "commends" );
        likeDetails = commends.getPostDetail().getLikeDetails();

        initView();
    }

    private void initView() {
        mIvHead = (ImageView) findViewById( R.id.iv_head );
        mTvName = (TextView) findViewById( R.id.tv_name );
        mTvTime = (TextView) findViewById( R.id.tv_time );
        mIvLabel = (ImageView) findViewById( R.id.iv_label );
        mBtnFollow = (TextView) findViewById( R.id.btn_follow );
        mUserLayout = (ConstraintLayout) findViewById( R.id.user_layout );
        mTvContent = (TextView) findViewById( R.id.tv_content );
        mIvNineGrid = (NineGridImageView) findViewById( R.id.iv_nine_grid );
        mSupporterLayout = (LinearLayout) findViewById( R.id.supporter_layout );
        mToolabrLayout = (CollapsingToolbarLayout) findViewById( R.id.toolabr_layout );
        mAppBar = (AppBarLayout) findViewById( R.id.app_bar );
        mRvComments = (RecyclerView) findViewById( R.id.rv_comments );

        Glide.with( this ).load(commends.getPostDetail().getHeadUrl()  ).into( mIvHead );
        TxtUtils.setTextView( mTvName, commends.getPostDetail().getNickName() );
        TxtUtils.setTextView( mTvContent, commends.getPostDetail().getContent() );
        TxtUtils.setTextView( mTvTime, commends.getPostDetail().getCreateTime() );

        ArrayList<String> strings = new ArrayList<>();
        for (RecommendBean.DataBean.PostDetailBean.ImagesBean images: commends.getPostDetail().getImages()) {
            strings.add( images.getFilePath() );
        }
        mIvNineGrid.setAdapter( new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                Glide.with( context ).load( o ).apply( RequestOptions.bitmapTransform( new RoundedCorners( 20 ) ) ).into( imageView );
            }
        } );
        mIvNineGrid.setImagesData( strings );



        mRvComments.setLayoutManager( new LinearLayoutManager( this ) );
        commentsAdapter = new CommentsAdapter(likeDetails,this);
        mRvComments.setAdapter( commentsAdapter );

        mRvComments.addItemDecoration(new DividerItemDecoration( this,DividerItemDecoration.VERTICAL ) );
        commentsAdapter.notifyDataSetChanged();
    }
}