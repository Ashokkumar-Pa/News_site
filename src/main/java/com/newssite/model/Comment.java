package com.newssite.model;

public class Comment {
    String name;
    String time;
    String cmd;
    public String getName()
    {
        return name;
    }
    public String getTime()
    {
        return time;
    }
    public String getCmd()
    {
        return cmd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
