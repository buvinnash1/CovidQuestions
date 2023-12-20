package com.example.covidquestions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class SettingsScreen extends AppCompatActivity {

    TextView questionview;
    TextView answerview;
    EditText questionEditView;
    EditText answerEditView;
    Button addquestionButton;
    Button setdefaultButton;
    Button deletequestionButton;
    Button viewquestionsButton;
    Button saveandexitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        questionview = (TextView) findViewById(R.id.question_view);
        answerview = (TextView) findViewById(R.id.answer_view);
        questionEditView = (EditText) findViewById(R.id.question_editview);
        answerEditView = (EditText) findViewById(R.id.answer_editview);
        addquestionButton = (Button) findViewById(R.id.addquestion_button);
        setdefaultButton = (Button) findViewById(R.id.setdefault_button);
        deletequestionButton = (Button) findViewById(R.id.delete_button);
        saveandexitButton = (Button) findViewById(R.id.saveandexit_button);
        viewquestionsButton = (Button) findViewById(R.id.viewdata_button);

        addQuestion();
        setDefaultQuestions();
        ViewAll();
        saveExit();
        DeleteData();
        onBackPressed();
    }

    public void addQuestion(){
       addquestionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(SettingsScreen.this, AddQuestion.class));
           }
       });
    }

    public void setDefaultQuestions(){
        setdefaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isInserted = MainActivity.myDb.setDefaultValues();
                if(isInserted = true){
                    Toast.makeText(SettingsScreen.this, "Set to Default Values", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SettingsScreen.this, "Failed to set to Default Values", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void saveExit(){
        saveandexitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsScreen.this, MainActivity.class));
            }
        });
    }

    public void ViewAll(){
        viewquestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = MainActivity.myDb.getAllData();
                if(res.getCount() == 0){
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("Question: " + res.getString(1) + "\n");
                    if(res.getString(2).equals("true")){
                        buffer.append("Correct Answer: " + "yes" + "\n\n");
                    }else{
                        buffer.append("Correct Answer: " + "no" + "\n\n");
                    }
                }
                //show all data
                showMessage("Question Database", buffer.toString());
            }
        });
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void DeleteData(){
        deletequestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsScreen.this, DeleteQuestion.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;

    }
}