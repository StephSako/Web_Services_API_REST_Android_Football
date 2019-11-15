package com.example.footballapi.view.activities;

import android.app.SearchManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.widget.SearchView;

import com.example.footballapi.R;
import com.example.footballapi.model.model_dao.AdapterRV_Search;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;

import java.util.List;

public class SearchTeamActivity extends AppCompatActivity {

    private RecyclerView rvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_team);

        this.setTitle("Chercher une équipe");

        rvSearch = findViewById(R.id.rvSearch);
        registerForContextMenu(rvSearch);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                showList(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void showList(String keyword){
        DataBase database = new DataBase(this);
        List<TeamDAO> searchList = database.findTeamByName(keyword);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSearch.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new AdapterRV_Search(searchList);
        rvSearch.setAdapter(mAdapter);
    }

}
