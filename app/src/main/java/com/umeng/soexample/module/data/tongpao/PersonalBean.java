package com.umeng.soexample.module.data.tongpao;

import java.io.Serializable;
import java.util.List;

public class PersonalBean implements Serializable {

    /**
     * status : {"statusCode":100,"message":"请求成功！","serverTime":"2020-08-08 16:23:01"}
     * data : {"userID":100001,"peopleNearby":1,"pullWires":1,"activityShow":1,"id":0,"rankName":"","level":8,"rankType":0,"typeName":"","rank":0,"expPrefix":0,"expSuffix":0,"ignb":2,"nickName":"官方小袍","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200404/fa5134d048f08eff6f3617dc35d3a836.jpg","sex":"1","zone":"","phone":"","realState":0,"age":2,"province":"湖北","city":"武汉","expScore":54037,"registerTime":"","accessTime":"","accessToken":"","tongQian":939,"staus":0,"longitude":0,"latitude":0,"locationAddress":"","token":"","refreshToken":"","msnToken":"","openID":"","userSkillList":[],"speaking":0,"skill":"","isReal":0,"accessReadID":0,"onlineTime":"","birthday":"2018-09-28","signature":" 同袍官方QQ贰群:  246325922","idCardNo":"","trueName":"","isContact":0,"dynamicnumber":641,"articelnumber":431,"collectnumber":3,"rewardnumber":25,"activitynumber":41,"orgstatus":1,"contactMe":131039,"myContact":110,"isSign":0,"vueVersion":0,"userAllSkills":[],"likeNumMe":0,"permission":""}
     */

    private StatusBean status;
    private DataBean data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * statusCode : 100
         * message : 请求成功！
         * serverTime : 2020-08-08 16:23:01
         */

        private int statusCode;
        private String message;
        private String serverTime;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getServerTime() {
            return serverTime;
        }

