package com.umeng.soexample.ui.tongpao.HomePersonalPprofile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.behind_background)
    ImageView behindBackground;
    @BindView(R.id.iv_callback)
    ImageView ivCallback;
    @BindView(R.id.kong)
    TextView kong;
    @BindView(R.id.btn_focus)
    Button btnFocus;
    @BindView(R.id.btn_liao)
    Button btnLiao;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.tv_focus_people)
    TextView tvFocusPeople;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_experience)
    TextView tvExperience;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.iv_header_image)
    ImageView ivHeaderImage;
    @BindView(R.id.cool_tool_layout)
    CollapsingToolbarLayout coolToolLayout;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.nv)
    NestedScrollView nv;
    @BindView(R.id.tv_nike_name)
    TextView tvNikeName;
    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_personal );
        ButterKnife.bind( this );
        initView();
        //斜体
        Watermark.getInstance().show( this,"Fantasy BlogDemo" );

        String headerUrl = getIntent().getStringExtra( "HeaderUrl" );
        String nickName = getIntent().getStringExtra( "NickName" );
        Glide.with( this ).load( headerUrl ).apply( new RequestOptions().circleCrop() ).into( ivHeaderImage );
        Glide.with( this ).load( headerUrl ).into( behindBackground );
        tvNikeName.setText( nickName);

    }

    private void initView() {
        fragments = new ArrayList<>();

        fragments.add( new PersonalFragment() );
        fragments.add( new PersonalFragment() );
        fragments.add( new PersonalFragment() );
        fragments.add( new PersonalFragment() );
        fragments.add( new PersonalFragment() );

        vpMain.setAdapter( new FragmentPagerAdapter( getSupportFragmentManager() ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get( position );
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        } );

        tab.setupWithViewPager( vpMain );
        tab.getTabAt( 0 ).setText( "资料" );
        tab.getTabAt( 1 ).setText( "动态" );
        tab.getTabAt( 2 ).setText( "活动" );
        tab.getTabAt( 3 ).setText( "社团" );
        tab.getTabAt( 4 ).setText( "文章" );




    }
}