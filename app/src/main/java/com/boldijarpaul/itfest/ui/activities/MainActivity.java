package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.presenter.presenters.QuizesPresenter;
import com.boldijarpaul.itfest.presenter.views.QuizesView;
import com.boldijarpaul.itfest.ui.adapters.QuizAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements QuizesView, QuizAdapter.ItemListener {

    @Bind(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.activity_main_recycler)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private QuizAdapter mQuizAdapter;

    private QuizesPresenter mQuizesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpRecycler();
        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.ic_heart_big);

        if (mQuizesPresenter == null) {
            mQuizesPresenter = new QuizesPresenter(this, this);
        }
        mQuizesPresenter.getQuizes();

    }

    private void setUpRecycler() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mQuizAdapter = new QuizAdapter(this,this);
        mRecyclerView.setAdapter(mQuizAdapter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onGetQuizes(List<Quiz> quizes) {
        mQuizAdapter.addQuizes(quizes);
    }

    @Override
    public void onPlayClicked(Quiz quiz) {

    }
}
