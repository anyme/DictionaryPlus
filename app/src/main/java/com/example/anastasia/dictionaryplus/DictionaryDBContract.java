package com.example.anastasia.dictionaryplus;

import android.provider.BaseColumns;

/**
 * Created by anastasia on 15/03/15.
 */
public final class DictionaryDBContract {
    public DictionaryDBContract() {}

    public final static class DictionaryColumns implements BaseColumns{
        public static final String TABLE_NAME = "persodictionary";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_IMAGE = "image";
    }

    public final static class MeaningsColumns implements BaseColumns{
        public static final String TABLE_NAME = "meanings";
        public static final String COLUMN_NAME_WORD_ID = "wordid";
        public static final String COLUMN_NAME_DEFINITION = "definition";
        public static final String COLUMN_NAME_POS = "pos";
        public static final String COLUMN_NAME_EXAMPLE = "examples";
    }
}
