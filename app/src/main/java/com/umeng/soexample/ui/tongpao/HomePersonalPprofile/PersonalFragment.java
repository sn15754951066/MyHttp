package com.umeng.soexample.ui.tongpao.HomePersonalPprofile;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.module.data.tongpao.BannerBean;
import com.umeng.soexample.module.data.tongpao.DiscussedBean;
import com.umeng.soexample.module.data.tongpao.HotUserBean;
import com.umeng.soexample.module.data.tongpao.PersonalBean;
import com.umeng.soexample.module.data.tongpao.RecommendBean;
import com.umeng.soexample.persenter.tongpao.RecommendPersenter;

import java.util.ArrayList;

import butterknife.BindView;

public class PersonalFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private ArrayList<PersonalBean.DataBean> personalBeans;
    private PersonalAdapter personalAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {
        personalBeans = new ArrayList<>();
        rvMain.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        personalAdapter = new PersonalAdapter( personalBeans,getActivity());
        rvMain.setAdapter( personalAdapter );

    }

    @Override
    public RecommendPersenter createPersenter() {
        return new RecommendPersenter( this );
    }

    @Override
    public void initData() {
        persenter.getPersonal();
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {

    }

    @Override
    public void getBannerReturn(BannerBean result) {

    }

    @Override
    public void getDiscussedReturn(DiscussedBean result) {

    }

    @Override
    public void getHotUserReturn(HotUserBean result) {

    }

    @Override
    public void getPersonalReturn(PersonalBean result) {
        personalBeans.clear();
        PersonalBean.DataBean data = result.getData();
        personalBeans.add( data);
        personalAdapter.notifyDataSetChanged();



    }
}
