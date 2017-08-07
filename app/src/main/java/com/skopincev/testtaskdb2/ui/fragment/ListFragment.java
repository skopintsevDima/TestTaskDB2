package com.skopincev.testtaskdb2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skopincev.testtaskdb2.BundleConst;
import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.Message;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;

/**
 * Created by skopi on 06.08.2017.
 */

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatsAdapter adapter;

    private String userId;
    private User user;
    private List<Chat> chats = new ArrayList<>();

    public static ListFragment newInstance(String userId) {

        Bundle args = new Bundle();
        args.putString(BundleConst.USER_ID_KEY, userId);

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadUser();
    }

    private void loadUser() {
        userId = getArguments().getString(BundleConst.USER_ID_KEY);
        RealmApi realmApi = new RealmApiImpl();
        user = realmApi.getUserById(userId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_chats);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        loadChats();
        adapter = new ChatsAdapter(getContext(), chats, user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void loadChats() {
        //TODO: load chats from DB instead of hardcode
        User sender = new User(UUID.randomUUID().toString(),
                "Joe Doe",
                "",
                new RealmList<>());

        RealmList<Message> messages = createDialog(sender);

        Chat chat = new Chat(UUID.randomUUID().toString(),
                sender,
                messages);
        RealmApi realmApi = new RealmApiImpl();
        realmApi.putChat(chat);

        int chats_count = 10;
        for (int i = 0; i < chats_count; i++)
            chats.add(chat);
    }

    //TODO: remove hardcode
    private RealmList<Message> createDialog(User sender) {
        RealmList<Message> messages = new RealmList<>();

        //TODO: random unread count
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
}
