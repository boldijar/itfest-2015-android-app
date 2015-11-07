package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.boldijarpaul.itfest.DaggerApp;
import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.data.models.QuizInfo;
import com.boldijarpaul.itfest.helper.QuizHelper;
import com.boldijarpaul.itfest.ui.adapters.ImageFragmentPagerAdapter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.activity_quiz_left)
    View mLeft;
    @Bind(R.id.activity_quiz_right)
    View mRight;
    @Bind(R.id.activity_quiz_question)
    TextView mQuestion;
    @Bind(R.id.activity_quiz_viewpager)
    ViewPager mPager;
    @Bind(R.id.activity_quiz_save)
    View mSave;


    private Quiz mQuiz;
    public static String KEY_QUIZ = "KEYZUIQ";
    private TextToSpeech mTextToSpeech;

    @Inject
    QuizHelper mQuizHelper;
    private FragmentPagerAdapter mAdapter;
    private List<QuizInfo> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DaggerApp.get(this).graph().inject(this);
        mTextToSpeech = new TextToSpeech(this, this);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getSerializableExtra(KEY_QUIZ) == null) {
            finish();
        }
        mQuiz = (Quiz) getIntent().getSerializableExtra(KEY_QUIZ);


        setUpViews();
    }

    private void setUpViews() {
        mImages = mQuizHelper.quizToQuizInfoArray(mQuiz);
        mAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager(), mImages, this);
        mQuestion.setText(mQuiz.question);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(7);
        mPager.setOnPageChangeListener(this);
        mPager.setCurrentItem(0, true);
    }

    @OnClick(R.id.activity_quiz_save)
    void onSave() {
        Intent intent = new Intent(this, FinishQuizActivity.class);
        String choosenUrl = mImages.get(mPager.getCurrentItem()).url;
        intent.putExtra(FinishQuizActivity.KEY_CHOOSEN_URL, choosenUrl);
        intent.putExtra(FinishQuizActivity.KEY_QUIZ, mQuiz);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.activity_quiz_left)
    void leftClicked() {
        if (mPager.getCurrentItem() > 0) {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
        }
    }

    @OnClick(R.id.activity_quiz_right)
    void rightClicked() {
        if (mPager.getCurrentItem() < mImages.size() - 1) {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTextToSpeech.stop();
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

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTextToSpeech.setLanguage(Locale.US);
            mTextToSpeech.setSpeechRate(0.6f);
            mTextToSpeech.speak(mQuiz.question, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mLeft.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE);
        mRight.setVisibility(position == mImages.size() - 1 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
