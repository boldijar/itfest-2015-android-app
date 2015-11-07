package com.boldijarpaul.itfest.presenter.presenters;

import android.content.Context;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.api.services.EventService;
import com.boldijarpaul.itfest.api.services.MovieService;
import com.boldijarpaul.itfest.data.models.MovieResponse;
import com.boldijarpaul.itfest.data.models.PersonResponse;
import com.boldijarpaul.itfest.presenter.base.RxPresenter;
import com.boldijarpaul.itfest.presenter.views.PeopleView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by razvan on 10/27/15.
 */
public class PeoplePresenter extends RxPresenter<PeopleView> {

    @Inject
    EventService mEventService;
    @Inject
    MovieService movieService;

    public PeoplePresenter(PeopleView view, Context context) {
        super(view);

        DaggerApp.get(context).graph().inject(this);
    }

    public void getMovies() {
        movieService.getMovies("1,100")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        getView().showMovies(movieResponse.movie);
                    }
                });
    }

    public void getPeople() {
        mEventService.getPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PersonResponse>() {
                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Timber.e(e, "[getEvent]: onError");
                               }

                               @Override
                               public void onNext(PersonResponse personResponse) {
                                   getView().showPeople(personResponse);
                               }
                           }

                );

    }
}
