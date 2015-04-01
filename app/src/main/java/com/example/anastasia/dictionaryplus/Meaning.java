package com.example.anastasia.dictionaryplus;

/**
 * Created by anastasia on 15/03/15.
 */
public class Meaning {
    private long id;
    private long word_id;
    private String definition;
    private String pos;
    private String examples;

    public Meaning() {
        this.id = -1;
        this.word_id = -1;
        this.definition = "";
        this.pos = "";
        this.examples = "";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setWord_id(long word_id) {
        this.word_id = word_id;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public long getId() {
        return id;
    }

    public long getWord_id() {
        return word_id;
    }

    public String getDefinition() {
        return definition;
    }

    public String getPos() {
        return pos;
    }

    public String getExamples() {
        return examples;
    }

    public String getAllAsString() {
        return "id = " + id + "\n" +
                "word_id = " + word_id + "\n" +
                "definition = " + definition + "\n" +
                "pos = " + pos + "\n" +
                "examples = " + examples + "\n";
    }
}
