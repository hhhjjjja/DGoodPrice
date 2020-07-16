package com.hustar.dgoodpricedemo;

public class Data {
    public String name;
    public String title;
    public String contents;
    public String date;

    public Data() {}

    public Data(String name, String title, String contents, String date) {
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
