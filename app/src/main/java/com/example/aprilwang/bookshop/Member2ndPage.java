package com.example.aprilwang.bookshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Member2ndPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member2nd_page);
    }

    public void chooseViewMembers(View view){
        Intent intent= new Intent(  this,MemberListActivity.class);
        Button button=(Button) findViewById(R.id.ViewList);
        startActivity(intent);
    }

    public void chooseAddNewMember(View view){
        Intent intent= new Intent(this,CreateMembersActivity.class);
        Button button=(Button) findViewById(R.id.CreateMembers);
        startActivity(intent);
    }
}