        public void setServerTime(String serverTime) {
            this.serverTime = serverTime;
        }
    }

    public static class DataBean implements Serializable{
        /**
         * userID : 100001
         * peopleNearby : 1
         * pullWires : 1
         * activityShow : 1
         * id : 0
         * rankName :
         * level : 8
         * rankType : 0
         * typeName :
         * rank : 0
         * expPrefix : 0
         * expSuffix : 0
         * ignb : 2
         * nickName : 官方小袍
         * headUrl : https://tpcdn.whfpsoft.com:443/File/headPhoto/20200404/fa5134d048f08eff6f3617dc35d3a836.jpg
         * sex : 1
         * zone :
         * phone :
         * realState : 0
         * age : 2
         * province : 湖北
         * city : 武汉
         * expScore : 54037
         * registerTime :
         * accessTime :
         * accessToken :
         * tongQian : 939
         * staus : 0
         * longitude : 0
         * latitude : 0
         * locationAddress :
         * token :
         * refreshToken :
         * msnToken :
         * openID :
         * userSkillList : []
         * speaking : 0
         * skill :
         * isReal : 0
         * accessReadID : 0
         * onlineTime :
         * birthday : 2018-09-28
         * signature :  同袍官方QQ贰群:  246325922
         * idCardNo :
         * trueName :
         * isContact : 0
         * dynamicnumber : 641
         * articelnumber : 431
         * collectnumber : 3
         * rewardnumber : 25
         * activitynumber : 41
         * orgstatus : 1
         * contactMe : 131039
         * myContact : 110
         * isSign : 0
         * vueVersion : 0
         * userAllSkills : []
         * likeNumMe : 0
         * permission :
         */

        private int userID;
        private int peopleNearby;
        private int pullWires;
        private int activityShow;
        private int id;
        private String rankName;
        private int level;
        private int rankType;
        private String typeName;
        private int rank;
        private int expPrefix;
        private int expSuffix;
        private int ignb;
        private String nickName;
        private String headUrl;
        private String sex;
        private String zone;
        private String phone;
        private int realState;
        private int age;
        private String province;
        private String city;
        private int expScore;
        private String registerTime;
        private String accessTime;
        private String accessToken;
        private int tongQian;
        private int staus;
        private int longitude;
        private int latitude;
        private String locationAddress;
        private String token;
        private String refreshToken;
        private String msnToken;
        private String openID;
        private int speaking;
        private String skill;
        private int isReal;
        private int accessReadID;
        private String onlineTime;
        private String birthday;
        private String signature;
        private String idCardNo;
        private String trueName;
        private int isContact;
        private int dynamicnumber;
        private int articelnumber;
        private int collectnumber;
        private int rewardnumber;
        private int activitynumber;
        private int orgstatus;
        private int contactMe;
        private int myContact;
        private int isSign;
        private int vueVersion;
        private int likeNumMe;
        private String permission;
        private List<?> userSkillList;
        private List<?> userAllSkills;

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getPeopleNearby() {
            return peopleNearby;
        }

        public void setPeopleNearby(int peopleNearby) {
            this.peopleNearby = peopleNearby;
        }

        public int getPullWires() {
            return pullWires;
        }

        public void setPullWires(int pullWires) {
            this.pullWires = pullWires;
        }

        public int getActivityShow() {
            return activityShow;
        }

        public void setActivityShow(int activityShow) {
            this.activityShow = activityShow;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRankName() {
            return rankName;
        }

        public void setRankName(String rankName) {
            this.rankName = rankName;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRankType() {
            return rankType;
        }

        public void setRankType(int rankType) {
            this.rankType = rankType;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getExpPrefix() {
            return expPrefix;
        }

        public void setExpPrefix(int expPrefix) {
            this.expPrefix = expPrefix;
        }

        public int getExpSuffix() {
            return expSuffix;
        }

        public void setExpSuffix(int expSuffix) {
            this.expSuffix = expSuffix;
        }

        public int getIgnb() {
            return ignb;
        }

        public void setIgnb(int ignb) {
            this.ignb = ignb;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRealState() {
            return realState;
        }

        public void setRealState(int realState) {
            this.realState = realState;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getExpScore() {
            return expScore;
        }

        public void setExpScore(int expScore) {
            this.expScore = expScore;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getAccessTime() {
            return accessTime;
        }

        public void setAccessTime(String accessTime) {
            this.accessTime = accessTime;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public int getTongQian() {
            return tongQian;
        }

        public void setTongQian(int tongQian) {
            this.tongQian = tongQian;
        }

        public int getStaus() {
            return staus;
        }

        public void setStaus(int staus) {
            this.staus = staus;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public String getLocationAddress() {
            return locationAddress;
        }

        public void setLocationAddress(String locationAddress) {
            this.locationAddress = locationAddress;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getMsnToken() {
            return msnToken;
        }

        public void setMsnToken(String msnToken) {
            this.msnToken = msnToken;
        }

        public String getOpenID() {
            return openID;
        }

        public void setOpenID(String openID) {
            this.openID = openID;
        }

        public int getSpeaking() {
            return speaking;
        }

        public void setSpeaking(int speaking) {
            this.speaking = speaking;
        }

        public String getSkill() {
            return skill;
        }

        public void setSkill(String skill) {
            this.skill = skill;
        }

        public int getIsReal() {
            return isReal;
        }

        public void setIsReal(int isReal) {
            this.isReal = isReal;
        }

        public int getAccessReadID() {
            return accessReadID;
        }

        public void setAccessReadID(int accessReadID) {
            this.accessReadID = accessReadID;
        }

        public String getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(String onlineTime) {
            this.onlineTime = onlineTime;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public int getIsContact() {
            return isContact;
        }

        public void setIsContact(int isContact) {
            this.isContact = isContact;
        }

        public int getDynamicnumber() {
            return dynamicnumber;
        }

        public void setDynamicnumber(int dynamicnumber) {
            this.dynamicnumber = dynamicnumber;
        }

        public int getArticelnumber() {
            return articelnumber;
        }

        public void setArticelnumber(int articelnumber) {
            this.articelnumber = articelnumber;
        }

        public int getCollectnumber() {
            return collectnumber;
        }

        public void setCollectnumber(int collectnumber) {
            this.collectnumber = collectnumber;
        }

        public int getRewardnumber() {
            return rewardnumber;
        }

        public void setRewardnumber(int rewardnumber) {
            this.rewardnumber = rewardnumber;
        }

        public int getActivitynumber() {
            return activitynumber;
        }

        public void setActivitynumber(int activitynumber) {
            this.activitynumber = activitynumber;
        }

        public int getOrgstatus() {
            return orgstatus;
        }

        public void setOrgstatus(int orgstatus) {
            this.orgstatus = orgstatus;
        }

        public int getContactMe() {
            return contactMe;
        }

        public void setContactMe(int contactMe) {
            this.contactMe = contactMe;
        }

        public int getMyContact() {
            return myContact;
        }

        public void setMyContact(int myContact) {
            this.myContact = myContact;
        }

        public int getIsSign() {
            return isSign;
        }

        public void setIsSign(int isSign) {
            this.isSign = isSign;
        }

        public int getVueVersion() {
            return vueVersion;
        }

        public void setVueVersion(int vueVersion) {
            this.vueVersion = vueVersion;
        }

        public int getLikeNumMe() {
            return likeNumMe;
        }

        public void setLikeNumMe(int likeNumMe) {
            this.likeNumMe = likeNumMe;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public List<?> getUserSkillList() {
            return userSkillList;
        }

        public void setUserSkillList(List<?> userSkillList) {
            this.userSkillList = userSkillList;
        }

        public List<?> getUserAllSkills() {
            return userAllSkills;
        }

        public void setUserAllSkills(List<?> userAllSkills) {
            this.userAllSkills = userAllSkills;
        }
    }
}
