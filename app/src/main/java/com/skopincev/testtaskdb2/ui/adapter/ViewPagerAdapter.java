package com.skopincev.testtaskdb2.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skopincev.testtaskdb2.ui.fragment.EmptyFragment;
import com.skopincev.testtaskdb2.ui.fragment.ListFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final int PAGE_COUNT = 2;
    private final String[] PAGE_TITLES =
            {
                    "Chat",
                    "Live chat"
            };

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        initTabs();
    }

    private void initTabs() {

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return ListFragment.newInstance();
            }
            case 1: {
                return EmptyFragment.newInstance();
            }
            default:{
                return ListFragment.newInstance();
            }
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }
}
