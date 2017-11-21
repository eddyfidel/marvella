package com.eddyfidel.marvella.character;

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

public class CharacterPresenter implements CharacterContract.Presenter {

    private final CharacterRepository mCharacterRepository;

    private final CharacterContract.View mCharacterView;

    public CharacterPresenter(CharacterRepository repository, CharacterContract.View view) {
        mCharacterRepository = repository;
        mCharacterView = view;

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

    private void loadCharacters(final boolean showLoadingUi) {
        if (showLoadingUi) {
            mCharacterView.setLoadingIndicator(true);
        }

        mCharacterRepository.getMarvelWikiaService().getArticle("Top", 1, "Characters", 25)
                .enqueue(new Callback<Article>() {

            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (showLoadingUi) {
                    mCharacterView.setLoadingIndicator(false);
                }

                processCharacters(response.body().getItems());
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                if (showLoadingUi) {
                    mCharacterView.setLoadingIndicator(false);
                }

                mCharacterView.showLoadingCharactersError();
            }
        });
    }

    private void processCharacters(List<Character> characters) {
        mCharacterView.showCharacters(characters);
    }

}
