package com.umeng.soexample.interfaces.tongpao;

import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.IBasePersenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.tongpao.BannerBean;
import com.umeng.soexample.module.data.tongpao.DiscussedBean;
import com.umeng.soexample.module.data.tongpao.HotUserBean;
import com.umeng.soexample.module.data.tongpao.PersonalBean;
import com.umeng.soexample.module.data.tongpao.RecommendBean;

/**
 * 同袍首页推荐功能接口锲约类
 */
public interface IRecommend {

    interface View extends IBaseView{

        //定义一个被推荐页实现的View层接口方法
        void getRecommendReturn(RecommendBean result);

        //banner
        void getBannerReturn(BannerBean result);

        //discussed
        void getDiscussedReturn(DiscussedBean result);
        //热门用户
        void getHotUserReturn(HotUserBean result);

        void getPersonalReturn(PersonalBean result);

    }

    interface Persenter extends IBasePersenter<View>{

        //定义一个首页推荐页面V层调用的接口
        void getRecommend();

        void getBanner();

        void getDiscuessed();

        void getHotUser();

        void getPersonal();
    }

    interface Model extends IModel{

        //定义一个加载推荐数据的接口方法 被P层
        void loadRecommend(Callback callback);

        void getBean(Callback callback);

        void getDiscuessed(Callback callback);

        void getHotUser(Callback callback);

        void getPersonal(Callback callback);
    }


}
