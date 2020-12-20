package com.umeng.soexample.ui.tongpao;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.ui.TongPaoObject.AddFragment;
import com.umeng.soexample.ui.TongPaoObject.FindFragment;
import com.umeng.soexample.ui.TongPaoObject.HomeFragment;
import com.umeng.soexample.ui.TongPaoObject.MyFragment;
import com.umeng.soexample.ui.TongPaoObject.ShoppingFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TongPaoActivity extends AppCompatActivity {

    @BindView(R.id.tongPao_vp)
    ViewPager tongPaoVp;
    @BindView(R.id.tongPao_tab)
    TabLayout tongPaoTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tong_pao );
        ButterKnife.bind( this );
        initView();
    }

    private void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add( new HomeFragment() );
        fragments.add( new FindFragment() );
        fragments.add( new AddFragment() );
        fragments.add( new ShoppingFragment() );
        fragments.add( new MyFragment() );

        tongPaoVp.setAdapter( new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        tongPaoTab.setupWithViewPager( tongPaoVp );
        tongPaoTab.getTabAt( 0 ).setText( "首页" ).setIcon( R.drawable.icon_goods_hanfu );
        tongPaoTab.getTabAt( 1 ).setText( "发现" ).setIcon( R.drawable.icon_goods_zhoubian );
        tongPaoTab.getTabAt( 2 ).setText( "" ).setIcon( R.drawable.icon_main_add );
        tongPaoTab.getTabAt( 3 ).setText( "商城" ).setIcon( R.drawable.icon_home_game );
        tongPaoTab.getTabAt( 4 ).setText( "我的" ).setIcon( R.drawable.icon_home_goods );


    }
}