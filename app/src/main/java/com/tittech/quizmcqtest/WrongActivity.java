package com.tittech.quizmcqtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WrongActivity extends AppCompatActivity {
    Button next;
    TextView question;
    TextView answer;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        next = findViewById(R.id.next);
        question = findViewById(R.id.lblQuestion);
        answer = findViewById(R.id.answer);

        Bundle bundle = getIntent().getExtras();
        String q = bundle.getString("lblQuestion");
        String rightAnswer = bundle.getString("rightAnswer");
        String rightAnswerFull = bundle.getString("rightAnswerFull");
        Log.d("lblQuestion",q+" \nrightAnswer : "+rightAnswer+"."+rightAnswerFull);

        question.setText(q);
        answer.setText(rightAnswer+". "+rightAnswerFull);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        MediaPlayer audio = MediaPlayer.create(this, R.raw.wrong);
        audio.start();
        //thread.start();
    }
}
