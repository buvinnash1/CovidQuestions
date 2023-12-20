package com.example.covidquestions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CovidQuestions.db";
    public static final String TABLE_NAME = "CovidQuestions_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Question";
    public static final String COL_3 = "Answer";
    List<question> questionList = new ArrayList<question>();
    question question1 = new question("Have you experienced fevers or chills in the past 48 hours?", false, true);
    question question2 = new question("Have you experienced cough, shortness of breath or difficulty breathing in the past 48 hours?", false, true);
    question question3 = new question("Have you experienced fatigue, muscle or body aches, or headaches in the past 48 hours?", false, true);
    question question4 = new question("Have you experienced new loss of taste or smell in the past 48 hours?", false, true);
    question question5 = new question("Have you experienced sore throat, congestion or runny nose, nausea or vomiting, diarrhea in the past 48 hours?", false, true);

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);
        questionList.add(question5);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT, ANSWER TEXT)");
        for (int i = 0; i < questionList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, questionList.get(i).getQuestionAsked());
            contentValues.put(COL_3, questionList.get(i).getCorrectAnswer() + "");
            database.insert(TABLE_NAME, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String question, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, question);
        contentValues.put(COL_3, answer);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public boolean setDefaultValues(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 1, 2);
        return true;
    }

    public boolean updateData(String id, String question, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, question);
        contentValues.put(COL_3, answer);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}

