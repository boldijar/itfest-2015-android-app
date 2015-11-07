package com.boldijarpaul.itfest.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;

public class QuizActivity extends AppCompatActivity {

    private Quiz mQuiz;
    public static String KEY_QUIZ = "KEYZUIQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (getIntent().getSerializableExtra(KEY_QUIZ) == null) {
            finish();
        }
        mQuiz = (Quiz) getIntent().getSerializableExtra(KEY_QUIZ);
    }
}
