package com.example.aprilwang.bookshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateBooksActivity extends AppCompatActivity {

    final static int []view = {R.id.createET, R.id.createET2, R.id.createET3, R.id.createET4, R.id.createET5};
    final static String []key = {"Stock", "Title", "Author", "Isbn", "Price"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_books);
        Button b = (Button) findViewById(R.id.CreateButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book c = new Book();
                for (int i=0; i<view.length; i++) {
                    EditText t = (EditText) findViewById(view[i]);
                    c.put(key[i], t.getText().toString());
                }
                new AsyncTask<Book, Void, Void>() {
                    @Override
                    protected Void doInBackground(Book... params) {
                        Book.createBook(params[0]);
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        Intent intent = new Intent(CreateBooksActivity.this, BookMainActivity.class);
                        startActivity(intent);
                    }
                }.execute(c);
            }
        });
    }
}
