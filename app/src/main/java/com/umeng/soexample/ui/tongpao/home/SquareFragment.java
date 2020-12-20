package com.umeng.soexample.ui.tongpao.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.persenter.tongpao.SquarePersenter;
import com.umeng.soexample.ui.tongpao.SquareFragment.AllFragment;
import com.umeng.soexample.ui.tongpao.SquareFragment.GhosaFragment;
import com.umeng.soexample.ui.tongpao.SquareFragment.NearbyFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class SquareFragment extends BaseFragment<SquarePersenter> {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager viewPager;

    @Override
    public int getLayout() {
        return R.layout.fragment_square;
    }

    @Override
    public void initView() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add( new AllFragment() );
        fragments.add( new GhosaFragment() );
        fragments.add( new NearbyFragment() );
        viewPager.setAdapter( new FragmentPagerAdapter( getChildFragmentManager() ) {
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
        tablayout.setupWithViewPager( viewPager );
        tablayout.getTabAt( 0 ).setText( "所有" );
        tablayout.getTabAt( 1 ).setText( "附近" );
        tablayout.getTabAt( 2 ).setText( "妙音" );


    }

    @Override
    public SquarePersenter createPersenter() {
        return null;
    }

    @Override
    public void initData() {

    }


}
