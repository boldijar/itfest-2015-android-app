package com.boldijarpaul.itfest.presenter.base;

/**
 * Created by razvan on 8/5/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Represents the Presenter of the popular Model-View-Presenter design pattern.
 * <p/>
 * The presenter connects the View V to a model which don't know each other. The View is passive and
 * provides this Presenter with events from the UI. It's an RxPresenter because it works with {@link
 * Observable} from RxJava to communicate with the View.
 * <p/>
 * Created by pascalwelsch on 4/17/15.
 */
public abstract class RxPresenter<V> {

    private final V mView;

    private CompositeSubscription mUiSubscriptions = new CompositeSubscription();

    private BehaviorSubject<Boolean> mViewReady = BehaviorSubject.create(false);

    public RxPresenter(final V view) {
        mView = view;
    }

    /**
     * @return the view of this presenter
     */
    protected V getView() {
        return mView;
    }

    /**
     * add your subscriptions for View events to this method to get them automatically cleaned up in
     * {@link #sleep()}. typically call this in {@link #wakeUp()} where you subscribe to the UI
     * events
     */
    protected void manageViewSubscription(final Subscription subscription) {
        mUiSubscriptions.add(subscription);
    }

    /**
     * Returns a transformer that will delay onNext, onError and onComplete emissions unless a view
     * become available. getView() is guaranteed to be != null during all emissions. This
     * transformer can only be used on application's main thread.
     * <p/>
     * If the transformer receives a next value while the previous value has not been delivered, the
     * previous value will be dropped.
     * <p/>
     * The transformer will duplicate the latest onNext emission in case if a view has been
     * reattached.
     * <p/>
     * This operator ignores onComplete emission and never sends one.
     * <p/>
     * Use this operator when you need to show updatable data that needs to be cached in memory.
     *
     * @param <T> a type of onNext value.
     * @return the delaying operator.
     */
    public <T> Observable.Transformer<T, T> deliverLatestCacheToView() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.lift(OperatorSemaphore.<T>semaphoreLatestCache(isViewReady()));
            }
        };
    }

    /**
     * Returns a transformer that will delay onNext, onError and onComplete emissions unless a view
     * become available. getView() is guaranteed to be != null during all emissions. This
     * transformer can only be used on application's main thread.
     * <p/>
     * If this transformer receives a next value while the previous value has not been delivered,
     * the previous value will be dropped.
     * <p/>
     * Use this operator when you need to show updatable data.
     *
     * @param <T> a type of onNext value.
     * @return the delaying operator.
     */
    public <T> Observable.Transformer<T, T> deliverLatestToView() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.lift(OperatorSemaphore.<T>semaphoreLatest(isViewReady()));
            }
        };
    }

    /**
     * Returns a transformer that will delay onNext, onError and onComplete emissions unless a view
     * become available. getView() is guaranteed to be != null during all emissions. This
     * transformer can only be used on application's main thread.
     * <p/>
     * Use this operator if you need to deliver *all* emissions to a view, in example when you're
     * sending items into adapter one by one.
     *
     * @param <T> a type of onNext value.
     * @return the delaying operator.
     */
    public <T> Observable.Transformer<T, T> deliverToView() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.lift(OperatorSemaphore.<T>semaphore(isViewReady()));
            }
        };
    }

    /**
     * completes all observables of this presenter. Should be called when the view is about to die
     * and will never come back.
     * <p/>
     * call this in {@link Fragment#onDestroy()}
     * <p/>
     * complete all {@link Observer}, i.e. BehaviourSubjects with {@link Observer#onCompleted()}
     * to unsubscribe all observers
     */
    public void destroy() {
        mViewReady.onNext(false);
    }

    /**
     * call sleep as the opposite of {@link #wakeUp()} to unsubscribe all observers listening to the
     * UI observables of the view. Calling sleep in {@link Fragment#onDestroyView()} makes sense
     * because observing a discarded view does not.
     */
    public void sleep() {
        mViewReady.onNext(false);
        // unsubscribe all UI subscriptions created in wakeUp() and added
        // via manageViewSubscription(Subscription)
        mUiSubscriptions.unsubscribe();
        // there is no reuse possible. recreation works fine
        mUiSubscriptions = new CompositeSubscription();
    }

    /**
     * when calling wakeUp the presenter starts to observe the observables of the View.
     * <p/>
     * Call this in a Fragment after {@link Fragment#onCreateView(LayoutInflater, ViewGroup,
     * Bundle)} and after you created and published all observables the presenter will use. At the
     * end of {@link Fragment#onViewCreated(View, Bundle)} is an appropriate place.
     */
    public void wakeUp() {
        mViewReady.onNext(true);
    }

    /**
     * Observable of the view state. The View is ready to receive calls after calling {@link
     * #wakeUp()} and before calling {@link #sleep()}.
     */
    private Observable<Boolean> isViewReady() {
        return mViewReady.asObservable().distinctUntilChanged();
    }
}
