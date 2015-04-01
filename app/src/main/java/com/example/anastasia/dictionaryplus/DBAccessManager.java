package com.example.anastasia.dictionaryplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.anastasia.dictionaryplus.DictionaryDBContract.*;

/**
 * Created by anastasia on 15/03/15.
 */
public class DBAccessManager {
    private SQLiteDatabase database;
    private DictionarySQLiteHelper dbHelper;
    private Cursor cursor;
    private Context context;
    private String whereCondition;
    private String[] whereArgs;

    public DBAccessManager(Context context) {
        this.context = context;
        this.dbHelper = new DictionarySQLiteHelper(context);
    }

    public void openWrite() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void openRead() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addWord(Word aWord) {
        openWrite();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DictionaryColumns.COLUMN_NAME_WORD, aWord.getWord());
        contentValues.put(DictionaryColumns.COLUMN_NAME_IMAGE, aWord.getImg());
        Log.d("ANASTASIA", "Putting Word : \n" + aWord.getAllAsString());
        return database.insert(DictionaryColumns.TABLE_NAME, null, contentValues);

    }
    public boolean addMeaning(Meaning aMeaning) {
        openWrite();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MeaningsColumns.COLUMN_NAME_DEFINITION, aMeaning.getDefinition());
        contentValues.put(MeaningsColumns.COLUMN_NAME_POS, aMeaning.getPos());
        contentValues.put(MeaningsColumns.COLUMN_NAME_WORD_ID, aMeaning.getWord_id());
        contentValues.put(MeaningsColumns.COLUMN_NAME_EXAMPLE, aMeaning.getExamples());

        long meaningID = database.insert(MeaningsColumns.TABLE_NAME, null, contentValues);
        Log.d("ANASTASIA", "Putting Meaning : \n" + aMeaning.getAllAsString());
        return meaningID == -1 ? false : true;

    }

    public boolean addWordWithMeaning(Word aWord, Meaning aMeaning) {
        long wordID = addWord(aWord);

        if (wordID == -1) { return false; }

        aMeaning.setWord_id(wordID);

        return addMeaning(aMeaning);
    }

    public long deleteWord(Word aWord) {
        whereCondition = DictionaryColumns.COLUMN_NAME_WORD + "=?";
        whereArgs = new String [] { aWord.getWord() };
        openWrite();

        return database.delete(DictionaryColumns.TABLE_NAME, whereCondition, whereArgs);

    }

    public long  deleteMeaning(Meaning aMeaning) {
        whereCondition = MeaningsColumns._ID + "=?";
        whereArgs = new String [] { "" + aMeaning.getId() } ;
        openWrite();

        return database.delete(DictionaryColumns.TABLE_NAME, whereCondition, whereArgs);


    }

    public long  deleteWordMeaning(Word aWord) {
        whereCondition = MeaningsColumns.COLUMN_NAME_WORD_ID + "=?";
        whereArgs = new String [] { "" + aWord.getId() } ;
        openWrite();

        return database.delete(DictionaryColumns.TABLE_NAME, whereCondition, whereArgs);


    }

    public Cursor getAllWordsCursor() {
        String selectQuery = "SELECT * FROM " + DictionaryColumns.TABLE_NAME;

        openRead();
        return database.rawQuery(selectQuery, null);
    }

    public ArrayList<Word> getAllWords() {
        ArrayList<Word> wordList = new ArrayList<Word>();

        cursor = getAllWordsCursor();

        if (cursor.moveToFirst()) {
            do {
                Word aWord = new Word();
                aWord.setId(Integer.parseInt(cursor.getString(0)));
                aWord.setWord(cursor.getString(1));
                aWord.setImg(cursor.getBlob(2));

                wordList.add(aWord);
            } while (cursor.moveToFirst());
        }

        cursor.close();

        return wordList;
    }

    public ArrayList<Meaning> getAllMeanings(Word aWord) {
        ArrayList<Meaning> meaningList = new ArrayList<Meaning>();

        String selectQuery = "SELECT * FROM " + MeaningsColumns.TABLE_NAME + " WHERE " + MeaningsColumns.COLUMN_NAME_WORD_ID + "=" + aWord.getId();

        Log.d("ANASTASIA", "Getting a meaning for the word id: \n" + aWord.getId());

        openRead();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Meaning aMeaning = new Meaning();
                aMeaning.setId(Integer.parseInt(cursor.getString(0)));
                aMeaning.setWord_id(Integer.parseInt(cursor.getString(1)));
                aMeaning.setDefinition(cursor.getString(2));
                aMeaning.setPos(cursor.getString(3));
                aMeaning.setExamples(cursor.getString(4));
                Log.d("ANASTASIA", "Getting a meaning : \n" + aMeaning.getAllAsString());
                meaningList.add(aMeaning);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return meaningList;

    }

    public Word getWord(String aWord) {
        Word word = new Word();
        String selectQuery = "SELECT * FROM " + DictionaryColumns.TABLE_NAME +  " WHERE " + DictionaryColumns.COLUMN_NAME_WORD + " LIKE '" + aWord + "'";

        openRead();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            word.setId(Integer.parseInt(cursor.getString(0)));
            word.setWord(cursor.getString(1));
            word.setImg(cursor.getBlob(2));
        }

        cursor.close();

        return word;
    }

    public Word getWord(int wordID) {
        Word word = new Word();
        String selectQuery = "SELECT * FROM " + DictionaryColumns.TABLE_NAME +  " WHERE " + DictionaryColumns._ID + " LIKE '" + wordID + "'";

        openRead();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            word.setId(Integer.parseInt(cursor.getString(0)));
            word.setWord(cursor.getString(1));
            word.setImg(cursor.getBlob(2));
        }

        cursor.close();
        Log.d("ANASTASIA", "Getting Word : \n" + word.getAllAsString());

        return word;
    }
}
