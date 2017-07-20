package com.madden.anrjustcards;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.madden.anrjustcards.model.NRDBModel;
import com.madden.anrjustcards.screens.catalog.CatalogFragment;
import com.madden.anrjustcards.screens.catalog.CatalogPresenter;


public class MainActivity extends AppCompatActivity {
    private NRDBModel model;
    private CatalogPresenter catalogPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        model = new NRDBModel();

        showCatalog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        catalogPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        catalogPresenter.onPause();
    }

    private void showCatalog() {
        CatalogFragment fragment = new CatalogFragment();
        catalogPresenter = new CatalogPresenter(fragment, model);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }
}