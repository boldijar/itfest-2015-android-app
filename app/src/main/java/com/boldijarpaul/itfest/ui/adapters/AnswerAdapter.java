package com.boldijarpaul.itfest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.ui.viewholders.AnswerViewHolder;
import com.boldijarpaul.itfest.ui.viewholders.QuizViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

    private Context mContext;
    private List<Answer> answers = new ArrayList<>();

    public AnswerAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void addAnswers(List<Answer> answers) {
        int newEventIndex = this.answers.size();
        this.answers.addAll(answers);
        notifyItemRangeChanged(newEventIndex, answers.size());
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_answer, parent, false);
        AnswerViewHolder viewHolder = new AnswerViewHolder(itemView);
        return viewHolder;
    }

    private String getStringFromLong(long date) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int millis = now.get(Calendar.MILLISECOND);

        String result = String.format("%d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
        return result;

    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        final Answer answer = answers.get(position);
        holder.difficulty.setText(mContext.getString(R.string.msg_difficulty) + answer.quiz.get(0).difficulty.toLowerCase());
        holder.name.setText(answer.quiz.get(0).name);
        holder.difficultyImage.setImageResource(getImageResourceFromAnswer(answer));
        holder.date.setText(getStringFromLong(answer.date));

    }

    private int getImageResourceFromAnswer(Answer answer) {
        if (answer.success == 1) return R
                .drawable.ic_check;
        return R.drawable.ic_x;
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
        return answers.size();
    }

    public void clear() {
        answers.clear();
        notifyDataSetChanged();
    }


}
