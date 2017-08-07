package com.skopincev.testtaskdb2.data.model;

import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by skopi on 06.08.2017.
 */

public class User extends RealmObject{
    @PrimaryKey
    private String id;

    private String name;

    private String photoPath;

    private RealmList<Chat> chats = new RealmList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public RealmList<Chat> getChats() {
        return chats;
    }

    public void setChats(RealmList<Chat> chats) {
        this.chats = chats;
    }

    public Integer getUnreadCount() {
        int count = 0;
        for (Chat chat: chats)
            count += chat.getUnread();

        return count;
    }

    public void addChat(Chat chat){
        RealmApi realmApi = new RealmApiImpl();
        realmApi.addChatForUser(chat, this);
    }

    public User(){

    }

    public User(String id,
                String name,
                String photoPath,
                RealmList<Chat> chats){
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.chats = chats;
    }
}
