package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.helper.EventsFeedRecyclerScrollListener;
import com.boldijarpaul.itfest.presenter.presenters.QuizesPresenter;
import com.boldijarpaul.itfest.presenter.views.QuizesView;
import com.boldijarpaul.itfest.ui.adapters.QuizAdapter;
import com.boldijarpaul.itfest.ui.fragments.QuizClickedDialogFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements QuizesView, QuizAdapter.ItemListener, EventsFeedRecyclerScrollListener.ScrollListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.activity_main_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.activity_main_stats)
    View mStats;
    @Bind(R.id.activity_main_register)
    View mRegister;
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
        mRecyclerView.addOnScrollListener(new EventsFeedRecyclerScrollListener(mLinearLayoutManager, this));
        mQuizesPresenter.getQuizes();
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    private void setUpRecycler() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mQuizAdapter = new QuizAdapter(this, this);
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
        QuizClickedDialogFragment.newInstance(quiz).show(getSupportFragmentManager(), "tag");
    }

    @Override
    public void onReachedEndOfScroll() {
        mQuizesPresenter.getQuizes();

    }

    @Override
    public void onRefresh() {
        mQuizAdapter.clear();
        mQuizesPresenter.resetQuizes();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.activity_main_register)
    void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.activity_main_stats)
    void statsClick() {
        startActivity(new Intent(this, AnswersActivity.class));
    }
}
