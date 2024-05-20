package com.tittech.quizmcqtest;


import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView txtscore;
    TextView txtStatus, tvSubjectName;
    MediaPlayer audio;
    ImageView imgBack;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    TextView progressText;
    ProgressBar progressBarWrong;
    TextView progressTextWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        imgBack = findViewById(R.id.imgBack);
        tvSubjectName = findViewById(R.id.tvSubjectName);
        txtscore = findViewById(R.id.txtscore);
        txtStatus = findViewById(R.id.txtStatus);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        progressBar = findViewById(R.id.progress_bar_right);
        progressText = findViewById(R.id.progress_text_right);

        progressBarWrong = findViewById(R.id.progress_bar_wrong);
        progressTextWrong = findViewById(R.id.progress_text_wrong);

        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));
        String wrong = String.valueOf(intent.getIntExtra("wrong", 0));

        int iscore = Integer.parseInt(scores);
        int iworng = Integer.parseInt(wrong);




        txtStatus.setText(setStatus(scores));
        audio.start();


        tvSubjectName.setText(QuestionCollection.SUBJECT_NAME);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(ScoreActivity.this, MainActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(home);
                finish();
            }
        });

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedScore", scores+ "0% ("+ QuestionCollection.SUBJECT_NAME+")");
/*        editor.putInt("rightScore", iscore);
        editor.putInt("wrongScore", iworng);*/
        editor.apply();
/*
        String lastScore = sharedPreferences.getString("savedScore", "No Data");
        int rightScore = sharedPreferences.getInt("rightScore", 0);
        int wrongScore = sharedPreferences.getInt("wrongScore", 0);*/

        progressBar.setProgress(iscore*10);
        progressText.setText(iscore*10+"%");

        progressBarWrong.setProgress(iworng*10);
        progressTextWrong.setText(iworng*10+"%");

        txtscore.setText(scores);




/*
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent home = new Intent(ScoreActivity.this, MainActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(home);
                finish();
            }
        });
*/


    }

    private String setStatus(String scores){
        int score = Integer.parseInt(scores);

        if(score >= 8){
            audio = MediaPlayer.create(this, R.raw.high_score);
            return "Congratulations!! Very well done";
        }

        if (score >= 5){
            audio = MediaPlayer.create(this,  R.raw.medium_score);
            return "Well Done, Try again";
        }

        audio = MediaPlayer.create(this,  R.raw.low_score);
        return "Must do better";

    }




    //=======================================================

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent home = new Intent(ScoreActivity.this, MainActivity.class);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(home);
        finish();

    }

    //=======================================================

}
