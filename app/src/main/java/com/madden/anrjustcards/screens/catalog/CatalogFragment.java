package com.madden.anrjustcards.screens.catalog;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madden.anrjustcards.R;
import com.madden.anrjustcards.model.pojo.Cards;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.subjects.BehaviorSubject;


public class CatalogFragment extends Fragment implements CatalogContract.View {
    private CatalogAdapter adapter;
    private Unbinder unbinder;
    BehaviorSubject<String> searchObservable = BehaviorSubject.create("");

    @BindView(R.id.card_catalog_recycler)
    RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.catalog, container, false);
        unbinder = ButterKnife.bind(this, root);
        setHasOptionsMenu(true);

        adapter = new CatalogAdapter();
        recycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(root.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchObservable.onCompleted();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchObservable.onNext(newText);
                return true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCardsReceived(Cards cards) {
        adapter.setCards(cards);
    }

    @Override
    public Observable<String> createSearchObservable() {
        return searchObservable;
    }
}