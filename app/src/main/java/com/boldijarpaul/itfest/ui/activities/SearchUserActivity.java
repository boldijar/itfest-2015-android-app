package com.boldijarpaul.itfest.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.User;
import com.boldijarpaul.itfest.helper.EventsFeedRecyclerScrollListener;
import com.boldijarpaul.itfest.presenter.presenters.AnswersPresenter;
import com.boldijarpaul.itfest.presenter.presenters.UserSearchPresenter;
import com.boldijarpaul.itfest.presenter.views.SearchUserView;
import com.boldijarpaul.itfest.ui.adapters.AnswerAdapter;
import com.boldijarpaul.itfest.ui.adapters.UserAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchUserActivity extends AppCompatActivity implements SearchUserView {

    @Bind(R.id.activity_search_user_age)
    EditText mAge;
    @Bind(R.id.activity_search_user_name)
    EditText mName;
    @Bind(R.id.activity_search_users)
    RecyclerView mRecyclerView;


    private LinearLayoutManager mLinearLayoutManager;
    private UserAdapter mUserAdapter;

    private UserSearchPresenter mUsersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setUpRecycler();

        if (mUsersPresenter == null) {
            mUsersPresenter = new UserSearchPresenter(this, this);
        }

        mAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                requestToServer();
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                requestToServer();
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void requestToServer() {
        String name = "";
        int age = 0;
        if (mName.getText().toString().trim().length() >= 0)
            name = mName.getText().toString();
        try {
            age = Integer.parseInt(mAge.getText().toString());
        } catch (Exception ex) {
            age = 0;
        }
        mUsersPresenter.getUsers(name, age);
    }

    private void setUpRecycler() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mUserAdapter = new UserAdapter(this);
        mRecyclerView.setAdapter(mUserAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetUsers(List<User> users) {
        mUserAdapter.clear();
        mUserAdapter.addUsers(users);
    }
}
