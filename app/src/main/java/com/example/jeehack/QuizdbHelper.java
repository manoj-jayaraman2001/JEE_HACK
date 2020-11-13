package com.example.jeehack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.jeehack.QuestionContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizdbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizdbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "+
                QuestionsTable.TABLE_NAME+ " ( "+
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionsTable.COLUMN_QUESTION + " TEXT, "+
                QuestionsTable.COLUMN_OPTION1 + " TEXT, "+
                QuestionsTable.COLUMN_OPTION2 + " TEXT, "+
                QuestionsTable.COLUMN_OPTION3 + " TEXT, "+
                QuestionsTable.COLUMN_ANSWERNUM + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ QuestionsTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable(){
        Question q1 = new Question("If set A is subset of set B then AꓵB is equal to","A","B","C",1);
        addQuestion(q1);
        Question q2 = new Question("If A = {1,2,3,4} then the numbers of subsets of A are","12","16","11",2);
        addQuestion(q2);
        Question q3 = new Question("If A = {2,5,7} and B = {1,2,5,6,7} then A-B =","{2,7}","{5,7}","{}",3);
        addQuestion(q3);
        Question q4 = new Question("If A = {2,5,7} and B = {1,2,5,6,7} then B-A =","{1,6}","{1,2}","{}",1);
        addQuestion(q4);
        Question q5 = new Question("A = {8,9,10} and B = {} then A union B = ","{8,9,10}","{8,9}","{}",2);
        addQuestion(q5);
    }
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWERNUM, question.getAnswernum());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);

    }

    public List<Question> getallQuestions(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+QuestionsTable.TABLE_NAME,null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswernum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERNUM)));
                questionList.add(question);


            }while(c.moveToNext());
        }
        c.close();
        return questionList;

    }
}
