package com.eddyfidel.marvella.character;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eddyfidel.marvella.R;
import com.eddyfidel.marvella.data.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterFragment extends Fragment implements CharacterContract.View {

    private CharacterContract.Presenter mPresenter;

    private CharacterAdapter mCharacterAdapter;

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

        mCharacterAdapter = new CharacterAdapter(new ArrayList<Character>(0));
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    public void setPresenter(CharacterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.character_fragment, container, false);

        ListView listView = (ListView) root.findViewById(R.id.characterListView);

        listView.setAdapter(mCharacterAdapter);

        ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refreshLayout);

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimary),
            ContextCompat.getColor(getActivity(), R.color.colorAccent),
            ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mPresenter.loadCharacters();
            }
        });

        return root;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.refreshLayout);

        refreshLayout.post(new Runnable() {

            @Override
            public void run() {
                refreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showCharacters(List<Character> characters) {
        mCharacterAdapter.replaceData(characters);
    }

    private static class CharacterAdapter extends BaseAdapter {

        private List<Character> mCharacters;

        public CharacterAdapter(List<Character> characters) {
            setList(characters);
        }

        public void replaceData(List<Character> characters) {
            mCharacters = characters;

            notifyDataSetChanged();
        }

        private void setList(List<Character> characters) {
            mCharacters = characters;
        }

        @Override
        public int getCount() {
            return mCharacters.size();
        }

        @Override
        public Character getItem(int position) {
            return mCharacters.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                rowView = inflater.inflate(R.layout.character_item, parent, false);
            }

            Character character = getItem(position);

            TextView titleTexView = (TextView) rowView.findViewById(R.id.title);

            titleTexView.setText(character.getTitle());

            return rowView;
        }
    }
}
