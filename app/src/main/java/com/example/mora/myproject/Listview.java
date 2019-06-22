package com.example.mora.myproject;

import java.util.List;

/**
 * Created by mora on 2017/8/19.
 */

public class Listview {
    private String status;
    private List<userBean> Content;
    public String getStatus() {
        return status;
    }
    public List<userBean> getContent() {
        return Content;
    }
    public class userBean {

        private String Title;
        private String Photo;
        private String subhead;
        private String news;
        private String image_1;
        private String news_2;
        private String image_2;

        public String getTitle() {
            return Title;
        }

        public userBean setTitle(String title) {
            Title = title;
            return this;
        }

        public String getPhoto() {
            return Photo;
        }

        public userBean setPhoto(String photo) {
            Photo = photo;
            return this;
        }

        public String getSubhead() {
            return subhead;
        }

        public userBean setSubhead(String subhead) {
            this.subhead = subhead;
            return this;
        }

        public String getNews() {
            return news;
        }

        public userBean setNews(String news) {
            this.news = news;
            return this;
        }

        public String getImage_1() {
            return image_1;
        }

        public userBean setImage_1(String image_1) {
            this.image_1 = image_1;
            return this;
        }

        public String getNews_2() {
            return news_2;
        }

        public userBean setNews_2(String news_2) {
            this.news_2 = news_2;
            return this;
        }

        public String getImage_2() {
            return image_2;
        }

        public userBean setImage_2(String image_2) {
            this.image_2 = image_2;
            return this;
        }
    }
}
