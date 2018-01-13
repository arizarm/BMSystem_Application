package com.example.aprilwang.bookshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateMembersActivity extends AppCompatActivity {

    final static int []view = {R.id.createEditText1, R.id.createEditText2, R.id.createEditText3, R.id.createEditText4};
    final static String []key = {"MemberName", "Email", "PhoneNo", "Password"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_members);
        Button b = (Button) findViewById(R.id.confirmButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Member c = new Member();
                for (int i=0; i<view.length; i++) {
                    EditText t = (EditText) findViewById(view[i]);
                    c.put(key[i], t.getText().toString());
                }
                new AsyncTask<Member, Void, Void>() {
                    @Override
                    protected Void doInBackground(Member... params) {
                        Member.createMember(params[0]);
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        Intent intent = new Intent(CreateMembersActivity.this, MemberListActivity.class);
                        startActivity(intent);
                    }
                }.execute(c);
            }
        });
    }

}
