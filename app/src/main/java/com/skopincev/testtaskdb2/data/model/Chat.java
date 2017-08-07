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

    private RealmList<Message> messages = new RealmList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCompanion() {
        return companion;
    }

    public void setCompanion(User companion) {
        this.companion = companion;
    }

    public RealmList<Message> getMessages() {
        return messages;
    }

    public void setMessages(RealmList<Message> messages) {
        this.messages = messages;
    }

    public Integer getUnread() {
        int count = 0;
        for (Message message: messages)
            if (!message.isRead())
                count++;
        return count;
    }

    public void addMessage(Message msg){
        messages.add(msg);
    }

    public Chat(){

    }

    public Chat(String id,
                User companion) {
        this.id = id;
        this.companion = companion;
    }
}
