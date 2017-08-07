package com.skopincev.testtaskdb2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skopincev.testtaskdb2.R;
import com.skopincev.testtaskdb2.data.model.Message;
import com.skopincev.testtaskdb2.data.model.User;
import com.skopincev.testtaskdb2.resolver.TimeResolver;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by skopi on 07.08.2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private interface VIEW_TYPE {
        int USER_MESSAGE = 1;
        int SENDER_MESSAGE = 2;
    }

    public class UserMessageHolder extends RecyclerView.ViewHolder{

        private TextView tvDate;
        private TextView tvMsg;
        private TextView tvTime;

        public UserMessageHolder(View itemView) {
            super(itemView);

            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }

        public void bind(Message data){
            if (data != null) {
                long timeSend = data.getTimeSend();
                long difference = System.currentTimeMillis() - timeSend;
                String time = data.getTime();
                String msg = data.getText();

                String dateType = TimeResolver.getMessageDateByDifference(difference);
                switch (dateType) {
                    case TimeResolver.DATE_TYPE.NONE:{
                        ((ViewGroup)tvDate.getParent()).removeView(tvDate);
                        break;
                    }
                    case TimeResolver.DATE_TYPE.TODAY:{
                        tvDate.setText(TimeResolver.DATE_TYPE.TODAY);
                        break;
                    }
                    case TimeResolver.DATE_TYPE.YESTERDAY:{
                        tvDate.setText(TimeResolver.DATE_TYPE.YESTERDAY);
                        break;
                    }
                    case TimeResolver.DATE_TYPE.DATE:{
                        String date = TimeResolver.getDateByMilli(timeSend);
                        tvDate.setText(date);
                        break;
                    }
                }
                tvTime.setText(time);
                tvMsg.setText(msg);
            }
        }
    }

    public class SenderMessageHolder extends RecyclerView.ViewHolder{

        private CircleImageView ivPhoto;
        private TextView tvDate;
        private TextView tvMsg;

        public SenderMessageHolder(View itemView) {
            super(itemView);

            ivPhoto = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
        }

        public void bind(Message data){
            if (data != null) {
                long timeSend = data.getTimeSend();
                long difference = System.currentTimeMillis() - timeSend;
                String msg = data.getText();
                String photoPath = data.getSender().getPhotoPath();

                String dateType = TimeResolver.getMessageDateByDifference(difference);
                    switch (dateType) {
                        case TimeResolver.DATE_TYPE.NONE:{
                            ((ViewGroup)tvDate.getParent()).removeView(tvDate);
                            break;
                        }
                        case TimeResolver.DATE_TYPE.TODAY:{
                            tvDate.setText(TimeResolver.DATE_TYPE.TODAY);
                            break;
                        }
                        case TimeResolver.DATE_TYPE.YESTERDAY:{
                            tvDate.setText(TimeResolver.DATE_TYPE.YESTERDAY);
                            break;
                        }
                        case TimeResolver.DATE_TYPE.DATE:{
                            String date = TimeResolver.getDateByMilli(timeSend);
                            tvDate.setText(date);
                            break;
                        }
                }
                tvMsg.setText(msg);
                //TODO: set photo
            }
        }
    }

    private List<Message> items;
    private LayoutInflater inflater;
    private User user;

    public MessagesAdapter(Context context, List<Message> items, User user){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.user = user;
    }

    public void addItem(Message item){
        items.add(item);
        notifyItemInserted(items.size() + 1);
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) != null){
            if (items.get(position).getSender().equals(user))
                return VIEW_TYPE.USER_MESSAGE;
            else
                return VIEW_TYPE.SENDER_MESSAGE;
        } else
            return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case VIEW_TYPE.USER_MESSAGE:{
                view = inflater.inflate(R.layout.item_user_msg, parent, false);
                holder = new UserMessageHolder(view);
                break;
            }
            case VIEW_TYPE.SENDER_MESSAGE:{
                view = inflater.inflate(R.layout.item_sender_msg, parent, false);
                holder = new SenderMessageHolder(view);
                break;
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message data = null;
        if (items != null)
            data = items.get(position);
        if (data != null) {
            switch (holder.getItemViewType()){
                case VIEW_TYPE.USER_MESSAGE:{
                    UserMessageHolder userMessageHolder = (UserMessageHolder) holder;
                    userMessageHolder.bind(data);
                    break;
                }
                case VIEW_TYPE.SENDER_MESSAGE:{
                    SenderMessageHolder senderMessageHolder = (SenderMessageHolder) holder;
                    senderMessageHolder.bind(data);
                    break;
                }
            }
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
