package com.umeng.soexample.utils;


import com.umeng.soexample.module.data.EMUserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoManager {

    private List<EMUserInfo> userList = new ArrayList<>();

    private static UserInfoManager infoManager;

    public static UserInfoManager getInstance(){
        if(infoManager == null){
            infoManager = new UserInfoManager();
        }
        return infoManager;
    }

    /**
     * 添加用户信息
     * @param list
     */
    public void addUsers(List<EMUserInfo> list){
        userList.clear();
        userList.addAll(list);
    }

    /**
     * 获取所有的用户信息
     * @return
     */
    public List<EMUserInfo> getAllUsers(){
        return userList;
    }

    /**
     * 通过uid查找用户信息
     * @param uid
     * @return
     */
    public EMUserInfo getUserInfoByUid(String uid){
        for(EMUserInfo item:userList){
            if(item.getUid().equals(uid)){
                return item;
            }
        }
        return null;
    }
}
