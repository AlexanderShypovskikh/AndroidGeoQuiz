package com.project.shypovskikh.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestion;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
            new TrueFalse(R.string.question_mideat, false)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestion = (TextView)findViewById(R.id.textView);
        mQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(1);
            }
        });
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestion.setText(question);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(false);
            }
        });

        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             nextQuestion(-1);
            }
        });
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              nextQuestion(1);
            }
        });


    }

    private void checkAnswer(boolean userPresseTrue){
       boolean answer = mQuestionBank[mCurrentIndex].ismTrueQuestion();
        int msgId = 0;
        if(userPresseTrue == answer)
             msgId = R.string.correct_toast;
            else
                msgId = R.string.incorrect_toast;

                Toast.makeText(QuizActivity.this, msgId, Toast.LENGTH_SHORT).show();

    }

    private void nextQuestion(int offset){
        mCurrentIndex = (mCurrentIndex + offset) % mQuestionBank.length;
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestion.setText(question);
    }



}
