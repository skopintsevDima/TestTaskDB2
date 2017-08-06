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
import com.skopincev.testtaskdb2.ui.adapter.ChatsAdapter;

/**
 * Created by skopi on 06.08.2017.
 */

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;

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
//        loadChats();
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_surveys);
//         adapter = new ChatsAdapter(this, surveys);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
    }

    private void loadChats() {

    }
}
