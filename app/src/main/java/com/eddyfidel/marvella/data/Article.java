package com.eddyfidel.marvella.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class Article {

    @SerializedName("items")
    @Expose
    private List<Character> items;

    public List<Character> getItems() {
        return items;
    }
}
