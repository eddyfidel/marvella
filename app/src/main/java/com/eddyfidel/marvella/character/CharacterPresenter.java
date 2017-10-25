package com.eddyfidel.marvella.character;

import com.eddyfidel.marvella.character.CharacterContract.Presenter;
import com.eddyfidel.marvella.data.Character;
import com.eddyfidel.marvella.data.source.CharacterRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eddyfidel on 10/24/17.
 */

public class CharacterPresenter implements Presenter {

    private final CharacterRepository mCharacterRepository;

    private final CharacterContract.View mCharacterView;

    public CharacterPresenter(CharacterRepository mCharacterRepository, CharacterContract.View mCharacterView) {
        this.mCharacterRepository = mCharacterRepository;
        this.mCharacterView = mCharacterView;

        mCharacterView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCharacters();
    }

    @Override
    public void loadCharacters() {
        loadCharacters(true);
    }

    private void loadCharacters(boolean showLoadingUI) {
        if (showLoadingUI) {
            mCharacterView.setLoadingIndicator(true);
        }

        List<Character> charactersToShow = new ArrayList<Character>();

        mCharacterRepository.getCharacters(25);

        if (showLoadingUI) {
            mCharacterView.setLoadingIndicator(false);
        }

        processCharacters(charactersToShow);
    }

    private void processCharacters(List<Character> characters) {
        mCharacterView.showCharacters(characters);
    }
}
