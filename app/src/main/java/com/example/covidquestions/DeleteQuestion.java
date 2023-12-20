package com.example.covidquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteQuestion extends AppCompatActivity {

    TextView idView;
    EditText idEditView;
    Button deletequestionButton;
    Button saveandexitButton;
    Button gobackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);

        idView = (TextView) findViewById(R.id.id_view);
        idEditView = (EditText) findViewById(R.id.id_editview);
        deletequestionButton = (Button) findViewById(R.id.deletequestion_button);
        saveandexitButton = (Button) findViewById(R.id.saveandexit_button);
        gobackButton = (Button) findViewById(R.id.goback_button);
        DeleteData();
        goBack();
        saveExit();
    }

    public void DeleteData(){
        deletequestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userInt = Integer.parseInt(idEditView.getText().toString());
                if(userInt < 0 || userInt > MainActivity.questionList.size() + 1){
                    Toast.makeText(DeleteQuestion.this, "Invalid ID", Toast.LENGTH_LONG).show();
                }else{
                    Integer deletedRows = MainActivity.myDb.deleteData(idEditView.getText().toString());
                    if(deletedRows > 0){
                        Toast.makeText(DeleteQuestion.this, "Question Deleted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(DeleteQuestion.this, "Question not Deleted(Invalid ID)", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void saveExit(){
        saveandexitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteQuestion.this, MainActivity.class));
            }
        });
    }

    public void goBack(){
        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteQuestion.this, SettingsScreen.class));
            }
        });
    }
}