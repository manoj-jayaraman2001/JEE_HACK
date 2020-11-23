package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class mockTest extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";

    private TextView textViewQuestion,textViewScore,textViewQuestionCount,textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1,rb2,rb3;
    private Button ConfirmNext;

    private List<Question> questionlist;

    private ColorStateList textColorDefaultRb;
    private int questionCounter,totalQuestionCount;
    private int score;
    private Question currentquestion;
    private boolean answered;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test);

        textViewQuestion = findViewById(R.id.textView_question);
        textViewScore = findViewById(R.id.textView_score);
        textViewQuestionCount = findViewById(R.id.textView_questioncount);
        textViewCountDown = findViewById(R.id.textView_countdown);
        rbGroup = findViewById(R.id.rbgroup);
        rb1 = findViewById(R.id.radioButton_option1);
        rb2 = findViewById(R.id.radioButton_option2);
        rb3 = findViewById(R.id.radiobutton_option3);
        ConfirmNext = findViewById(R.id.button_confirmnext);

        textColorDefaultRb = rb1.getTextColors();

        QuizdbHelper quizdbHelper = new QuizdbHelper(this);
        questionlist = quizdbHelper.getallQuestions();
        totalQuestionCount = questionlist.size();
        Collections.shuffle(questionlist);

        showNextQuestion();

        ConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(mockTest.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });


    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter<totalQuestionCount){
            currentquestion = questionlist.get(questionCounter);
                textViewQuestion.setText(currentquestion.getQuestion());
                rb1.setText(currentquestion.getOption1());
                rb2.setText(currentquestion.getOption2());
                rb3.setText(currentquestion.getOption3());
                questionCounter++;
                textViewQuestionCount.setText("Question: " + questionCounter + "/" + totalQuestionCount);
                answered = false;
                ConfirmNext.setText("Confirm");

        }else{
            finishQuiz();
        }
    }


    private void checkAnswer(){
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected)+1;
        if(answerNr == currentquestion.getAnswernum()){
            score++;
            textViewScore.setText("Score: "+ score);
        }


        showSolution();
    }
    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);


        switch(currentquestion.getAnswernum()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if(questionCounter<totalQuestionCount){
            ConfirmNext.setText("Next");
        }else{
            ConfirmNext.setText("Finish");
        }
    }


    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}