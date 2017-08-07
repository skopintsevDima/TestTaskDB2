package com.skopincev.testtaskdb2.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.adapter.ViewPagerAdapter;

import java.util.UUID;

import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUser();
        initUI();
    }

    private void initUser() {
        user = new User(UUID.randomUUID().toString(),
                "Dima Skopintsev",
                "",
                new RealmList<Chat>());
        RealmApi realmApi = new RealmApiImpl();
        realmApi.putUser(user);
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_head);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_pager);
        initViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setCustomView(adapter.getTabView(0, user.getUnreadCount(), getLayoutInflater()));
        tabLayout.getTabAt(1).setCustomView(adapter.getTabView(1, 15, getLayoutInflater()));
    }

    private void initViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), user);
        viewPager.setAdapter(adapter);
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
