package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.AnswersService;
import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.data.models.AnswerResponse;
import com.boldijarpaul.itfest.data.models.QuizResponse;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.AnswersView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razvan on 10/27/15.
 */
public class AnswersPresenter extends RxPresenter<AnswersView> {

    @Inject
    AnswersService mAnswerService;
    private static final int ITEMS_PER_PAGE = 5;

    private List<Answer> mAnswers = new ArrayList<>();

    private int mPage = 1;
    private boolean reachedEnd = false;
    private boolean loading = false;

    private Context mContext;

    public AnswersPresenter(AnswersView view, Context context) {
        super(view);
        mContext = context;
        DaggerApp.get(context).graph().inject(this);
    }

    public void resetAnswers() {
        mAnswers.clear();
        loading = false;
        mPage = 1;
        reachedEnd = false;
        getAnswers();
    }


    public String getUniquePsuedoID() {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
    public void getAnswers() {
        if (loading || reachedEnd) return;
        loading = true;
        String pageString = mPage + "," + ITEMS_PER_PAGE;
        String deviceId = getUniquePsuedoID();
        String filteredString = "deviceId,eq," + deviceId;
        mAnswerService.getFilteredAnswers(filteredString, pageString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnswerResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loading = false;
                    }

                    @Override
                    public void onNext(AnswerResponse answerResponse) {
                        mAnswers.addAll(answerResponse.answers);
                        getView().getAnswers(answerResponse.answers);
                        mPage++;
                        loading = false;
                        if (answerResponse.answers.size() == 0) {
                            reachedEnd = true;
                        }
                    }
                });
    }


}
