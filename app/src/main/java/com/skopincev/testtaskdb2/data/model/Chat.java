package com.skopincev.testtaskdb2.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by skopi on 06.08.2017.
 */

public class Chat extends RealmObject{
    @PrimaryKey
    private String id;

    private User companion;

    private RealmList<Message> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<Message> getMessages() {
        return messages;
    }

    public void setMessages(RealmList<Message> messages) {
        this.messages = messages;
    }

    public Chat(){

    }

    public Chat(String id,
                User companion,
                RealmList<Message> messages){
        this.id = id;
        this.companion = companion;
        this.messages = messages;
    }
}
