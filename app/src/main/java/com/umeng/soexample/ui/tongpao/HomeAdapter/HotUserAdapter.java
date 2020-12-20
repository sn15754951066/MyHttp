package com.umeng.soexample.ui.tongpao.HomeAdapter;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.HotUserBean;
import com.umeng.soexample.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HotUserAdapter extends BaseAdapter {


    Button btnGz;
    private ArrayList<HotUserBean.DataBean> HotuserList;
    private Context context;

    public HotUserAdapter(List List, Context context) {
        super( List, context );
        this.HotuserList = (ArrayList<HotUserBean.DataBean>) List;
        this.context = context;
    }


    @Override
    protected int getLayout() {
        return R.layout.layout_hotuser;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HotUserBean.DataBean hotUserBean= (HotUserBean.DataBean) data;
        ImageView image_icon = (ImageView) vh.getViewById( R.id.image_icon );
        TextView tv_name = (TextView) vh.getViewById( R.id.tv_name );
        TextView tv_city = (TextView) vh.getViewById( R.id.tv_city );
        Banner banner = (Banner) vh.getViewById( R.id.image_icon_people );
        Button btn_gz = (Button) vh.getViewById( R.id.btn_gz );

        Glide.with( context ).load( hotUserBean.getHeadUrl() ).apply( new RequestOptions().circleCrop() ).into( image_icon );
        TxtUtils.setTextView( tv_name,hotUserBean.getNickName() );
        TxtUtils.setTextView( tv_city,hotUserBean.getCity() );
        List<HotUserBean.DataBean.FileBeanListBean> fileBeanList = hotUserBean.getFileBeanList();

        banner.setImages( fileBeanList ).setImageLoader( new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HotUserBean.DataBean.FileBeanListBean pic= (HotUserBean.DataBean.FileBeanListBean) path;
                String filePath = pic.getFilePath();
                Glide.with( context ).load( filePath ).into( imageView );
            }
        } ).start();



    }
}
