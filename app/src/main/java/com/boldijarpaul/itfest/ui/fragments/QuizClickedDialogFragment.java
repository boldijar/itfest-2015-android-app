package com.boldijarpaul.itfest.ui.fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.ui.activities.QuizActivity;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizClickedDialogFragment extends DialogFragment implements TextToSpeech.OnInitListener {


    public static final String KEY_QUIZ = "KEYQUIZ";
    private TextToSpeech mTextToSpeech;
    @Bind(R.id.fragment_quiz_clicked_dialog_yes)
    View mYesView;
    @Bind(R.id.fragment_quiz_clicked_dialog_no)
    View mNoView;
    @Bind(R.id.fragment_quiz_clicked_dialog_message)
    TextView mMessage;

    public static QuizClickedDialogFragment newInstance(Quiz quiz) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_QUIZ, quiz);
        QuizClickedDialogFragment fragment = new QuizClickedDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public QuizClickedDialogFragment() {
        // Required empty public constructor
    }


    @OnClick(R.id.fragment_quiz_clicked_dialog_yes)
    void yesClicked() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra(QuizActivity.KEY_QUIZ, getQuiz());
        startActivity(intent);
        dismiss();

    }

    @OnClick(R.id.fragment_quiz_clicked_dialog_no)
    void noClicked() {
        dismiss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mTextToSpeech.stop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_clicked_dialog, container, false);
        ButterKnife.bind(this, view);
        mTextToSpeech = new TextToSpeech(view.getContext(), this);
        mMessage.setText(createMessage());
        return view;
    }


    private Quiz getQuiz() {
        return (Quiz) getArguments().getSerializable(KEY_QUIZ);

    }

    private String createMessage() {
        Quiz quiz = getQuiz();
        String result = "";
        result += getActivity().getString(R.string.msg_quiz_about) + quiz.about + ".";
        result += "\n";
        result += getActivity().getString(R.string.msg_quz_difficulty) + quiz.difficulty.toLowerCase() + ".";
        result += "\n" + getActivity().getString(R.string.msg_please_click_left);
        return result;
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTextToSpeech.setLanguage(Locale.US);
            mTextToSpeech.setSpeechRate(0.6f);
            mTextToSpeech.speak(createMessage(), TextToSpeech.QUEUE_FLUSH, null);
        }

    }
}
