package com.eddyfidel.marvella.data.source.remote.api;

import com.eddyfidel.marvella.data.Article;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by eddyfidel on 10/23/17.
 */

public interface MarvelWikiaService {

    @GET("Articles/List")
    Call<Article> getArticle(@Query("expand") int expand, @Query("category") String category,
                             @Query("limit") int limit);
}
