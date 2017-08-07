package com.skopincev.testtaskdb2.data.db;

import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.User;


import io.realm.Realm;

/**
 * Created by skopi on 20.07.2017.
 */

public class RealmApiImpl implements RealmApi {

    private Realm realm;

    public RealmApiImpl(){
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void clearDb() {
        realm.executeTransaction(rm -> {
            rm.deleteAll();
        });
    }

    @Override
    public void putChat(Chat chat) {
        realm.executeTransaction(rm -> {
            rm.copyToRealmOrUpdate(chat);
        });
    }

    @Override
    public Chat getChatById(String id) {
        Chat chat = realm.where(Chat.class)
                .equalTo("id", id)
                .findFirst();
        return chat;
    }

    @Override
    public void putUser(User user) {
        realm.executeTransaction(rm -> {
            rm.copyToRealmOrUpdate(user);
        });
    }

    @Override
    public User getUserById(String id) {
        User user = realm.where(User.class)
                .equalTo("id", id)
                .findFirst();
        return user;
    }
}
