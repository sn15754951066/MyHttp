package com.umeng.soexample.ui.tongpao.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.RecommendBean;
import com.umeng.soexample.ui.tongpao.HomeDetailsPage.DetailsActivity;
import com.umeng.soexample.ui.tongpao.HomePersonalPprofile.PersonalActivity;
import com.umeng.soexample.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecommmendAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<RecommendBean.DataBean> recommendList;
    //是否点击展开
    private boolean isshow;
    private RecommendBean.DataBean dataBean;

    public RecommmendAdapter(List mData, Context context) {
        super( mData, context );
        this.context = context;
        recommendList = (ArrayList<RecommendBean.DataBean>) mData;

    }


    @Override
    protected int getLayout() {
        return R.layout.layout_recommend_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {


        dataBean = (RecommendBean.DataBean) data;

        ImageView image_head = (ImageView) vh.getViewById( R.id.image_hend );
        TextView tv_name = (TextView) vh.getViewById( R.id.tv_name );
        Button btn_attention = (Button) vh.getViewById( R.id.btn_attention );
        TextView tv_desc = (TextView) vh.getViewById( R.id.tv_desc );
        TextView tv_data = (TextView) vh.getViewById( R.id.tv_data );
        TextView tv_show_more = (TextView) vh.getViewById( R.id.tv_show_more );
        NineGridImageView nv = (NineGridImageView) vh.getViewById( R.id.nv );

        String desc = dataBean.getPostDetail().getContent();

        Glide.with( context ).load( dataBean.getPostDetail().getHeadUrl() ).apply( new RequestOptions().circleCrop() ).into( image_head );
        TxtUtils.setTextView( tv_name, dataBean.getPostDetail().getNickName() );
        TxtUtils.setTextView( tv_desc, dataBean.getPostDetail().getContent() );
        TxtUtils.setTextView( tv_data, dataBean.getPostDetail().getCreateTime() );

        //点击头像跳转页面
        image_head.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到个人简介
                Intent intent = new Intent( context, PersonalActivity.class );
                intent.putExtra( "HeaderUrl",dataBean.getPostDetail().getHeadUrl() );
                intent.putExtra( "NickName",dataBean.getPostDetail().getNickName() );
                context.startActivity( intent );

            }
        } );
        //设置九宫格
        ArrayList<String> imgs = new ArrayList<>();
        for (RecommendBean.DataBean.PostDetailBean.ImagesBean item : dataBean.getPostDetail().getImages()) {
            imgs.add( item.getFilePath() );
        }
        //设置九宫格
        nv.setAdapter( new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                //加载图片
                Glide.with( context ).load( o ).apply( RequestOptions.bitmapTransform( new RoundedCorners( 20 ) ) ).into( imageView );
            }

            //大图
            @Override
            protected void onItemImageClick(Context context, int index, List list) {
                super.onItemImageClick( context, index, list );
                //跳转页面 传值
                /*Intent intent = new Intent(context, BigImagectivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("postion", index);
                bundle.putStringArrayList("urls",imgs);
                intent.putExtra("data", bundle);
                context.startActivity(intent);*/

                //弹出popupWindow
                setPopWindow( index, list );
            }
        } );
        //设置九宫格图片
        nv.setImagesData( imgs );

//富文本 第一段
        SpannableStringBuilder builder = new SpannableStringBuilder( desc );
        String re = "#[^#]*#";
        Pattern pattern = Pattern.compile( re );
        Matcher matcher = pattern.matcher( desc );
        int s = 0;
        int e = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            s = start;
            e = end;
            //设置文字前景色
            builder.setSpan( new ForegroundColorSpan( Color.BLUE ), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
            ClickableSpan span = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {
                    //跳转到详情页面
                    Intent intent = new Intent( context, DetailsActivity.class );
                    intent.putExtra( "commends", dataBean );
                    context.startActivity( intent );
                }
            };
            builder.setSpan( span, s, e, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
        }
        //使用ClickableSpan的文本如果想真正实现点击作用，必须为TextView设置setMovementMethod方法
        tv_desc.setMovementMethod( LinkMovementMethod.getInstance() );
        tv_desc.setText( builder );

        //第二段
        String exp_2 = "@.+?\\s";
        Pattern pattern1 = Pattern.compile( exp_2 );
        Matcher matcher1 = pattern1.matcher( desc );
        int s1 = 0;
        int e1 = 0;
        while (matcher1.find()) {
            int start = matcher1.start();
            int end = matcher1.end();
            //只能点击这部分区域才有反应，设置点击区域
            s1 = start;
            e1 = end;
            builder.setSpan( new ForegroundColorSpan( Color.BLUE ), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
            ClickableSpan span1 = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {
                    Toast.makeText( context, "111", Toast.LENGTH_SHORT ).show();
                }
            };
            builder.setSpan( span1, s1, e1, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
        }

        //文字点击收起展开 第二种方式
        tv_desc.setLines( 3 );//最多显示3行
//点击收起/展开的监听
        tv_show_more.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isshow) {
                    //isshow=false;
                    tv_desc.setEllipsize( TextUtils.TruncateAt.END );//收起
                    tv_desc.setLines( 3 );//最多显示3行

                    tv_show_more.setText( "展开" );
                } else {
                    // isshow=true;
                    //  tv_des.setEllipsize(TextUtils.TruncateAt.END);//收起
                    tv_desc.setEllipsize( null );//展开
                    tv_desc.setSingleLine( false );//这个方法是必须设置的，否则无法展开
                    tv_show_more.setText( "收起" );
                }
                isshow = !isshow;
            }
        } );
    }

    private void setPopWindow(int index, List list) {
        //接口回调 到fragment 设置背景透明度
        if (iOnClickItem != null) {
            iOnClickItem.iOnClickItem( 1.5f );
        }

        View view = LayoutInflater.from( context ).inflate( R.layout.image_pop, null );
        PopupWindow popupWindow = new PopupWindow( view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );
        popupWindow.setBackgroundDrawable( new ColorDrawable() );
        popupWindow.setOutsideTouchable( true );

        popupWindow.setOnDismissListener( new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //接口回调到fragment 设置背景
                if (iOnClickItem != null) {
                    iOnClickItem.iOnClickItem( 1.0f );
                }

            }
        } );

        popupWindow.showAtLocation( view, Gravity.CENTER, 0, 0 );
        //获取控价


        ImageView iv_callBack = view.findViewById( R.id.iv_callback );
        TextView iv_page = view.findViewById( R.id.tv_page );
        ImageView iv_down = view.findViewById( R.id.iv_down );
        ViewPager vp_images = view.findViewById( R.id.vp_images );
        Button btn_return = view.findViewById( R.id.btn_return );

        vp_images.setAdapter( new BigImageAdapter( context, list ) );
        vp_images.setCurrentItem( index );//设置viewPager 显示指定页面（点击哪个显示哪张）
        int item = vp_images.getCurrentItem();
        iv_page.setText( (item + 1) + "/" + list.size() );

        vp_images.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int item = vp_images.getCurrentItem();
                iv_page.setText( (item + 1) + "/" + list.size() );

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );

        //返回
        btn_return.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        } );

        //图片
        iv_callBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        } );


    }

    public interface IOnClickItem {
        void iOnClickItem(float v);
    }

    IOnClickItem iOnClickItem;

    public void setIOnClickItem(final IOnClickItem iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
    }
}
