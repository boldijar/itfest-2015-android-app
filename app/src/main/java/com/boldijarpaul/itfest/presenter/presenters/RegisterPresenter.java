package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;
import android.provider.Settings;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.AnswersService;
import com.boldijarpaul.itfest.api.services.UserService;
import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.data.models.User;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.AddAnswerView;
import com.boldijarpaul.itfest.presenter.views.RegisterView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razvan on 10/27/15.
 */
public class RegisterPresenter extends RxPresenter<RegisterView> {

    @Inject
    UserService mUserService;

    private Context mContext;

    public RegisterPresenter(RegisterView view, Context context) {
        super(view);
        mContext = context;
        DaggerApp.get(context).graph().inject(this);
    }

    public String getUniquePsuedoID() {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public void addUser(String fullName, int age) {

        User user = new User();
        user.fullName = fullName;
        user.age = age;
        user.deviceId = getUniquePsuedoID();


        mUserService.addUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String response) {
                        getView().onSuccess();
                    }
                });
    }


}
