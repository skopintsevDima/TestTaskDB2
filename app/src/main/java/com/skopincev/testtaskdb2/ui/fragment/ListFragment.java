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
import com.skopincev.testtaskdb2.data.HardcodeGenerator;
import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter.OnChatOpenListener;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter.OnChatsChangeListener;

/**
 * Created by skopi on 06.08.2017.
 */

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatsAdapter adapter;
    private OnChatsChangeListener onChatsChangeListener;
    private OnChatOpenListener onChatOpenListener;

    private User user;
    private RealmApi realmApi = new RealmApiImpl();

    public void setUser(User user) {
        this.user = user;
    }

    public void setOnChatsChangeListener(OnChatsChangeListener onChatsChangeListener) {
        this.onChatsChangeListener = onChatsChangeListener;
    }

    public void setOnChatOpenListener(OnChatOpenListener onChatOpenListener) {
        this.onChatOpenListener = onChatOpenListener;
    }

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
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_chats);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        initRecyclerView();
    }

    private void initRecyclerView() {
        loadChats();
        adapter = new ChatsAdapter(
                getActivity(),
                user,
                onChatsChangeListener,
                onChatOpenListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void loadChats() {
        if (user.getChats().size() == 0){
            Chat chat = HardcodeGenerator.createChat(user);
            Chat simpleChat = HardcodeGenerator.createSimpleChat(user);

            realmApi.putChat(chat);
            realmApi.putChat(simpleChat);

            int chats_count = 5;
            for (int i = 0; i < chats_count; i++) {
                user.addChat(chat);
                user.addChat(simpleChat);
            }
            onChatsChangeListener.onChanged();
        }
    }
}
