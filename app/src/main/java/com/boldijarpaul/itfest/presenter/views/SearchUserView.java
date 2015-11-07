package com.boldijarpaul.itfest.presenter.views;

import com.boldijarpaul.itfest.data.models.User;

import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public interface SearchUserView {
    void onGetUsers(List<User> users);
}
