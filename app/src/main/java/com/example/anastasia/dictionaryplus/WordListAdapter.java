package com.example.anastasia.dictionaryplus;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by anastasia on 22/03/15.
 */
public class WordListAdapter extends CursorAdapter {
    public WordListAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.word_list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView wordTextView = (TextView) view.findViewById(R.id.wordTextView);
        wordTextView.setText(cursor.getString(1));
    }
}
