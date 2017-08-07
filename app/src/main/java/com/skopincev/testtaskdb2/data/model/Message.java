package com.skopincev.testtaskdb2.data.model;

import com.skopincev.testtaskdb2.resolver.TimeResolver;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by skopi on 06.08.2017.
 */

public class Message extends RealmObject{
    @PrimaryKey
    private String id;

    private String text;

    private Long timeSend;

    private User sender;

    private boolean read;

    private long lastMsgDifference;

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

    public String getTime() {
        return TimeResolver.getTimeOfDayByMilli(timeSend);
    }

    public Long getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Long timeSend) {
        this.timeSend = timeSend;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public long getLastMsgDifference() {
        return lastMsgDifference;
    }

    public void setLastMsgDifference(long lastMsgDifference) {
        this.lastMsgDifference = lastMsgDifference;
    }

    public Message(){

    }

    public Message(String id,
                   String text,
                   Long timeSend,
                   User sender,
                   boolean read){
        this.id = id;
        this.text = text;
        this.timeSend = timeSend;
        this.sender = sender;
        this.read = read;
    }
}
