package com.boldijarpaul.itfest;

import com.boldijarpaul.itfest.presenter.presenters.AddAnswerPresenter;
import com.boldijarpaul.itfest.presenter.presenters.QuizesPresenter;
import com.boldijarpaul.itfest.ui.activities.QuizActivity;

/**
 * Created by razvan on 10/27/15.
 */
public interface DaggerGraph {


    void inject(QuizesPresenter quizesPresenter);

    void inject(DaggerApp daggerApp);

    void inject(QuizActivity quizActivity);

    void inject(AddAnswerPresenter addAnswerPresenter);
}
