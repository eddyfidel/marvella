package com.eddyfidel.marvella.character;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eddyfidel.marvella.R;
import com.eddyfidel.marvella.data.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CharacterFragment extends Fragment implements CharacterContract.View {

    @BindView(R.id.list_characters) ListView listView;

    @BindView(R.id.swipe_refresh) ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;

    private CharacterContract.Presenter mCharacterPresenter;

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

        listView.setAdapter(mCharacterAdapter);

        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        swipeRefreshLayout.setScrollUpChild(listView);

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

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) getView()
                .findViewById(R.id.swipe_refresh);

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

    @Override
    public void showLoadingCharactersError() {
        Toast.makeText(getContext(), R.string.loading_characters_error, Toast.LENGTH_SHORT).show();
    }

    private static class CharacterAdapter extends BaseAdapter {

        private Context mContext;

        private List<Character> mCharacters;

        public CharacterAdapter(Context context, List<Character> characters) {
            mContext = context;

            setList(characters);
        }

        public void replaceData(List<Character> characters) {
            setList(characters);

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

                rowView = inflater.inflate(R.layout.item_character, parent, false);
            }

            Character character = getItem(position);

            TextView titleTexView = (TextView) rowView.findViewById(R.id.text_title);

            ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.image_thumbnail);

            titleTexView.setText(character.getTitle());

            Picasso.with(mContext).load(character.getThumbnail()).into(thumbnailImageView);

            return rowView;
        }
    }
}
