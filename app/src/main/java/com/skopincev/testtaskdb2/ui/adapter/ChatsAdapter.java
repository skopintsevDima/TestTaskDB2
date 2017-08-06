package com.skopincev.testtaskdb2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.model.Chat;

import java.util.List;

/**
 * Created by skopi on 06.08.2017.
 */

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatHolder> {

    public static class ChatHolder extends RecyclerView.ViewHolder{

        public ChatHolder(View itemView) {
            super(itemView);

            //find views
        }

        public void bind(Chat data){
            if (data != null) {
                //set views data
            }
        }
    }

    private List<Chat> items;
    private LayoutInflater inflater;

    public ChatsAdapter(Context context, List<Chat> items){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    public void addItem(Chat item){
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chat, parent, false);
        ChatHolder holder = new ChatHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Chat data = null;
        if (items != null)
            data = items.get(position);
        if (data != null) {
            holder.bind(data);
        }
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else
            return 0;
    }

}
