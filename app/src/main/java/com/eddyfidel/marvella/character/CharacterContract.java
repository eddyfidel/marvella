package com.eddyfidel.marvella.character;

import com.eddyfidel.marvella.common.BasePresenter;
import com.eddyfidel.marvella.common.BaseView;
import com.eddyfidel.marvella.data.Character;

import java.util.List;

/**
 * Created by eddyfidel on 10/23/17.
 */

public interface CharacterContract {

    interface Presenter extends BasePresenter {

        void loadCharacters();
    }

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showCharacters(List<Character> characters);

        void showLoadingCharactersError();
    }

}
