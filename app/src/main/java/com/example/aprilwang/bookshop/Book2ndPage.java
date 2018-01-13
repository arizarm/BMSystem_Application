package com.example.aprilwang.bookshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Book2ndPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2nd_page);
    }


    public void chooseViewBooks(View view){
        Intent intent= new Intent(  this,BookMainActivity.class);
        Button button=(Button) findViewById(R.id.BookList);
        startActivity(intent);
    }

/*    public void chooseAddNewBook(View view){
        Intent intent= new Intent(this,CreateBooksActivity.class);
        Button button=(Button) findViewById(R.id.CreateBooks);
        startActivity(intent);
    }*/
}
