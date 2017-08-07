package com.skopincev.testtaskdb2.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.skopincev.testtaskdb2.BundleConst;
import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.db.RealmApi;
import com.skopincev.testtaskdb2.data.db.RealmApiImpl;
import com.skopincev.testtaskdb2.data.model.Chat;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.ui.activity.ChatActivity;
import com.skopincev.testtaskdb2.ui.view.NumberTag;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by skopi on 06.08.2017.
 */

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatHolder> {

    public class ChatHolder extends RecyclerView.ViewHolder{

        public SwipeRevealLayout swipeRevealLayout;
        private CircleImageView ivAvatar;
        private TextView tvName;
        private TextView tvLastMsg;
        private TextView tvTime;
        private ImageButton ibOpenChat;
        private NumberTag ntMessages;
        private Button btnRemove;

        private Context context;

        public ChatHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            swipeRevealLayout = (SwipeRevealLayout) itemView.findViewById(R.id.srl_swipe);
            ivAvatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvLastMsg = (TextView) itemView.findViewById(R.id.tv_last_msg);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ibOpenChat = (ImageButton) itemView.findViewById(R.id.ib_open);
            ntMessages = (NumberTag) itemView.findViewById(R.id.nt_messages);
            btnRemove = (Button) itemView.findViewById(R.id.btn_remove);
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
                ibOpenChat.setOnClickListener(button -> openChat(data));
                btnRemove.setOnClickListener(button -> removeItem(getPosition()));
            }
        }

        private void openChat(Chat data) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra(BundleConst.CHAT_ID_KEY, data.getId());
            intent.putExtra(BundleConst.COMPANION_NAME_KEY, data.getCompanion().getName());
            intent.putExtra(BundleConst.USER_ID_KEY, user.getId());
            context.startActivity(intent);
            onChatOpenListener.onOpen(data);
        }
    }

    public interface OnChatOpenListener{
        void onOpen(Chat chat);
    }

    public interface OnChatsChangeListener {
        void onChanged();
    }

    private List<Chat> items;
    private LayoutInflater inflater;
    private User user;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private RealmApi realmApi = new RealmApiImpl();
    private OnChatsChangeListener onChatsChangeListener;
    private OnChatOpenListener onChatOpenListener;

    public ChatsAdapter(Context context,
                        User user,
                        OnChatsChangeListener onChatsChangeListener,
                        OnChatOpenListener onChatOpenListener){
        this.inflater = LayoutInflater.from(context);
        this.user = user;
        this.items = user.getChats();
        this.onChatsChangeListener = onChatsChangeListener;
        this.onChatOpenListener = onChatOpenListener;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public void addItem(Chat item){
        items.add(item);
        notifyItemInserted(items.size() + 1);
    }

    public void removeItem(int position){
        realmApi.removeChatForUserByPosition(position, user);
        onChatsChangeListener.onChanged();
        notifyItemRemoved(position);
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
