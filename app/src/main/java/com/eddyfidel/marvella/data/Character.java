package com.eddyfidel.marvella.data;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class Character {

    private int id;

    private String title;

    private String thumbnail;

    public Character(int id, String title, String thumbnail) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
