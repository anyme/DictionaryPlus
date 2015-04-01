package com.example.anastasia.dictionaryplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.util.ArrayList;


public class WordDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);

        Intent intent = getIntent();
        int wordID = intent.getIntExtra("wordID", -1);

        if (wordID == -1) { this.finish(); }

        DBAccessManager db = new DBAccessManager(this);

        Word aWord = db.getWord(wordID);
        ArrayList<Meaning> meanings = db.getAllMeanings(aWord);

        TextView tv = (TextView) findViewById(R.id.wordTextView);
        tv.setText(aWord.getWord());

        tv = (TextView) findViewById(R.id.subtitleTextView);
        if (meanings.size() == 1) {
            tv.setText(meanings.get(0).getPos());
            tv = (TextView) findViewById(R.id.definitionTextView);
            tv.setText(meanings.get(0).getDefinition());
        }
    }


}
