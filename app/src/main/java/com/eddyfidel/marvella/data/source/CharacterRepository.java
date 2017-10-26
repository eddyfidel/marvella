package com.eddyfidel.marvella.data.source;

import com.eddyfidel.marvella.data.source.remote.CharacterRemoteDataSource;
import com.eddyfidel.marvella.data.source.remote.api.MarvelWikiaService;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class CharacterRepository {

    private final CharacterRemoteDataSource mCharacterRemoteDataSource;

    public CharacterRepository(CharacterRemoteDataSource characterRemoteDataSource) {
        mCharacterRemoteDataSource = characterRemoteDataSource;
    }

    public MarvelWikiaService getMarvelWikiaService() {
        return mCharacterRemoteDataSource.getMarvelWikiaService();
    }
}
