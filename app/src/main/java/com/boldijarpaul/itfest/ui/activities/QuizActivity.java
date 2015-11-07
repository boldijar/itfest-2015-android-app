package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity {

    @Bind(R.id.activity_quiz_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.activity_quiz_question)
    TextView mQuestion;
    @Bind(R.id.activity_quiz_viewpager)
    ViewPager mPager;

    private Quiz mQuiz;
    public static String KEY_QUIZ = "KEYZUIQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getSerializableExtra(KEY_QUIZ) == null) {
            finish();
        }
        mQuiz = (Quiz) getIntent().getSerializableExtra(KEY_QUIZ);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
