package com.boldijarpaul.itfest.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.ui.activities.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SplashActivity extends AppCompatActivity {

    private static final long MILLISECONDS_OF_WAITING = 2500;
    private Subscription mSplashScreenSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setStatusBarColor();
        startSplashTimer();

    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.splashBackgroundColor));
        }
    }

    private void startSplashTimer() {
        Observable<Long> observable = Observable.timer(MILLISECONDS_OF_WAITING, TimeUnit.MILLISECONDS);
        mSplashScreenSubscription = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        splashOver();
                    }
                });
    }

    private void splashOver() {
//        startActivity(new Intent(this, MainActivity.class));
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSplashScreenSubscription != null && !mSplashScreenSubscription.isUnsubscribed()) {
            mSplashScreenSubscription.unsubscribe();
        }
    }

}
