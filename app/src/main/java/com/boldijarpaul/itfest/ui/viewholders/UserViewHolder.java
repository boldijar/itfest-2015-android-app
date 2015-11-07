package com.boldijarpaul.itfest.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;

/**
 * Created by Browsing on 11/7/2015.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView age;

    public UserViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.item_user_name);
        age = (TextView) itemView.findViewById(R.id.item_user_age);
    }
}
