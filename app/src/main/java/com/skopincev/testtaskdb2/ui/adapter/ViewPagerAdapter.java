package com.skopincev.testtaskdb2.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter.OnChatOpenListener;
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter.OnChatsChangeListener;
import com.skopincev.testtaskdb2.ui.fragment.EmptyFragment;
import com.skopincev.testtaskdb2.ui.fragment.ListFragment;
import com.skopincev.testtaskdb2.ui.view.NumberTag;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final int PAGE_COUNT = 2;
    private final String[] PAGE_TITLES =
            {
                    "Chat",
                    "Live chat"
            };
    private User user;
    private OnChatsChangeListener onChatsChangeListener;
    private OnChatOpenListener onChatOpenListener;

    public ViewPagerAdapter(FragmentManager fm,
                            User user,
                            OnChatsChangeListener onChatsChangeListener,
                            OnChatOpenListener onChatOpenListener) {
        super(fm);
        this.user = user;
        this.onChatsChangeListener = onChatsChangeListener;
        this.onChatOpenListener = onChatOpenListener;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                ListFragment fragment = ListFragment.newInstance();
                fragment.setOnChatsChangeListener(onChatsChangeListener);
                fragment.setOnChatOpenListener(onChatOpenListener);
                fragment.setUser(user);
                return fragment;
            }
            case 1: {
                return EmptyFragment.newInstance();
            }
            default:{
                ListFragment fragment = ListFragment.newInstance();
                fragment.setOnChatsChangeListener(onChatsChangeListener);
                fragment.setOnChatOpenListener(onChatOpenListener);
                fragment.setUser(user);
                return fragment;
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

    public View getTabView(int position, int messages, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.tab_layout, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        NumberTag ntMessages = (NumberTag) view.findViewById(R.id.nt_messages);

        String title = PAGE_TITLES[position];
        tvTitle.setText(title);

        ntMessages.setNumber(messages);

        return view;
    }
}
