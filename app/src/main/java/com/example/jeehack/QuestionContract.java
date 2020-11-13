package com.example.jeehack;

import android.provider.BaseColumns;

public final class QuestionContract {
    private QuestionContract(){

    }
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions_table";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWERNUM = "answernum";
    }
}
