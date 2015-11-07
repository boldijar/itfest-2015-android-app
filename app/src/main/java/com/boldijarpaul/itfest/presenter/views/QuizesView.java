package com.boldijarpaul.itfest.presenter.views;

import com.boldijarpaul.itfest.data.models.Quiz;

import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public interface QuizesView {
    void onGetQuizes(List<Quiz> quizes);
}
