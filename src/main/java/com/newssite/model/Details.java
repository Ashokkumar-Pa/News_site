package com.newssite.model;

import java.util.ArrayList;
import java.util.List;

public class Details {
    int id;
    String Tittle;
    String Link;
    int Upvote;
    String name;
    String time;
    int commentsCount;
    List<Comment> comments = new ArrayList<Comment>();
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
    public List<Comment> getCommentText()
    {
       return comments;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTittle(String tittle) {
        this.Tittle = tittle;
    }
    public void setLink(String link) {
        this.Link = link;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setUpvote(int upvote) {
        this.Upvote = upvote;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
