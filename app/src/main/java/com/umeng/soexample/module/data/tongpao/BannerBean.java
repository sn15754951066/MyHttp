package com.umeng.soexample.module.data.tongpao;

import java.util.List;

public class BannerBean {

    /**
     * status : {"statusCode":100,"message":"请求成功！","serverTime":"2020-08-04 15:52:01"}
     * data : {"countNumber":1,"counts":{},"list":[{"homeActivityId":457,"commId":84518,"type":10,"createTime":"2020-07-31 09:33:37","bannerStartTime":"2020-07-31 09:34:00","bannerEndTime":"2020-08-05 00:00:00","banner":"https://tpcdn.whfpsoft.com:443/File/activity/20200731/f6f20a42333f40442a8a3803a6659c4f.png","htmlUrl":"","shareUrl":"","name":"#你最喜欢的汉服#抽送你最喜欢的汉服啦！...","htmlContent":"","state":0}]}
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
         * serverTime : 2020-08-04 15:52:01
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
         * countNumber : 1
         * counts : {}
         * list : [{"homeActivityId":457,"commId":84518,"type":10,"createTime":"2020-07-31 09:33:37","bannerStartTime":"2020-07-31 09:34:00","bannerEndTime":"2020-08-05 00:00:00","banner":"https://tpcdn.whfpsoft.com:443/File/activity/20200731/f6f20a42333f40442a8a3803a6659c4f.png","htmlUrl":"","shareUrl":"","name":"#你最喜欢的汉服#抽送你最喜欢的汉服啦！...","htmlContent":"","state":0}]
         */

        private int countNumber;
        private CountsBean counts;
        private List<ListBean> list;

        public int getCountNumber() {
            return countNumber;
        }

        public void setCountNumber(int countNumber) {
            this.countNumber = countNumber;
        }

        public CountsBean getCounts() {
            return counts;
        }

        public void setCounts(CountsBean counts) {
            this.counts = counts;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class CountsBean {
        }

        public static class ListBean {
            /**
             * homeActivityId : 457
             * commId : 84518
             * type : 10
             * createTime : 2020-07-31 09:33:37
             * bannerStartTime : 2020-07-31 09:34:00
             * bannerEndTime : 2020-08-05 00:00:00
             * banner : https://tpcdn.whfpsoft.com:443/File/activity/20200731/f6f20a42333f40442a8a3803a6659c4f.png
             * htmlUrl :
             * shareUrl :
             * name : #你最喜欢的汉服#抽送你最喜欢的汉服啦！...
             * htmlContent :
             * state : 0
             */

            private int homeActivityId;
            private int commId;
            private int type;
            private String createTime;
            private String bannerStartTime;
            private String bannerEndTime;
            private String banner;
            private String htmlUrl;
            private String shareUrl;
            private String name;
            private String htmlContent;
            private int state;

            public int getHomeActivityId() {
                return homeActivityId;
            }

            public void setHomeActivityId(int homeActivityId) {
                this.homeActivityId = homeActivityId;
            }

            public int getCommId() {
                return commId;
            }

            public void setCommId(int commId) {
                this.commId = commId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getBannerStartTime() {
                return bannerStartTime;
            }

            public void setBannerStartTime(String bannerStartTime) {
                this.bannerStartTime = bannerStartTime;
            }

            public String getBannerEndTime() {
                return bannerEndTime;
            }

            public void setBannerEndTime(String bannerEndTime) {
                this.bannerEndTime = bannerEndTime;
            }

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
            }

            public String getHtmlUrl() {
                return htmlUrl;
            }

            public void setHtmlUrl(String htmlUrl) {
                this.htmlUrl = htmlUrl;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHtmlContent() {
                return htmlContent;
            }

            public void setHtmlContent(String htmlContent) {
                this.htmlContent = htmlContent;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
