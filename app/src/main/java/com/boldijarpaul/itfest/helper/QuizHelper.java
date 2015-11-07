package com.boldijarpaul.itfest.helper;

import com.boldijarpaul.itfest.data.models.Quiz;
import com.boldijarpaul.itfest.data.models.QuizInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class QuizHelper {

    public List<QuizInfo> quizToQuizInfoArray(Quiz quiz) {
        List<QuizInfo> quizes = new ArrayList<>();
        if (quiz.answer1 != null) {
            quizes.add(new QuizInfo(quiz.answer1, quiz.details1));
        }
        if (quiz.answer2 != null) {
            quizes.add(new QuizInfo(quiz.answer2, quiz.details2));
        }
        if (quiz.answer3 != null) {
            quizes.add(new QuizInfo(quiz.answer3, quiz.details3));
        }
        if (quiz.answer4 != null) {
            quizes.add(new QuizInfo(quiz.answer4, quiz.details4));
        }
        if (quiz.answer5 != null) {
            quizes.add(new QuizInfo(quiz.answer5, quiz.details5));
        }
        if (quiz.answer6 != null) {
            quizes.add(new QuizInfo(quiz.answer6, quiz.details6));
        }
        Collections.shuffle(quizes);
        return quizes;

    }


}
