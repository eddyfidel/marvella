package com.eddyfidel.marvella.data.source;

import com.eddyfidel.marvella.data.Character;
import com.eddyfidel.marvella.data.source.remote.CharacterRemoteDataSource;

import java.util.List;

/**
 * Created by eddyfidel on 10/23/17.
 */

public class CharacterRepository implements CharacterDataSource {

    private final CharacterRemoteDataSource mCharacterRemoteDataSource;

    public CharacterRepository(CharacterRemoteDataSource characterRemoteDataSource) {
        this.mCharacterRemoteDataSource = characterRemoteDataSource;
    }

    @Override
    public List<Character> getCharacters(int limit) {
        return mCharacterRemoteDataSource.getCharacters(limit);
    }
}
