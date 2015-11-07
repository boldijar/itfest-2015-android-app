package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;
import android.provider.Settings;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.UserService;
import com.boldijarpaul.itfest.data.models.User;
import com.boldijarpaul.itfest.data.models.UserResponse;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.RegisterView;
import com.boldijarpaul.itfest.presenter.views.SearchUserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razvan on 10/27/15.
 */
public class UserSearchPresenter extends RxPresenter<SearchUserView> {

    @Inject
    UserService mUserService;

    private Context mContext;

    public UserSearchPresenter(SearchUserView view, Context context) {
        super(view);
        mContext = context;
        DaggerApp.get(context).graph().inject(this);
    }


    public void getUsers(String userName, int age) {


        String userQuery = null;
        if (userName.trim().length() > 0) {
            userQuery = "fullname,cs," + userName;
        }
        String ageQuery = null;
        if (age > 0) {
            ageQuery = "age,eq," + age;
        }

        List<String> queries = new ArrayList<>();
        if (ageQuery != null) queries.add(ageQuery);
        if (userQuery != null) queries.add(userQuery);
        String[] queryArray = new String[queries.size()];
        for (int i = 0; i < queryArray.length; i++) {
            queryArray[0] = queries.get(i);
        }
        mUserService.getUsers(queryArray)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        getView().onGetUsers(response.users);
                    }
                });
    }


}
