package com.umeng.soexample.ui.TongPaoObject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.IDiscover;
import com.umeng.soexample.module.data.tongpao.DiscoverBean;
import com.umeng.soexample.persenter.tongpao.DiscoverPersenter;
import com.umeng.soexample.ui.tongpao.FindAdapter.FindAdapter;
import com.umeng.soexample.ui.tongpao.FindFragment.HotFragment;
import com.umeng.soexample.ui.tongpao.FindFragment.MakeupFragment;
import com.umeng.soexample.ui.tongpao.FindFragment.TuShangFragment;
import com.umeng.soexample.ui.tongpao.FindFragment.WikipediaFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class FindFragment extends BaseFragment<DiscoverPersenter> implements IDiscover.View {


    @BindView(R.id.find_rv)
    RecyclerView findRv;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp_main)
    ViewPager mMainVp;
    private FindAdapter findAdapter;
    private ArrayList<DiscoverBean.DataBean> discoverBeans;

    @Override
    public int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        discoverBeans = new ArrayList<>();
        findRv.setLayoutManager( new LinearLayoutManager( getActivity(),RecyclerView.HORIZONTAL,false ) );
        findAdapter = new FindAdapter( discoverBeans, getActivity() );
        findRv.setAdapter( findAdapter );


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add( new HotFragment() );
        fragments.add( new MakeupFragment() );
        fragments.add( new TuShangFragment() );
        fragments.add( new WikipediaFragment() );

        mMainVp.setAdapter( new FragmentPagerAdapter( getChildFragmentManager() ) {
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
        mTab.setupWithViewPager( mMainVp );
        mTab.getTabAt( 0 ).setText( "热点" );
        mTab.getTabAt( 1 ).setText( "状造" );
        mTab.getTabAt( 2 ).setText( "图赏" );
        mTab.getTabAt( 3 ).setText( "百科" );
    }


    @Override
    public DiscoverPersenter createPersenter() {
        return new DiscoverPersenter( this );
    }

    @Override
    public void initData() {
        persenter.getDiscover();

    }


    @Override
    public void getDiscoverReturn(DiscoverBean result) {
        discoverBeans.clear();
        discoverBeans.addAll( result.getData());
        findAdapter.notifyDataSetChanged();

    }
}