package com.example.demo.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

public class MessagePic extends BaseMessage {
    private int ArticleCount;
    private List<Item> Articles;

    public List<Item> getArticles() {
        return Articles;
    }

    public void setArticles(List<Item> articles) {
        Articles = articles;
    }

    public MessagePic() {
    }

    public MessagePic(int articleCount, List<Item> articles) {
        ArticleCount = articleCount;
        Articles = articles;

    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }


    public static class Item{
        private String Title;
        private String Description;
        private String PicUrl;
        private String Url;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getPicUrl() {
            return PicUrl;
        }

        public void setPicUrl(String picUrl) {
            PicUrl = picUrl;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }
    }


}
