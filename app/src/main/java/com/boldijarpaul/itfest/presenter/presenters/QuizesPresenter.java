package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.QuizesService;
import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.data.models.QuizResponse;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.QuizesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razvan on 10/27/15.
 */
public class QuizesPresenter extends RxPresenter<QuizesView> {

    @Inject
    QuizesService mQuizesService;
    private static final int ITEMS_PER_PAGE = 3;

    private List<Quiz> mQuizes = new ArrayList<Quiz>();

    private int mPage = 1;
    private boolean reachedEnd = false;
    private boolean loading = false;

    public QuizesPresenter(QuizesView view, Context context) {
        super(view);
        DaggerApp.get(context).graph().inject(this);
    }

    public void resetQuizes() {
        mQuizes.clear();
        loading = false;
        mPage = 1;
        reachedEnd = false;
        getQuizes();
    }

    public void getQuizes() {
        if (loading || reachedEnd) return;
        loading = true;
        String pageString = mPage + "," + ITEMS_PER_PAGE;
        mQuizesService.getQuizes(pageString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuizResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loading = false;
                    }

                    @Override
                    public void onNext(QuizResponse quizResponse) {
                        mQuizes.addAll(quizResponse.quizes);
                        getView().onGetQuizes(quizResponse.quizes);
                        mPage++;
                        loading = false;
                        if (quizResponse.quizes.size() == 0) {
                            reachedEnd = true;
                        }
                    }
                });
    }

    public void getAllQuizes() {
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
                        mQuizes.addAll(quizResponse.quizes);
                        getView().onGetQuizes(quizResponse.quizes);
                    }
                });
    }


}
