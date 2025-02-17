package com.tittech.quizmcqtest;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionModule {

    private String question;
    private ArrayList<String> answers = new ArrayList<>();
    private String rightAnswer;
    private String rightAnswerFull;

    public QuestionModule(String question, String rightAnswer, String rightAnswerFull , String ... answers ) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.rightAnswerFull = rightAnswerFull;
        this.answers.add(answers[0]);
        this.answers.add(answers[1]);
        this.answers.add(answers[2]);
        this.answers.add(answers[3]);
    }


    //===============================Some automation by Juba
    public static void createQuestionsForSubject(String subjectName, ArrayList<QuestionModule> arrQuestions){
        QuestionCollection.questionBank.add(arrQuestions);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("subjectName", subjectName);
     //   hashMap.put("icon", String.valueOf(drawable) );
        QuestionCollection.subjectList.add(hashMap);


    }





    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getRightAnswerFull() {
        return rightAnswerFull;
    }
}
