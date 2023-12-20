package com.example.covidquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddQuestion extends AppCompatActivity {

    TextView questionview;
    TextView answerview;
    EditText questionEditView;
    EditText answerEditView;
    Button addquestionButton;
    Button saveandexitButton;
    Button gobackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        questionview = (TextView) findViewById(R.id.question_view);
        answerview = (TextView) findViewById(R.id.answer_view);
        questionEditView = (EditText) findViewById(R.id.question_editview);
        answerEditView = (EditText) findViewById(R.id.answer_editview);
        addquestionButton = (Button) findViewById(R.id.addquestion_button);
        saveandexitButton = (Button) findViewById(R.id.saveandexit_button);
        gobackButton = (Button) findViewById(R.id.goback_button);
        addQuestion();
        saveExit();
        goBack();
    }

    public void addQuestion(){
        addquestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermadeQuestion = questionEditView.getText().toString();
                String usermadeAnswer = answerEditView.getText().toString();
                if(usermadeAnswer.equals("") || usermadeQuestion.equals("") ){
                    Toast.makeText(AddQuestion.this, "Cannot Insert Empty Values", Toast.LENGTH_LONG).show();
                }else if(!usermadeAnswer.equals("yes") && !usermadeAnswer.equals("no")){
                    Toast.makeText(AddQuestion.this, "Answer Must be a Yes or No", Toast.LENGTH_LONG).show();
                }else{
                    Boolean tempUserAns;
                    if(usermadeAnswer.equals("yes")){
                        tempUserAns = true;
                    }else{
                        tempUserAns = false;
                    }
                    Boolean isInserted = MainActivity.myDb.insertData(usermadeQuestion, tempUserAns.toString());
                    if(isInserted = true){
                        Toast.makeText(AddQuestion.this, "Question Inserted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AddQuestion.this, "Question not Inserted", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public void saveExit(){
        saveandexitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddQuestion.this, MainActivity.class));
            }
        });
    }

    public void goBack(){
        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddQuestion.this, SettingsScreen.class));
            }
        });
    }
}