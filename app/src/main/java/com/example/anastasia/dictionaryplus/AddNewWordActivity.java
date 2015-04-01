package com.example.anastasia.dictionaryplus;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class AddNewWordActivity extends ActionBarActivity {

    DBAccessManager db;
    Activity self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        setContentView(R.layout.activity_add_new_word);
        db = new DBAccessManager(this);
        Button addButton = (Button) findViewById(R.id.add_new_word_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word aWord = new Word();
                Meaning aMeaning = new Meaning();
                Log.d("ANASTASIA", "Click on view :" + v.toString());
                EditText tv = (EditText) findViewById(R.id.newWordEditText);
                if (!tv.getText().toString().isEmpty()) {
                    aWord.setWord(tv.getText().toString());
                } else {
                    //create alert to fill in all the fields
                }
                tv = (EditText) findViewById(R.id.definitionEditText);
                if (!tv.getText().toString().isEmpty()) {
                    aMeaning.setDefinition(tv.getText().toString());
                }  else {
                    //create alert to fill in all the fields
                }
                RadioGroup rbg = (RadioGroup) findViewById(R.id.radioGroup);
                int checkedBtn = rbg.getCheckedRadioButtonId();
                if (checkedBtn != -1) {
                    aMeaning.setPos(ResourceMapping.getStringID(checkedBtn));
                }  else {
                    //create alert to fill in all the fields
                }
                tv = (EditText) findViewById(R.id.exampleEditText);
                if (!tv.getText().toString().isEmpty()) {
                    aMeaning.setExamples(tv.getText().toString());
                }  else {
                    //create alert to fill in all the fields
                }
                db.addWordWithMeaning(aWord, aMeaning);
                self.finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
