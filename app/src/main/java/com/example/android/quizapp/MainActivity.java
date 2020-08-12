package com.example.android.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int totalPoints = 0;
    CheckBox ashBox;
    CheckBox buckBox;
    CheckBox nomadBox;
    CheckBox zofiaBox;
    EditText questionTwoAnswerField;
    EditText questionFourTextField;
    RadioGroup radioGroup;
    boolean questionThreeCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTextField();
        setupCheckBoxes();
        setupRadioButton();
    }

    /**
     * This method calculates the score for question one.
     *
     * @param ashBox
     * @param buckBox
     * @param nomadBox
     * @param zofiaBox
     */
    private int questionOneCheck(boolean ashBox, boolean buckBox, boolean nomadBox, boolean zofiaBox) {



        int questionOnePoints = 0;

        if ((ashBox && buckBox && nomadBox) && zofiaBox == false) {
            questionOnePoints = questionOnePoints + 1;
        }

        Log.v("MainActivity", "Question one check is operational");

        return questionOnePoints;

    }

    //Method
    private int writtenQuestionCheck(String userAnswer, String answer) {

        int writtenQuestionPoints = 0;

        if (answer.equals(userAnswer)) {
            writtenQuestionPoints = writtenQuestionPoints + 1;
        }

        return writtenQuestionPoints;
    }

    private int questionThreeCheck(boolean answer){

        int questionThreePoints = 0;

        if (answer == true){
            questionThreePoints = questionThreePoints + 1;
        }

        return questionThreePoints;
    }


    public void setupRadioButton(){
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.trueField:
                        Log.v("MainActivity", "Radio ID " + checkedId);
                        questionThreeCriteria = true;
                        break;
                    case R.id.falseField:
                        Log.v("MainActivity", "Radio ID " + checkedId);
                        questionThreeCriteria = false;
                        break;

                }
            }
        });
    }

    private void setupTextField(){
        questionTwoAnswerField = (EditText) findViewById(R.id.questionTwoAnswerField);
        questionFourTextField = (EditText) findViewById(R.id.questionFourAnswerField);
    }

    private void setupCheckBoxes() {
        ashBox = (CheckBox) findViewById(R.id.checkBox1);
        ashBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                  Log.v("MainActivity", "Ash was marked: " + isChecked);
                                              }
                                          }
        );
        buckBox = (CheckBox) findViewById(R.id.checkBox2);
        buckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                   Log.v("MainActivity", "Buck was marked: " + isChecked);
                                               }
                                           }
        );
        nomadBox = (CheckBox) findViewById(R.id.checkBox3);
        nomadBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    Log.v("MainActivity", "Nomad was marked: " + isChecked);
                                                }
                                            }
        );

        zofiaBox = (CheckBox) findViewById(R.id.checkBox4);
        zofiaBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    Log.v("MainActivity", "Zofia was marked: " + isChecked);
                                                }
                                            }
        );
    }

    public void checkTotalPoints (View view){
        totalPoints = questionOneCheck(ashBox.isChecked(), buckBox.isChecked(), nomadBox.isChecked(), zofiaBox.isChecked());
        totalPoints = totalPoints + writtenQuestionCheck(questionTwoAnswerField.getText().toString(), "Blitz");
        totalPoints = totalPoints + questionThreeCheck(questionThreeCriteria);
        totalPoints = totalPoints + writtenQuestionCheck(questionFourTextField.getText().toString(), "Mozzie");
        Log.v("MainActivity", "Question two answer " + questionTwoAnswerField.getText().toString());
        Log.v("MainActivity", "Total Points: " + totalPoints);


    int finalScore = totalPoints;
    String scoreMessage = finalScoreSummary();
    displaySummary(scoreMessage);
    }



    private String finalScoreSummary (){
        String scoreMessage;
        scoreMessage = "\nPoints Possible: 4 \nPoints Earned: " + totalPoints;
        return scoreMessage;
    }

    private void displaySummary(String message){
        TextView scoreSummaryTextView = (TextView) findViewById(R.id.summaryView);
        scoreSummaryTextView.setText(message);
    }
}