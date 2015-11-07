package com.boldijarpaul.itfest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.User;
import com.boldijarpaul.itfest.ui.viewholders.UserViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context mContext;
    private List<User> users = new ArrayList<>();

    public UserAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void addUsers(List<User> users) {
        int newEventIndex = this.users.size();
        this.users.addAll(users);
        notifyItemRangeChanged(newEventIndex, users.size());
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User user = users.get(position);

        holder.name.setText(user.fullName);
        holder.age.setText(user.age + "");


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void clear() {
        users.clear();
        notifyDataSetChanged();
    }


}
