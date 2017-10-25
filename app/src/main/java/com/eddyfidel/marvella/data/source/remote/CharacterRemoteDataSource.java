package com.eddyfidel.marvella.data.source.remote;

import com.eddyfidel.marvella.data.Article;
import com.eddyfidel.marvella.data.Character;
import com.eddyfidel.marvella.data.source.CharacterDataSource;
import com.eddyfidel.marvella.data.source.remote.api.MarvelWikiaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class CharacterRemoteDataSource implements CharacterDataSource {

    private static final String MARVEL_WIKIA_BASE_URL = "http://es.marvel.wikia.com/api/v1/";

    @Override
    public List<Character> getCharacters(int limit) {
//        Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(MARVEL_WIKIA_BASE_URL)
//            .build();
//
//        MarvelWikiaService service = retrofit.create(MarvelWikiaService.class);
//
//        Article article = (Article) service.getArticle(limit);

        List<Character> characters = new ArrayList<Character>();

        characters.add(new Character(1, "Spider-Man", null));
        characters.add(new Character(2, "Iron man", null));
        characters.add(new Character(3, "Hulk", null));

        return characters;
    }
}
