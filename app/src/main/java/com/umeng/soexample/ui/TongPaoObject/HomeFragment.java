package com.umeng.soexample.ui.TongPaoObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.ui.tongpao.home.SquareFragment;
import com.umeng.soexample.ui.tongpao.home.RecommendFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private TabLayout mTabLayoutHome;
    private ViewPager mViewPagerHome;

    //背景阴影
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_tongpao, container, false );
        initView( view );
        return view;
    }

    private void initView( final View itemView) {
        mTabLayoutHome = (TabLayout) itemView.findViewById( R.id.home_tabLayout );
        mViewPagerHome = (ViewPager) itemView.findViewById( R.id.home_viewPager );

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add( new RecommendFragment() );
        fragments.add( new SquareFragment() );

        mViewPagerHome.setAdapter( new FragmentPagerAdapter(getChildFragmentManager()) {
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

        mTabLayoutHome.setupWithViewPager( mViewPagerHome );
        mTabLayoutHome.getTabAt( 0 ).setText( "推荐" );
        mTabLayoutHome.getTabAt( 1 ).setText( "广场" );

    }


}
