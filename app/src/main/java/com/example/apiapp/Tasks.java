package com.example.apiapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Tasks {

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId_user() {
        return id_user;
    }
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    private String title;
    private String body;
    private Integer id_user;
    private Integer id;
    private boolean completed;
    private String key= null;

    public Tasks(String title, String body, Integer id_user, Integer id, boolean completed, String key) {
        this.title = title;
        this.body = body;
        this.id_user = id_user;
        this.id = id;
        this.completed = completed;
        this.key = key;
    }

    public Tasks(){

    }
    public Tasks(String title, String body, Integer id_user, Integer id, boolean completed) {
        this.title = title;
        this.body = body;
        this.id_user = id_user;
        this.id = id;
        this.completed = completed;
    }
}
