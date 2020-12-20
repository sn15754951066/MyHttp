package com.umeng.soexample.ui.tongpao.SquareFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.tongpao.SquareBean;
import com.umeng.soexample.persenter.tongpao.SquarePersenter;
import com.umeng.soexample.ui.tongpao.SquareAdapter.AllAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class AllFragment extends BaseFragment<SquarePersenter> implements ISquare.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<SquareBean.DataBean.DynamicsBean> dynamicsBeans;
    private AllAdapter allAdapter;

    @Override
    public int getLayout() {
        return R.layout.layout_all;
    }

    @Override
    public void initView() {
        dynamicsBeans = new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        allAdapter = new AllAdapter(getContext(),dynamicsBeans);
        recyclerView.setAdapter( allAdapter );
    }

    @Override
    public SquarePersenter createPersenter() {
        return new SquarePersenter( this );
    }

    @Override
    public void initData() {
        persenter.getSquare();
    }


    @Override
    public void getSquareReturn(SquareBean squareBean) {
        dynamicsBeans.clear();
        dynamicsBeans.addAll( squareBean.getData().getDynamics() );
        allAdapter.notifyDataSetChanged();
    }
}
