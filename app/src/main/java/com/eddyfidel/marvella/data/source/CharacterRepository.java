package com.eddyfidel.marvella.data.source;

import com.eddyfidel.marvella.data.Character;
import com.eddyfidel.marvella.data.source.remote.CharacterRemoteDataSource;
import com.eddyfidel.marvella.data.source.remote.api.MarvelWikiaService;

import java.util.List;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class CharacterRepository {

    private final CharacterRemoteDataSource mCharacterRemoteDataSource;

    public CharacterRepository(CharacterRemoteDataSource characterRemoteDataSource) {
        this.mCharacterRemoteDataSource = characterRemoteDataSource;
    }

    public MarvelWikiaService getMarvelWikiaService() {
        return mCharacterRemoteDataSource.getMarvelWikiaService();
    }
}
