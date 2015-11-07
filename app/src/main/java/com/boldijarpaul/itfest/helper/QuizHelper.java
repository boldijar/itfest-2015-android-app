package com.boldijarpaul.itfest.helper;

import com.boldijarpaul.itfest.api.ApiModule;
import com.boldijarpaul.itfest.data.models.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class QuizHelper {

    public List<String> quizToImagesArray(Quiz quiz) {
        List<String> quizes = new ArrayList<>();
        if (quiz.answer1 != null) quizes.add(quiz.answer1.replace("localhost", ApiModule.IP));
        if (quiz.answer2 != null) quizes.add(quiz.answer2.replace("localhost", ApiModule.IP));
        if (quiz.answer3 != null) quizes.add(quiz.answer3.replace("localhost", ApiModule.IP));
        if (quiz.answer4 != null) quizes.add(quiz.answer4.replace("localhost", ApiModule.IP));
        if (quiz.answer5 != null) quizes.add(quiz.answer5.replace("localhost", ApiModule.IP));
        if (quiz.answer6 != null) quizes.add(quiz.answer6.replace("localhost", ApiModule.IP));
        return quizes;
    }
}
