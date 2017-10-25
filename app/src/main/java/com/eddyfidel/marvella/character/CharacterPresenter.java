package com.eddyfidel.marvella.character;

import com.eddyfidel.marvella.character.CharacterContract.Presenter;
import com.eddyfidel.marvella.data.Article;
import com.eddyfidel.marvella.data.Character;
import com.eddyfidel.marvella.data.source.CharacterRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void loadCharacters(final boolean showLoadingUI) {
        if (showLoadingUI) {
            mCharacterView.setLoadingIndicator(true);
        }

        mCharacterRepository.getMarvelWikiaService().getArticle(1, "Personajes", 25).enqueue(new Callback<Article>() {

            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (showLoadingUI) {
                    mCharacterView.setLoadingIndicator(false);
                }

                processCharacters(response.body().getItems());
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {

            }
        });
    }

    private void processCharacters(List<Character> characters) {
        mCharacterView.showCharacters(characters);
    }
}
