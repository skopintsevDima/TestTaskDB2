package com.skopincev.testtaskdb2.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.ui.adapter.ViewPagerAdapter;
import com.skopincev.testtaskdb2.ui.fragment.EmptyFragment;
import com.skopincev.testtaskdb2.ui.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
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
    }

    private void initViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_action:{
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
