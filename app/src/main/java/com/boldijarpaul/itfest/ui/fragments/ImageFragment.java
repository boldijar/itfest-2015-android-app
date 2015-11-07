package com.boldijarpaul.itfest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.api.ApiModule;
import com.boldijarpaul.itfest.data.models.QuizInfo;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Browsing on 11/7/2015.
 */
public class ImageFragment extends Fragment {


    @Bind(R.id.item_image_imageview)
    ImageView mImage;
    @Bind(R.id.item_image_play)
    View mPlay;

    private PlayListener mListener;


    public void setListener(PlayListener mListener) {
        this.mListener = mListener;
    }

    public static String KEY_URL = "KEYURL";

    public static ImageFragment newInstance(QuizInfo quizInfo) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_URL, quizInfo);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private QuizInfo mQuizInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_image, container, false);

        ButterKnife.bind(this, view);

        mQuizInfo = (QuizInfo) getArguments().getSerializable(KEY_URL);
        String url = mQuizInfo.url.replace("localhost", ApiModule.IP);
        Picasso.with(view.getContext())
                .load(url)
                .into(mImage);

        if (mQuizInfo.details == null) {
            mPlay.setVisibility(View.INVISIBLE);
        }
        return view;
    }


    @OnClick(R.id.item_image_play)
    void clickPlay() {
        if (mQuizInfo.details != null) {
            if (mListener != null) {
                mListener.onPlayText(mQuizInfo.details);
            }
        }
    }

    public interface PlayListener {
        void onPlayText(String text);
    }
}
