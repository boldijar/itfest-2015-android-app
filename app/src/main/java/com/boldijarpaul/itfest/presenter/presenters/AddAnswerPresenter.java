package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.AnswersService;
import com.boldijarpaul.itfest.api.services.QuizesService;
import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.data.models.QuizResponse;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.AddAnswerView;
import com.boldijarpaul.itfest.presenter.views.QuizesView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razvan on 10/27/15.
 */
public class AddAnswerPresenter extends RxPresenter<AddAnswerView> {

    @Inject
    AnswersService mAnswersService;

    public AddAnswerPresenter(AddAnswerView view, Context context) {
        super(view);
        DaggerApp.get(context).graph().inject(this);
    }

    public void addAnswer(Answer answer) {
        mAnswersService.addAnswer(answer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getView().onError();
                    }

                    @Override
                    public void onNext(String response) {
                        getView().onSuccess();
                    }
                });
    }


}
