package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView mSearchBar;
    private ListView mListView;

    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> mEmptyAdapter;
    private List<String> mItems = Arrays.asList(
            "Dog",
            "Cat",
            "Whale",
            "Shark",
            "Scorpion",
            "Underwater Bird"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] emptyList = new String[] {"No record found"};
        mListView = (ListView)findViewById(R.id.items_container);
        mSearchBar = (SearchView)findViewById(R.id.search_bar);

        mSearchBar.setOnQueryTextListener(searchListener);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItems);
        mEmptyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, emptyList);

        mListView.setAdapter(mEmptyAdapter);
    }

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String term) {

            // @todo query firebase source and display the list

            mListView.setAdapter(mAdapter);

            if (mItems.contains(term)) {
                mAdapter.getFilter().filter(term);
            } else {
                mListView.setAdapter(mEmptyAdapter);
            }

            mAdapter.notifyDataSetChanged();

            return false;
        }
    };

}
