package com.ll.wiseSaying;

public class WiseSaying {
    private final int id;
    private String wiseSaying;
    private String author;

    public WiseSaying(int id, String wiseSaying, String author){
        this.id = id;
        this.wiseSaying = wiseSaying;
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWiseSaying(String wiseSaying) {
        this.wiseSaying = wiseSaying;
    }

    public String getAuthor() {
        return author;
    }

    public String getWiseSaying() {
        return wiseSaying;
    }

    public int getId() { return id; }



}
