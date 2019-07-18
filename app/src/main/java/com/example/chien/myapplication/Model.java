package com.example.chien.myapplication;

import com.google.gson.annotations.SerializedName;

public class Model {

    int id;
    String date;
    String title;

    public Model(int id, String date, String title) {
        this.id = id;
        this.date = date;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
