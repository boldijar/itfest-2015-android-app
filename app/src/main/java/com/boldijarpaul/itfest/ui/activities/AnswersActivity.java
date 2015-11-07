package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.helper.EventsFeedRecyclerScrollListener;
import com.boldijarpaul.itfest.presenter.presenters.AnswersPresenter;
import com.boldijarpaul.itfest.presenter.views.AnswersView;
import com.boldijarpaul.itfest.ui.adapters.AnswerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AnswersActivity extends AppCompatActivity implements AnswersView, SwipeRefreshLayout.OnRefreshListener, EventsFeedRecyclerScrollListener.ScrollListener {


    @Bind(R.id.activity_answers_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.activity_answers_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager mLinearLayoutManager;
    private AnswerAdapter mAnswerAdapter;

    private AnswersPresenter mAnswersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);


        ButterKnife.bind(this);

        setUpRecycler();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mAnswersPresenter == null) {
            mAnswersPresenter = new AnswersPresenter(this, this);
        }
        mRecyclerView.addOnScrollListener(new EventsFeedRecyclerScrollListener(mLinearLayoutManager, this));
        mAnswersPresenter.getAnswers();
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setUpRecycler() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAnswerAdapter = new AnswerAdapter(this);
        mRecyclerView.setAdapter(mAnswerAdapter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void getAnswers(List<Answer> answers) {
        mAnswerAdapter.addAnswers(answers);
    }

    @Override
    public void onRefresh() {
        mAnswerAdapter.clear();
        mAnswersPresenter.resetAnswers();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReachedEndOfScroll() {
        mAnswersPresenter.getAnswers();
    }
}
