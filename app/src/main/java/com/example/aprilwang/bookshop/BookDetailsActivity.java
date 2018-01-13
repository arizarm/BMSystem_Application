package com.example.aprilwang.bookshop;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.aprilwang.bookshop.DetailsActivity.key;
import static com.example.aprilwang.bookshop.DetailsActivity.view;

public class BookDetailsActivity extends Activity implements View.OnClickListener {
    final static int[] ids = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5, R.id.editText6};
    final static String[] keys = {"BookID", "Title", "Author", "ISBN", "Price", "Stock"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        Intent i = getIntent();
        String bid = i.getStringExtra("Id");

        new AsyncTask<String, Void, Book>() {
            @Override
            protected Book doInBackground(String... params) {
                //Customer.updateCustomer(params[0]);
                return Book.getBook(params[0]);
            }

            @Override
            protected void onPostExecute(Book result) {
                show(result);
            }
        }.execute(bid);


        Button but = (Button) findViewById(R.id.button);
        but.setOnClickListener(this);
    }

    void show(Book book) {
        for (int i = 0; i < keys.length; i++) {
            EditText e = (EditText) findViewById(ids[i]);
            e.setText(book.get(keys[i]));
        }
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageBitmap(Book.getPhoto(book.get("Isbn")));

    }

    @Override
    public void onClick(View v) {
        final Book c = new Book();
        boolean isEmpty = false;
        for (int i = 0; i < view.length; i++) {
            EditText t = (EditText) findViewById(view[i]);
            if (t.getText().length()==0 || t.getText().equals("0")) {
                t.requestFocus();
                t.setError("Should not be empty");
                //Toast.makeText(getApplicationContext(), "Uh oh...", Toast.LENGTH_LONG).show();
                isEmpty = true;
            }
        }
        if (!isEmpty) {
            for (int i = 0; i < ids.length; i++) {
                EditText t = (EditText) findViewById(ids[i]);
                c.put(keys[i], t.getText().toString());
            }
            new AsyncTask<Book, Void, Void>() {
                @Override
                protected Void doInBackground(Book... params) {
                    Book.updateCustomer(params[0]);
                    return null;

                }

                @Override
                protected void onPostExecute(Void result) {
                    Intent intent = new Intent(BookDetailsActivity.this, BookMainActivity.class);

                            Toast.makeText(getApplicationContext(), "Update Success!", Toast.LENGTH_LONG).show();

                    startActivity(intent);
                }
            }.execute(c);
        }
    }
}
