package com.boldijarpaul.itfest.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Movie;
import com.boldijarpaul.itfest.data.models.Person;
import com.boldijarpaul.itfest.data.models.PersonResponse;
import com.boldijarpaul.itfest.presenter.presenters.PeoplePresenter;
import com.boldijarpaul.itfest.presenter.views.PeopleView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.ic_heart_big);
    }


}
