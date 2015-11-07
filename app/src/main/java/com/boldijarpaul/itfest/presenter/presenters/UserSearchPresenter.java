package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;
import android.provider.Settings;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.UserService;
import com.boldijarpaul.itfest.data.models.User;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.RegisterView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razvan on 10/27/15.
 */
public class UserSearchPresenter extends RxPresenter<UserService> {

    @Inject
    UserService mUserService;

    private Context mContext;

    public UserSearchPresenter(UserService view, Context context) {
        super(view);
        mContext = context;
        DaggerApp.get(context).graph().inject(this);
    }


    public void getUsers(String userName, int age) {

//
//        String userQuery = "";
//
//        mUserService.getUsers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(String response) {
//                        getView().onSuccess();
//                    }
//                });
    }


}
