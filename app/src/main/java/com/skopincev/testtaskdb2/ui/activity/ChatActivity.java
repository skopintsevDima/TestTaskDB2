package com.skopincev.testtaskdb2.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.skopincev.testtaskdb2.BundleConst;
import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.Message;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.adapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessagesAdapter adapter;

    private String userId;
    private User user;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        loadUser();
        initUI();
    }

    private void loadUser() {
        userId = getIntent().getStringExtra(BundleConst.USER_ID_KEY);
        RealmApi realmApi = new RealmApiImpl();
        user = realmApi.getUserById(userId);
    }

    private void initUI() {
        String title = getIntent().getStringExtra(BundleConst.COMPANION_NAME_KEY);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rv_messages);

        initRecyclerView();
    }

    private void initRecyclerView() {
        loadMessages();
        adapter = new MessagesAdapter(this, messages, user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadMessages() {
        String chatId = getIntent().getStringExtra(BundleConst.CHAT_ID_KEY);
        RealmApi realmApi = new RealmApiImpl();
        Chat chat = realmApi.getChatById(chatId);
        messages = chat.getMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_block:{
                //TODO: some action will be here
                break;
            }
            case android.R.id.home:{
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
