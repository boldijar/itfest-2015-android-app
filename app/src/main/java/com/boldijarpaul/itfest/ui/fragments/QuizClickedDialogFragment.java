package com.boldijarpaul.itfest.ui.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Quiz;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizClickedDialogFragment extends DialogFragment {


    public static final String KEY_QUIZ = "KEYQUIZ";

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

//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            int width = ViewGroup.LayoutParams.MATCH_PARENT;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            dialog.getWindow().setLayout(width, height);
//        }
//    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_clicked_dialog, container, false);
        ButterKnife.bind(this, view);

        mMessage.setText(createMessage());
        return view;
    }

    private String createMessage() {
        Quiz quiz = (Quiz) getArguments().getSerializable(KEY_QUIZ);
        String result = "";
        result += "This quiz is about " + quiz.name;
        result += "\n";
        result += "The quiz difficulty is " + quiz.difficulty.toLowerCase();
        return result;
    }


}
