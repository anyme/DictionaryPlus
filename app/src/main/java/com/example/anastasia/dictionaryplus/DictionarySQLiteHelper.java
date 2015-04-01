package com.example.anastasia.dictionaryplus;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.anastasia.dictionaryplus.DictionaryDBContract.*;

/**
 * Created by anastasia on 15/03/15.
 */
public class DictionarySQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DictionaryPlus.db";

    private static final String SQL_CREATE_DICTIONARY_QUERY;
    private static final String SQL_CREATE_MEANINGS_QUERY;
    private static final String TEXT_TYPE = " TEXT";
    private static final String IMAGE_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";

    static {
        SQL_CREATE_DICTIONARY_QUERY = "CREATE TABLE " + DictionaryColumns.TABLE_NAME + " (" +
                DictionaryColumns._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                DictionaryColumns.COLUMN_NAME_WORD + TEXT_TYPE + COMMA_SEP +
                DictionaryColumns.COLUMN_NAME_IMAGE + IMAGE_TYPE  +
                " )";
        SQL_CREATE_MEANINGS_QUERY = "CREATE TABLE " + MeaningsColumns.TABLE_NAME + " (" +
                MeaningsColumns._ID + " INTEGER PRIMARY KEY " + COMMA_SEP +
                MeaningsColumns.COLUMN_NAME_WORD_ID + TEXT_TYPE + COMMA_SEP +
                MeaningsColumns.COLUMN_NAME_DEFINITION + TEXT_TYPE + COMMA_SEP +
                MeaningsColumns.COLUMN_NAME_POS + TEXT_TYPE + COMMA_SEP +
                MeaningsColumns.COLUMN_NAME_EXAMPLE + TEXT_TYPE +
                " )";
        //"REFERENCES " + DictionaryColumns.TABLE_NAME + " ON DELETE CASCADE" + COMMA_SEP +
    }

    public DictionarySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DICTIONARY_QUERY);
        db.execSQL(SQL_CREATE_MEANINGS_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IS EXISTS " + DictionaryColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IS EXISTS " + MeaningsColumns.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
