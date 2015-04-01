package com.example.anastasia.dictionaryplus;

import android.widget.ImageView;

import java.sql.Blob;

/**
 * Created by anastasia on 15/03/15.
 */
public class Word {
    private int id;
    private String word;
    private byte[] img;

    public Word() {
        this.id = -1;
        this.word = "";
        this.img = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    //TODO create new byte object (release img object)
    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public byte[] getImg() {
        return img;
    }

    public String getAllAsString() {
        return "id = " + id + "\n" +
                "word = " + word + "\n" ;
    }
}
