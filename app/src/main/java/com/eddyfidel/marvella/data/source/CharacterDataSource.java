package com.eddyfidel.marvella.data.source;

import com.eddyfidel.marvella.data.Character;

import java.util.List;

/**
 * Created by eddyfidel on 10/23/17.
 */

public interface CharacterDataSource {

    List<Character> getCharacters(int limit);
}
