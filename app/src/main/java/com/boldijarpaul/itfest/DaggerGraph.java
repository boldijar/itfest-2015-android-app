package com.boldijarpaul.itfest;

import com.boldijarpaul.itfest.presenter.presenters.AddAnswerPresenter;
import com.boldijarpaul.itfest.presenter.presenters.AnswersPresenter;
import com.boldijarpaul.itfest.presenter.presenters.QuizesPresenter;
import com.boldijarpaul.itfest.presenter.presenters.RegisterPresenter;
import com.boldijarpaul.itfest.presenter.presenters.UserSearchPresenter;
import com.boldijarpaul.itfest.ui.activities.QuizActivity;

/**
 */
public interface DaggerGraph {


    void inject(QuizesPresenter quizesPresenter);

    void inject(DaggerApp daggerApp);

    void inject(QuizActivity quizActivity);

    void inject(AddAnswerPresenter addAnswerPresenter);

    void inject(AnswersPresenter answersPresenter);

    void inject(RegisterPresenter registerPresenter);

    void inject(UserSearchPresenter userSearchPresenter);
}
