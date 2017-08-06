package com.skopincev.testtaskdb2.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by skopi on 06.08.2017.
 */

public class Message extends RealmObject{
    @PrimaryKey
    private String id;

    private String text;

    private Integer time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Message(){

    }

    public Message(String id,
                   String text,
                   Integer time){
        this.id = id;
        this.text = text;
        this.time = time;
    }
}
