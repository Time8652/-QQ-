package com.example.myqq.News;

public class TopicNew {

    public int head_url, work_url;
    public String name, time, text;
    public int star;

    public TopicNew(int head_url, int work_url, String name, String time, String text, int star) {
        this.head_url = head_url;
        this.name = name;
        this.time = time;
        this.text = text;
        this.work_url = work_url;
        this.star = star;
    }
}
