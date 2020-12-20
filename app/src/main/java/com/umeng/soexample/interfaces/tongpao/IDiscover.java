package com.umeng.soexample.interfaces.tongpao;


import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.IBasePersenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.tongpao.DiscoverBean;

/**
 * 同袍发现推荐功能接口锲约类
 */
public interface IDiscover {

    interface View extends IBaseView {
        //定义一个被发现页实现的View层接口方法
        void getDiscoverReturn(DiscoverBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个首页推荐页面V层调用的接口
        void getDiscover();
    }

    interface Model extends IModel {
        //定义一个加载发现数据的接口方法 被P层
        void getDiscover(Callback callback);
    }

}
