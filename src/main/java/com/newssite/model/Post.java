package com.newssite.model;

public class Post {
    int id;
    String Tittle;
    String Link;
    int Upvote = 0;
    String name;
    String time;
    int commentsCount;

    public int getId() {
        return id;
    }
    public String getTittle() {
        return Tittle;
    }
    public String getLink() {
        return Link;
    }
    public String getName() {
        return name;
    }
    public int getUpvote() {
        return Upvote;
    }
    public String getTime() {
        return time;
    }
    public int getCommentsCount() {
        return commentsCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void setUpvote(int upvote) {
        Upvote = upvote;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}
