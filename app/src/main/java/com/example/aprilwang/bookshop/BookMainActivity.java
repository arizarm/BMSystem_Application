package com.example.aprilwang.bookshop;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.List;

public class BookMainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    SimpleAdapter sa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        setContentView(R.layout.activity_book_main);
        final ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setOnItemClickListener(this);
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... params) {
                //Book.list(params[0]);
                return Book.list();
            }

            @Override
            protected void onPostExecute(List<Book> result) {
                lv.setAdapter(sa = new SimpleAdapter
                        (BookMainActivity.this, result, R.layout.row, new String[]{"BookID", "Title","Price"},
                                new int[]{R.id.text1, R.id.text2,R.id.text3}));

            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sa.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Book b = (Book) av.getAdapter().getItem(position);
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("Id", b.get("BookID"));
        startActivity(intent);
    }

}
