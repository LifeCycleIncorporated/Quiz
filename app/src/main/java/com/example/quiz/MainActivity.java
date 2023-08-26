package com.example.quiz;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionTextviewId;
    TextView questionTextViewId;
    Button ansA, ansB, ansC, ansD;
    Button submitButton;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextviewId = findViewById(R.id.totalQuestionId);
        questionTextViewId = findViewById(R.id.questionId);

        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitButton = findViewById(R.id.submitId);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionTextviewId.setText("Total question : "+totalQuestion);

        loadNewQuestion();
    }


    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if (clickedButton.getId()==R.id.submitId){

            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion() {

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        questionTextViewId.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choice[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choice[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choice[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choice[currentQuestionIndex][3]);
    }

    void finishQuiz() {
        String passStatus;
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }
        else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" total "+totalQuestion)
                .setPositiveButton("Restart",((dialog, which) -> restart()))
                .setCancelable(false)
                .show();
    }

    void restart() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}