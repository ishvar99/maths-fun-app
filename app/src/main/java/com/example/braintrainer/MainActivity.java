package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

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
    int totalTime=25000;
    int timeInterval=1000;
    int counter=0;
    int score=0;
    String[] questions={"100-37+89","133+21/7"};
    Integer[] answers={152,22};
    Integer[][] options={{152,157,145,160},{136,22,150,35}};
    public void playAgain(View view){
        counter=0;
        score=0;
        startGame(view);
    }
    public void setQuestion(int i){
        Log.i("counter",String.valueOf(counter));
        textView.setText(questions[i]);
        for(int k=0;k<4;k++){
            option[k]=findViewById(option_ids[k]);
            option[k].setText(String.valueOf(options[i][k]));
        }
        counter++;
    }
    public void submitAnswer(View view){
        checkAnswer((Button) view);
    }
    public void updateScore(){
        score++;
        scoreButton.setText(score+"/5");
    }
    public void checkAnswer(Button btn){
        if(btn.getText().toString().equals("152")){
            updateScore();
            setQuestion(counter);
        }
        else{
            setQuestion(counter);
        }
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
            }
        }.start();
    }

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
        scoreButton.setText(score+"/5");
        setQuestion(counter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
