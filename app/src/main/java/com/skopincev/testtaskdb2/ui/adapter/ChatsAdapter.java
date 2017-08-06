package com.skopincev.testtaskdb2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.ui.view.NumberTag;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by skopi on 06.08.2017.
 */

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatHolder> {

    public static class ChatHolder extends RecyclerView.ViewHolder{

        public SwipeRevealLayout swipeRevealLayout;
        private CircleImageView ivAvatar;
        private TextView tvName;
        private TextView tvLastMsg;
        private TextView tvTime;
        private NumberTag ntMessages;

        public ChatHolder(View itemView) {
            super(itemView);

            swipeRevealLayout = (SwipeRevealLayout) itemView.findViewById(R.id.srl_swipe);
            ivAvatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvLastMsg = (TextView) itemView.findViewById(R.id.tv_last_msg);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ntMessages = (NumberTag) itemView.findViewById(R.id.nt_messages);
        }

        public void bind(Chat data){
            if (data != null) {
                String photoPath = data.getCompanion().getPhotoPath();
                String name = data.getCompanion().getName();
                String lastMsg = data.getMessages().last().getText();
                String time = data.getMessages().last().getTime();
                int unread= data.getUnread();

                //TODO: set photo
                tvName.setText(name);
                tvLastMsg.setText(lastMsg);
                tvTime.setText(time);
                ntMessages.setNumber(unread);
            }
        }
    }

    private List<Chat> items;
    private LayoutInflater inflater;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public ChatsAdapter(Context context, List<Chat> items){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        viewBinderHelper.setOpenOnlyOne(true);
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
            viewBinderHelper.bind(holder.swipeRevealLayout, data.getId());
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
