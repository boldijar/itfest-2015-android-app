package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.api.ApiModule;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FinishQuizActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {


    public static final String KEY_CHOOSEN_URL = "CHOSEURL";
    public static final String KEY_QUIZ = "QUIZKEY";
    @Bind(R.id.activity_finish_quiz_message)
    TextView mMessage;
    @Bind(R.id.activity_finish_quiz_image)
    ImageView mImage;


    private String mChoosenUrl;
    private Quiz mQuiz;
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        mTextToSpeech = new TextToSpeech(this, this);

        mChoosenUrl = getIntent().getStringExtra(KEY_CHOOSEN_URL);
        mQuiz = (Quiz) getIntent().getSerializableExtra(KEY_QUIZ);

        setUpViews();
    }


    private void setUpViews() {
        if (goodAnswer()) setUpGoodAnswerViews();
        else setUpWrongAnswerViews();
    }

    private void setUpWrongAnswerViews() {
        mMessage.setText(getWrongAnswerMessage());
        Picasso.with(this)
                .load(mQuiz.answer1.replace("localhost", ApiModule.IP))
                .into(mImage);
        mTextToSpeech.speak(getString(R.string.msg_wrong_play), TextToSpeech.QUEUE_FLUSH, null);

    }

    private void setUpGoodAnswerViews() {
        mMessage.setText(getString(R.string.msg_congratulation_right));
        mImage.setImageResource(R.drawable.gold_blue);
        mTextToSpeech.speak(getString(R.string.msg_congratulation_right), TextToSpeech.QUEUE_FLUSH, null);

    }

    private String getWrongAnswerMessage() {
        String result = "";
        result += getString(R.string.msg_wrong);
        return result;
    }


    private boolean goodAnswer() {
        return mQuiz.answer1.equals(mChoosenUrl);
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
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
