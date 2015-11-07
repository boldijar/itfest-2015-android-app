package com.boldijarpaul.itfest.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;

/**
 * Created by Browsing on 11/7/2015.
 */
public class AnswerViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView difficulty;
    public ImageView difficultyImage;

    public AnswerViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.item_answer_name);
        difficulty = (TextView) itemView.findViewById(R.id.item_answer_difficulty);
        difficultyImage = (ImageView) itemView.findViewById(R.id.item_answer_difficulty_image);
    }
}
