package com.boldijarpaul.itfest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.ui.viewholders.QuizViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {

    private Context mContext;
    private List<Quiz> quizes = new ArrayList<>();
    private ItemListener mListener;

    public QuizAdapter(ItemListener mListener, Context mContext) {
        this.mListener = mListener;
        this.mContext = mContext;
    }


    public void addQuizes(List<Quiz> newQuizes) {
        int newEventIndex = this.quizes.size();
        quizes.addAll(newQuizes);
        notifyItemRangeChanged(newEventIndex, newQuizes.size());
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz, parent, false);
        QuizViewHolder viewHolder = new QuizViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        final Quiz quiz = quizes.get(position);
        holder.difficulty.setText(mContext.getString(R.string.msg_difficulty) + quiz.difficulty.toLowerCase());
        holder.name.setText(quiz.name);
        holder.difficultyImage.setImageResource(getImageResourceFromCategory(quiz.difficulty));
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlayClicked(quiz);
            }
        });
    }

    private int getImageResourceFromCategory(String difficulty) {
        if (difficulty == null) return R.drawable.ic_easy;
        if (difficulty.equals(mContext.getString(R.string.cat_easy))) return R.drawable.ic_easy;
        if (difficulty.equals(mContext.getString(R.string.cat_medium))) return R.drawable.ic_medium;
        if (difficulty.equals(mContext.getString(R.string.cat_hard))) return R.drawable.ic_hard;
        return R.drawable.ic_easy;
    }

    @Override
    public int getItemCount() {
        return quizes.size();
    }

    public void clear() {
        quizes.clear();
        notifyDataSetChanged();
    }

    public static interface ItemListener {
        void onPlayClicked(Quiz quiz);
    }
}
