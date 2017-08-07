package com.skopincev.testtaskdb2.data.db;

import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.User;

import java.util.List;

/**
 * Created by skopi on 20.07.2017.
 */

public interface RealmApi {
    void clearDb();

    void putChat(Chat chat);

    Chat getChatById(String id);

    void putUser(User user);

    User getUserById(String id);

    void addChatForUser(Chat chat, User user);

    void removeChatForUserByPosition(int position, User user);
}
