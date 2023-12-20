package com.example.covidquestions;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;
    Button settingsbutton;
    TextView questionView;
    Boolean hasFailed = false;
    int i = 0;
    public static DatabaseHelper myDb;
    public static List<question> questionList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "timer finshed", Toast.LENGTH_LONG);
        myDb = new DatabaseHelper(this);
        questionList = new ArrayList<question>();
        yesButton = (Button) findViewById(R.id.yes_button);
        noButton = (Button) findViewById(R.id.no_button);
        settingsbutton = (Button) findViewById(R.id.settings_button);
        questionView = (TextView) findViewById(R.id.question_textview);
        i = 0;
        hasFailed = false;

        questionView.setAutoSizeTextTypeUniformWithConfiguration(10, 32, 2, TypedValue.COMPLEX_UNIT_DIP);

        Cursor res = myDb.getAllData();
        while(res.moveToNext()){
            Boolean tempAns;
            if(res.getString(2).equals("true")){
                tempAns = true;
            }else{
                tempAns = false;
            }
            questionList.add(new question(res.getString(1), tempAns, true));
        }
        generatequestion();
        //myDb.insertData("Have you travelled outside the country?", "false");
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chooseanswer(true);
                }
            });
            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chooseanswer(false);
                }
            });
            settingsbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, PasswordProtected.class));
                }
            });

            onBackPressed();

        }

    public void generatequestion() {
        questionView.setText(questionList.get(i).getQuestionAsked());
    }

    public void chooseanswer(Boolean userans){

        if(i == questionList.size() - 1){
            if(userans != questionList.get(i).getCorrectAnswer() && questionList.get(i).getIsDecidingFactor() == true){
                hasFailed = true;
            }
            if(hasFailed == true){
                hasFailed = false;
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
                startActivity(new Intent(MainActivity.this, DeniedScreen.class));
                return;
            }
            yesButton.setEnabled(false);
            noButton.setEnabled(false);
            startActivity(new Intent(MainActivity.this, AcceptedScreen.class));
            return;
        }
        else if(userans != questionList.get(i).getCorrectAnswer() && questionList.get(i).getIsDecidingFactor() == true){
                hasFailed = true;
        }
        if(i < questionList.size()){
            i++;
            generatequestion();
        }
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}