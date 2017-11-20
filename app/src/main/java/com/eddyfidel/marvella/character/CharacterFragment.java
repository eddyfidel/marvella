package com.eddyfidel.marvella.character;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eddyfidel.marvella.R;
import com.eddyfidel.marvella.data.Character;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by eddyfidel on 11/2/17.
 */

public class CharacterFragment extends Fragment implements CharacterContract.View {

    @BindView(R.id.recycler_characters) RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh) ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;

    private CharacterContract.Presenter mCharacterPresenter;

    private CharacterAdapter mCharacterAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    public CharacterFragment() {
        // Required empty public constructor
    }

    public static CharacterFragment newInstance() {
        CharacterFragment fragment = new CharacterFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCharacterAdapter = new CharacterAdapter(getContext(), new ArrayList<Character>(0));
    }

    @Override
    public void onResume() {
        super.onResume();

        mCharacterPresenter.loadCharacters();
    }

    @Override
    public void setPresenter(CharacterContract.Presenter presenter) {
        mCharacterPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        unbinder = ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mCharacterAdapter);

        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        swipeRefreshLayout.setScrollUpChild(recyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mCharacterPresenter.loadCharacters();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showCharacters(List<Character> characters) {
        mCharacterAdapter.replaceData(characters);
    }

    @Override
    public void showLoadingCharactersError() {
        Toast.makeText(getContext(), R.string.loading_characters_error, Toast.LENGTH_SHORT).show();
    }

}
