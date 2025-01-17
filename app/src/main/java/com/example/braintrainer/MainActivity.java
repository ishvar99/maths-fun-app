package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Button button,timerButton,scoreButton,playAgain;
    Button[] option=new Button[4];
    int[] option_ids={R.id.option1,R.id.option2,R.id.option3,R.id.option4};
    CountDownTimer timer;
    TextView textView;
    int totalTime=10000;
    int timeInterval=1000;
    int counter=0;
    int score=0;
    int nOptions=4;
    String[] questions={"100-37+89","133+21/7","56+7*4","90-12/4","128-80/10"};
    int maxScore=questions.length;
    Integer[] answers={1,2,4,1,4};
    Integer[][] options={{152,157,145,160},{136,22,150,35},{125,80,90,84},{87,90,105,77},{110,50,78,120}};
    public void playAgain(View view){
        counter=0;
        score=0;
        changeButtonState(true);
        startGame(view);
    }
    public void changeButtonState(boolean state){
        for(int i=0;i<nOptions;i++){
            option[i].setClickable(state);
        }
    }
    public void setQuestion(int i)
    {
        textView.setText(questions[i]);
        for(int k=0;k<nOptions;k++){
            option[k]=findViewById(option_ids[k]);
            option[k].setText(String.valueOf(options[i][k]));
        }
        if(i<questions.length-1)
        counter++;
    }
    public void submitAnswer(View view){
        checkAnswer((Button) view);
    }
    @SuppressLint("SetTextI18n")
    public void updateScore(){
        if(score<maxScore)
        score++;
        scoreButton.setText(score+"/"+maxScore);
    }
    public void checkAnswer(Button btn){

        if(btn.getTag().toString().equals(answers[counter-1].toString()))
        {
            updateScore();
            setQuestion(counter);
        }
        else
            setQuestion(counter);
    }
    public void startTimer()
    {
        timer = new CountDownTimer(totalTime+100,timeInterval) {
            @Override
            public void onTick(long l) {
                timerButton.setText(String.valueOf(l/1000));
            }
            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                changeButtonState(false);
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    public void startGame(View view){
        playAgain=findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        button =findViewById(R.id.button);
        scoreButton=findViewById(R.id.scoreButton);
        textView=findViewById(R.id.questionText);
        linearLayout=findViewById(R.id.linearLayoutMain);
        linearLayout.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        timerButton=findViewById(R.id.timerButton);
        startTimer();
        scoreButton.setText(score+"/"+maxScore);
        setQuestion(counter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
