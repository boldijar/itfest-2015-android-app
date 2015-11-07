package com.boldijarpaul.itfest;

import com.boldijarpaul.itfest.data.models.QuizResponse;
import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ModelTests {

    private String quizMock ="\n" +
            "{\"quiz\":[{\"id\":\"1\",\"name\":\"Sport quiz\",\"difficulty\":\"Medium\",\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"2\",\"name\":\"Sport quiz\",\"difficulty\":\"Easy\",\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"3\",\"name\":\"Sport quiz\",\"difficulty\":\"Hard\",\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"4\",\"name\":\"Sport quiz\",\"difficulty\":\"Medium\",\"question\":\"Please select the cool image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"5\",\"name\":\"Sport quiz\",\"difficulty\":null,\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"6\",\"name\":\"Sport quiz\",\"difficulty\":null,\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"7\",\"name\":\"Sport quiz\",\"difficulty\":null,\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"8\",\"name\":\"Sport quiz\",\"difficulty\":null,\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null},{\"id\":\"9\",\"name\":\"Sport quiz\",\"difficulty\":null,\"question\":\"Please select the football image\",\"answer1\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer2\":\"http:\\/\\/localhost\\/api\\/image2.jpg\",\"answer3\":\"http:\\/\\/localhost\\/api\\/image1.jpg\",\"answer4\":null,\"answer5\":null,\"answer6\":null}]}";
    @Test
    public void QuizResponseTest() throws Exception {
        QuizResponse eventResponse = new Gson().fromJson(quizMock, QuizResponse.class);


        assertEquals(eventResponse.quizes.size(), 9);
        assertEquals(eventResponse.quizes.get(0).name, "Sport quiz");
        assertEquals(eventResponse.quizes.get(0).answer1, "http://localhost/api/image1.jpg");
        assertEquals(eventResponse.quizes.get(0).answer6, null);
        assertEquals(eventResponse.quizes.get(0).difficulty, "Medium");
        assertEquals(eventResponse.quizes.get(3).question, "Please select the cool image");


    }


}