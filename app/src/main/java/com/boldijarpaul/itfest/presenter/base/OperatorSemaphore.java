package com.boldijarpaul.itfest.presenter.base;

/**
 * Created by razvan on 8/5/15.
 */
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Konstantin Mikheev sirstripy-at-gmail-com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * This operator delays onNext, onComplete and onError emissions until a True value received from a
 * given observable. When the given observable emits False, the operator starts delaying emissions
 * again.
 * <p/>
 * semaphoreLatest variant drops older not emitted onNext value if a new value has been received.
 * <p/>
 * semaphoreLatestCache keeps the latest value after emission and sends it on each True value from a
 * given observable received. This variant never emits onCompleted.
 *
 * @param <T> a type of onNext value
 * @author konmik https://github.com/konmik/nucleus/blob/53ecd398cec6e85b58545b4d30cfa961470d9f68/nucleus-example-with-tests/src/main/java/nucleus/example/main/MainPresenter.java
 */
public class OperatorSemaphore<T> implements Observable.Operator<T, T> {

    private boolean cache;

    private Observable<Boolean> go;

    private boolean latest;

    private OperatorSemaphore(Observable<Boolean> go) {
        this.go = go;
    }

    private OperatorSemaphore(Observable<Boolean> go, boolean latest) {
        this.go = go;
        this.latest = latest;
    }

    private OperatorSemaphore(Observable<Boolean> go, boolean latest, boolean cache) {
        this.go = go;
        this.latest = latest;
        this.cache = cache;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        return new Subscriber<T>() {

            boolean deliverCompleted;

            boolean deliverError;

            Throwable error;

            boolean hasCache;

            boolean isOpen;

            ArrayList<T> next = new ArrayList<>();

            T nextCache;

            @Override
            public void onCompleted() {
                if (!cache) {
                    deliverCompleted = true;
                    tick(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                error = e;
                deliverError = true;
                tick(false);
            }

            @Override
            public void onNext(T o) {
                if (latest) {
                    next.clear();
                }
                next.add(o);
                tick(false);
            }

            @Override
            public void onStart() {
                super.onStart();
                add(go.subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        isOpen = aBoolean;
                        tick(cache);
                    }
                }));
                child.add(this);
            }

            void tick(boolean deliverCache) {
                if (!isUnsubscribed() && isOpen) {

                    while (next.size() > 0) {
                        T value = next.remove(0);
                        child.onNext(value);
                        deliverCache = false;
                        if (cache) {
                            nextCache = value;
                            hasCache = true;
                        }
                    }

                    if (deliverCache && hasCache) {
                        child.onNext(nextCache);
                    }

                    if (deliverCompleted) {
                        child.onCompleted();
                        unsubscribe();
                    }

                    if (deliverError) {
                        child.onError(error);
                        unsubscribe();
                    }
                }
            }
        };
    }

    /**
     * Returns an operator that delays onNext, onComplete and onError emissions until a True value
     * received from a given observable. When the given observable emits False, the operator starts
     * delaying emissions again.
     *
     * @param go  an operator that controls emission.
     * @param <T> a type of onNext value.
     * @return an operator that delays onNext, onComplete and onError emissions until a True value
     * received from a given observable. When the given observable emits False, the operator starts
     * delaying emissions again.
     */
    public static <T> OperatorSemaphore<T> semaphore(Observable<Boolean> go) {
        return new OperatorSemaphore<>(go);
    }

    /**
     * Returns an operator that delays onNext, onComplete and onError emissions until a True value
     * received from a given observable. When the given observable emits False, the operator starts
     * delaying emissions again.
     * <p/>
     * This variant drops older not emitted value if a new value has been received.
     *
     * @param go  an operator that controls emission.
     * @param <T> a type of onNext value.
     * @return an operator that delays onNext, onComplete and onError emissions until a True value
     * received from a given observable. When the given observable emits False, the operator starts
     * delaying emissions again.
     * <p/>
     * This variant drops older not emitted value if a new value has been received.
     */
    public static <T> OperatorSemaphore<T> semaphoreLatest(Observable<Boolean> go) {
        return new OperatorSemaphore<>(go, true);
    }

    /**
     * Returns an operator that delays onNext, onComplete and onError emissions until a True value
     * received from a given observable. When the given observable emits False, the operator starts
     * delaying emissions again.
     * <p/>
     * This variant drops older not emitted value if a new value has been received.
     * <p/>
     * It also keeps the latest value after emission and sends it on each True value from a given
     * observable received. This variant never emits onCompleted.
     *
     * @param go  an operator that controls emission.
     * @param <T> a type of onNext value.
     * @return an operator that delays onNext, onComplete and onError emissions until a True value
     * received from a given observable. When the given observable emits False, the operator starts
     * delaying emissions again.
     * <p/>
     * This variant drops older not emitted value if a new value has been received.
     * <p/>
     * It also keeps the latest value after emission and sends it on each True value from a given
     * observable received. This variant never emits onCompleted.
     */
    public static <T> OperatorSemaphore<T> semaphoreLatestCache(Observable<Boolean> go) {
        return new OperatorSemaphore<>(go, true, true);
    }
}
