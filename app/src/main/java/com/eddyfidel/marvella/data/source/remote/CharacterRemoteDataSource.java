package com.eddyfidel.marvella.data.source.remote;

import com.eddyfidel.marvella.data.Article;
import com.eddyfidel.marvella.data.Character;
import com.eddyfidel.marvella.data.source.remote.api.MarvelWikiaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class CharacterRemoteDataSource {

    private static final String MARVEL_WIKIA_BASE_URL = "http://es.marvel.wikia.com/api/v1/";

    public MarvelWikiaService getMarvelWikiaService() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MARVEL_WIKIA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        MarvelWikiaService service = retrofit.create(MarvelWikiaService.class);

        return service;
    }
}
