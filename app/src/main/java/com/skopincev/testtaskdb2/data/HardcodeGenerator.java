package com.skopincev.testtaskdb2.data;

import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.Message;
import com.skopincev.testtaskdb2.data.model.User;

import java.util.UUID;

import io.realm.RealmList;

/**
 * Created by skopi on 07.08.2017.
 */

//TODO: remove hardcode
public class HardcodeGenerator {

    public static RealmList<Message> createDialog(User sender, User user) {
        RealmList<Message> messages = new RealmList<>();

        //TODO: get unread count
        Message msg1 = new Message(UUID.randomUUID().toString(),
                "Hello!!!",
                "16:04",
                System.currentTimeMillis(),
                user,
                false);
        Message msg2 = new Message(UUID.randomUUID().toString(),
                "Hi!!!",
                "16:05",
                System.currentTimeMillis(),
                sender,
                false);
        Message msg3 = new Message(UUID.randomUUID().toString(),
                "How are you?",
                "16:05",
                System.currentTimeMillis(),
                user,
                false);
        Message msg4 = new Message(UUID.randomUUID().toString(),
                "I am fine! And you?",
                "16:06",
                System.currentTimeMillis(),
                sender,
                false);

        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);
        messages.add(msg4);

        return messages;
    }

    public static Chat createChat(User user){
        User sender = new User(UUID.randomUUID().toString(),
                "Joe Doe",
                "",
                new RealmList<>());

        RealmList<Message> messages = createDialog(sender, user);

        Chat chat = new Chat(UUID.randomUUID().toString(),
                sender,
                messages);

        return chat;
    }
}
