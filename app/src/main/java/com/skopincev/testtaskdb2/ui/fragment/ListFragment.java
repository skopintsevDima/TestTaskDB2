package com.skopincev.testtaskdb2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skopincev.testtaskdb2.R;
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

    private List<Chat> chats = new ArrayList<>();

    public static ListFragment newInstance() {

        Bundle args = new Bundle();

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        loadChats();
        adapter = new ChatsAdapter(getContext(), chats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void loadChats() {
        //TODO: load chats from DB instead of hardcode
        User sender = new User(UUID.randomUUID().toString(),
                "Joe Doe",
                "",
                new RealmList<Chat>());
        Message msg = new Message(UUID.randomUUID().toString(),
                "Hello world!!!",
                "16:04",
                System.currentTimeMillis(),
                sender,
                false);
        RealmList<Message> messages = new RealmList<>();
        messages.add(msg);
        Chat chat1 = new Chat(UUID.randomUUID().toString(),
                sender,
                messages);
        Chat chat2 = new Chat(UUID.randomUUID().toString(),
                sender,
                messages);
        Chat chat3 = new Chat(UUID.randomUUID().toString(),
                sender,
                messages);
        chats.add(chat1);
        chats.add(chat2);
        chats.add(chat3);
    }
}
