package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.QuizesService;
import com.boldijarpaul.itfest.data.models.MovieResponse;
import com.boldijarpaul.itfest.data.models.PersonResponse;
import com.boldijarpaul.itfest.data.models.QuizResponse;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.PeopleView;
import com.boldijarpaul.itfest.presenter.views.QuizesView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by razvan on 10/27/15.
 */
public class QuizesPresenter extends RxPresenter<QuizesView> {

    @Inject
    QuizesService mQuizesService;

    public QuizesPresenter(QuizesView view, Context context) {
        super(view);
        DaggerApp.get(context).graph().inject(this);
    }

    public void getQuizes() {
        mQuizesService.getQuizes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuizResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(QuizResponse quizResponse) {
                        getView().onGetQuizes(quizResponse.quizes);
                    }
                });
    }


}
