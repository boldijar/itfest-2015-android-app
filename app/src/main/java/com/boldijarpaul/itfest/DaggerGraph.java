package com.boldijarpaul.itfest;

import com.boldijarpaul.itfest.presenter.presenters.QuizesPresenter;

/**
 * Created by razvan on 10/27/15.
 */
public interface DaggerGraph {


    void inject(QuizesPresenter quizesPresenter);

    void inject(DaggerApp daggerApp);
}
