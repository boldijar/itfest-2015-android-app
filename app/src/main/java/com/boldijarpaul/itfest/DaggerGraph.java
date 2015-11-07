package com.boldijarpaul.itfest;

import com.boldijarpaul.itfest.presenter.presenters.PeoplePresenter;

/**
 * Created by razvan on 10/27/15.
 */
public interface DaggerGraph {

    void inject(DaggerApp daggerApp);

    void inject(PeoplePresenter peoplePresenter);
}
