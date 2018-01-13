package com.example.aprilwang.bookshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }

    public void chooseBookOption(View view){
        Intent intent= new Intent(  this,Book2ndPage.class);
        Button button=(Button) findViewById(R.id.button2);
        startActivity(intent);
    }

    public void chooseMemberOption(View view){
        Intent intent= new Intent(this,Member2ndPage.class);
        Button button=(Button) findViewById(R.id.button3);
        startActivity(intent);
}
}