package com.skopincev.testtaskdb2.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.skopincev.testtaskdb2.BundleConst;
import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter;
import com.skopincev.testtaskdb2.ui.adapter.ViewPagerAdapter;
import com.skopincev.testtaskdb2.ui.view.NumberTag;

import java.util.UUID;

import io.realm.RealmList;

import static com.skopincev.testtaskdb2.BundleConst.USER_ID_KEY;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private User user;
    private RealmApi realmApi = new RealmApiImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUser();
        initUI();
    }

    private void initUser() {
        SharedPreferences local_pref = getSharedPreferences(BundleConst.SHARED_PREF_NAME, MODE_PRIVATE);
        String userId = local_pref.getString(USER_ID_KEY, "");
        if (userId.equals("")){
            user = new User(UUID.randomUUID().toString(),
                    "Dima Skopintsev",
                    "",
                    new RealmList<Chat>());

            realmApi.putUser(user);

            getSharedPreferences(BundleConst.SHARED_PREF_NAME, MODE_PRIVATE)
                    .edit()
                    .putString(USER_ID_KEY, user.getId())
                    .apply();
        } else {
            user = realmApi.getUserById(userId);
        }
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_head);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.vp_pager);
        initViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setCustomView(adapter.getTabView(0, user.getUnreadCount(), getLayoutInflater()));
        tabLayout.getTabAt(1).setCustomView(adapter.getTabView(1, 15, getLayoutInflater()));
    }

    private void initViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                user,
                () -> updateUnreadMessages(),
                chat -> {
                    realmApi.setChatOpenedState(chat);
                    updateUnreadMessages();
                });
        viewPager.setAdapter(adapter);
    }

    private void updateUnreadMessages() {
        View view = tabLayout.getTabAt(0).getCustomView();
        NumberTag ntUnread = (NumberTag) view.findViewById(R.id.nt_messages);
        ntUnread.setNumber(user.getUnreadCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void launchEmptyActivity(){
        Intent intent = new Intent(this, EmptyActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_action:{
                launchEmptyActivity();
                break;
            }
            case android.R.id.home:{
                launchEmptyActivity();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
