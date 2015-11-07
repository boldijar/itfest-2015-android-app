package com.boldijarpaul.itfest.helper;

import com.boldijarpaul.itfest.data.models.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class QuizHelper {

    public List<String> quizToImagesArray(Quiz quiz) {
        List<String> quizes = new ArrayList<>();
        if (quiz.answer1 != null) quizes.add(quiz.answer1);
        if (quiz.answer2 != null) quizes.add(quiz.answer2);
        if (quiz.answer3 != null) quizes.add(quiz.answer3);
        if (quiz.answer4 != null) quizes.add(quiz.answer4);
        if (quiz.answer5 != null) quizes.add(quiz.answer5);
        if (quiz.answer6 != null) quizes.add(quiz.answer6);
        Collections.shuffle(quizes);
        return quizes;
    }
}
