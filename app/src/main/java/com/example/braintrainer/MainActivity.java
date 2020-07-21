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
    Button button,timerButton;
    CountDownTimer timer;
    TextView textView;
    int totalTime=25000;
    int timeInterval=1000;
    int counter=0;
    String[] questions={"(100-37)+89","(133+21)/7)"};
    Integer[] answers={152,22};
    public String fetchQuestion(int i){
        return questions[i];
    }
    public void submitAnswer(View view){
        Log.i("info",view.toString());
        button=(Button)view;
        Log.i("tag",button.getTag().toString());
    }

    public void startTimer(){
        timer = new CountDownTimer(totalTime+100,timeInterval) {
            @Override
            public void onTick(long l) {
                timerButton.setText(String.valueOf(l/1000));
            }
            @Override
            public void onFinish() {
                Log.i("INFO","TIMES UP!");
            }
        }.start();
    }
    public void checkResult(){

    }

    public void startGame(View view){
        button =findViewById(R.id.button);
        textView=findViewById(R.id.questionText);
        linearLayout=findViewById(R.id.linearLayoutMain);
        linearLayout.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        timerButton=findViewById(R.id.timerButton);
        textView.setText(fetchQuestion(counter));
        startTimer();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
