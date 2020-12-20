package com.umeng.soexample.module.data.tongpao;

import java.util.List;

public class DiscussedBean {

    /**
     * status : {"statusCode":100,"message":"请求成功！","serverTime":"2020-08-04 13:41:10"}
     * data : [{"labelId":44,"name":"飞花令","imageUrl":"https://tpcdn.whfpsoft.com:443/File/labels/20190704/748df09273484512c55c8e0a1a1d9ed5.png","depict":"飞花令，原本是古人行酒令时的一个文字游戏，源自古人的诗词之趣，现代经过改良，也变得更加具有趣味性，我们也来玩儿一玩儿飞花令吧~","labelTypeName":"","useTime":351,"attentionNum":278,"type":1,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":497,"name":"你最喜欢的汉服","imageUrl":"https://tpcdn.whfpsoft.com:443/File/labels/20200731/e011ff5477be665399a59c6085e83e9a.png","depict":"你最喜欢的汉服是什么？！一起来种草！参与不定期投票！还有惊喜福利哦~","labelTypeName":"","useTime":97,"attentionNum":28,"type":1,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":499,"name":"礼衣华夏汉服超模大赛","imageUrl":"https://tpcdn.whfpsoft.com:443/File/labels/20200731/c50cc9f1da3c1a1bcb0537b270706ae2.png","depict":"让华夏审美引领T台时尚，让汉服文化重归大众视野","labelTypeName":"","useTime":20,"attentionNum":11,"type":1,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":2,"name":"汉服日常","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/f1465de4fc4b7891229472a882ddae16.png","depict":"原创汉服驿站，欢迎同袍们一起分享汉服日常生活！","labelTypeName":"","useTime":8157,"attentionNum":1186,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":15,"name":"好物安利","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/f8913cc0882fca43e3203e4c50baaa8b.png","depict":"推荐圈里的好物，盘点那些好看到哭的神器...","labelTypeName":"","useTime":4760,"attentionNum":1134,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0},{"labelId":38,"name":"晒晒童年汉服照","imageUrl":"https://tpcdn.whfpsoft.com:443/File/20191219/ffbf87de0de961a6b5d2102e476f23a0.png","depict":"穿梭时光，过一个诗情画意的汉服儿童节~你童年穿上汉服的样子，\u200b是不是特别可爱呢？","labelTypeName":"","useTime":3782,"attentionNum":112,"type":0,"isAttention":0,"createTime":"","number":0,"userID":0,"isHot":0,"isRecommend":0}]
     */

    private StatusBean status;
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * statusCode : 100
         * message : 请求成功！
         * serverTime : 2020-08-04 13:41:10
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

    public static class DataBean {
        /**
         * labelId : 44
         * name : 飞花令
         * imageUrl : https://tpcdn.whfpsoft.com:443/File/labels/20190704/748df09273484512c55c8e0a1a1d9ed5.png
         * depict : 飞花令，原本是古人行酒令时的一个文字游戏，源自古人的诗词之趣，现代经过改良，也变得更加具有趣味性，我们也来玩儿一玩儿飞花令吧~
         * labelTypeName :
         * useTime : 351
         * attentionNum : 278
         * type : 1
         * isAttention : 0
         * createTime :
         * number : 0
         * userID : 0
         * isHot : 0
         * isRecommend : 0
         */

        private int labelId;
        private String name;
        private String imageUrl;
        private String depict;
        private String labelTypeName;
        private String useTime;
        private int attentionNum;
        private int type;
        private int isAttention;
        private String createTime;
        private int number;
        private int userID;
        private int isHot;
        private int isRecommend;

        public int getLabelId() {
            return labelId;
        }

        public void setLabelId(int labelId) {
            this.labelId = labelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDepict() {
            return depict;
        }

        public void setDepict(String depict) {
            this.depict = depict;
        }

        public String getLabelTypeName() {
            return labelTypeName;
        }

        public void setLabelTypeName(String labelTypeName) {
            this.labelTypeName = labelTypeName;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public int getAttentionNum() {
            return attentionNum;
        }

        public void setAttentionNum(int attentionNum) {
            this.attentionNum = attentionNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }
    }
}
