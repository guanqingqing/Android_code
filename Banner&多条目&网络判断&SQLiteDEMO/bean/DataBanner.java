package com.example.guan.lian_manhua.bean;

import java.util.List;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/25 20:37
 */

public class DataBanner {

    private List<NewsBean> news;
    private List<BannerBean> banner;

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class NewsBean {
        /**
         * description : 2017年已经到来，DOTA2再一次向全世界的艺术家们发出号召，向DOTA2创意工坊递交中国新年/中国历史或鸡年相关元素的作品，我们在创意工坊内选取了不少已经上传的优秀中国风作品，同时其他玩家不要忘记前往创意工坊，在您的投票队列里为作品投票，确保大家能听到您的心声，这也会大大增加这些优秀作品进入游戏内的机会，让全世界的DOTA2玩家感受到属于中国的优秀饰品和套装。
         * title : 2017年新春征集优秀作品展示：让中国风走向世界
         * nid : 192741
         * background : http://img.dota2.com.cn/dota2/8f/a5/8fa56991e088b3f26b0e43a8721b59621483777281.jpg
         * time : 2017-01-07
         * date : 20170107
         */

        private String description;
        private String title;
        private String nid;
        private String background;
        private String time;
        private String date;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class BannerBean {
        /**
         * description : 文章中包含小地图完整版、野怪指示图、神符刷新点、圣坛指示图。
         * title : 【电竞虎推荐】从小地图看7.0版本野怪、圣坛分布及神符刷新点
         * nid : 22356
         * background : http://img.dota2.com.cn/dota2/65/e1/65e1b75f6bb55eb439eb3b4075cf358a1481544287.jpg
         * time : 2016-12-12
         * date : news
         */

        private String description;
        private String title;
        private String nid;
        private String background;
        private String time;
        private String date;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
