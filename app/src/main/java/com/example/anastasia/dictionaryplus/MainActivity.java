package com.example.anastasia.dictionaryplus;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.widget.AdapterView.*;


public class MainActivity extends ActionBarActivity {

    private Context self;
    private Cursor wordListCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self = this;
        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(self, AddNewWordActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        ListView listview = (ListView) findViewById(R.id.wordListView);
        DBAccessManager db = new DBAccessManager(self);
        wordListCursor = db.getAllWordsCursor();
        WordListAdapter aWordListAdapter = new WordListAdapter(this, wordListCursor);
        listview.setAdapter(aWordListAdapter);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (wordListCursor.moveToPosition(position)) {
                    int wordID = wordListCursor.getInt(0);
                    Log.d("ANASTASIA", "opening WordDetails , wordID is = " + wordID);
                    Intent intent = new Intent(self, WordDetails.class);
                    intent.putExtra("wordID", wordID);
                    startActivity(intent);
                }
            }
        });
    }


}
