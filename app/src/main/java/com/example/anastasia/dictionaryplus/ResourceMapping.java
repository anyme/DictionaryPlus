package com.example.anastasia.dictionaryplus;

/**
 * Created by anastasia on 22/03/15.
 */
public final class ResourceMapping {

    static String getStringID (int id) {
        switch (id) {
            case R.id.verb:
                return "verb";
            case R.id.noun:
                return "noun";
            case R.id.adjectif:
                return "adjectif";
            case R.id.adverb:
                return "adverb";
            case R.id.preposition:
                return "preposition";
        }
        return "";
    }

}
