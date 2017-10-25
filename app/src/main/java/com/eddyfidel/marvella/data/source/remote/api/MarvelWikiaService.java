package com.eddyfidel.marvella.data.source.remote.api;

import com.eddyfidel.marvella.data.Article;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eddyfidel on 10/23/17.
 */

public interface MarvelWikiaService {

    @GET("Articles/List?expand=1&category=Personajes&limit={limit}")
    Call<Article> getArticle(@Path("limit") int limit);
}
