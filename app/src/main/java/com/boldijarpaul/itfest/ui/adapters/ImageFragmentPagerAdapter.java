package com.boldijarpaul.itfest.ui.adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.data.models.QuizInfo;
import com.boldijarpaul.itfest.helper.QuizHelper;
import com.boldijarpaul.itfest.ui.fragments.ImageFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Browsing on 11/7/2015.
 */
public class ImageFragmentPagerAdapter extends FragmentPagerAdapter implements TextToSpeech.OnInitListener, ImageFragment.PlayListener {

    private TextToSpeech mTextToSpeech;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<QuizInfo> mQuizInfos;

    public ImageFragmentPagerAdapter(FragmentManager fm, List<QuizInfo> mQuizInfos, Context context) {
        super(fm);
        this.mQuizInfos = mQuizInfos;
        for (QuizInfo quizInfo : mQuizInfos) {
            ImageFragment imageFragment = ImageFragment.newInstance(quizInfo);
            imageFragment.setListener(this);
            mFragments.add(imageFragment);
        }
        mTextToSpeech = new TextToSpeech(context, this);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mQuizInfos.size();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTextToSpeech.setLanguage(Locale.US);
            mTextToSpeech.setSpeechRate(0.6f);
        }
    }

    @Override
    public void onPlayText(String text) {
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
}
