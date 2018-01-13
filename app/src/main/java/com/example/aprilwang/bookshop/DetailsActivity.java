package com.example.aprilwang.bookshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DetailsActivity extends AppCompatActivity {

    final static int []view = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4};
    final static String []key = {"MemberName", "Email", "PhoneNo", "Password"};
    Member a = new Member();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String item = getIntent().getExtras().getString("MemberName");
        new AsyncTask<String, Void, Member>() {
            @Override
            protected Member doInBackground(String... params) {
                a = Member.getMember(params[0]);
                return a;
            }

            @Override
            protected void onPostExecute(Member result) {
                for (int i = 0; i < view.length; i++) {
                    EditText t = (EditText) findViewById(view[i]);
                    t.setText(result.get(key[i]));
                }
            }
        }.execute(item);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Member c = new Member();
                boolean isEmpty = false;
                for (int i = 0; i < view.length; i++) {
                    EditText t = (EditText) findViewById(view[i]);
                    if (t.getText().length() == 0) {
                        t.requestFocus();
                        t.setError("Should not be empty");
                        //Toast.makeText(getApplicationContext(), "Uh oh...", Toast.LENGTH_LONG).show();
                        isEmpty=true;
                    }
                }
                if (!isEmpty) {
                    for (int i = 0; i < view.length; i++) {
                        EditText t = (EditText) findViewById(view[i]);
                        c.put(key[i], t.getText().toString());

                        new AsyncTask<Member, Void, Void>() {
                            @Override
                            protected Void doInBackground(Member... params) {
                                Member.updateMember(params[0]);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void result) {
                                Intent intent = new Intent(DetailsActivity.this, MemberListActivity.class);
                                try {
                                    Boolean b = false;
                                    for (int i = 0; i < view.length; i++)
                                        if (a.get(key[i]) != (c.get(key[i])) && !(c.get(key[i]).isEmpty())) {
                                            b = true;
                                        }
                                    if (b)
                                        Toast.makeText(getApplicationContext(), "Update Success!", Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    // Toast.makeText(getApplicationContext(), "Update Failed!", Toast.LENGTH_LONG).show();
                                }
                                startActivity(intent);
                            }
                        }.execute(c);
                    }
                }
            }
        });
    }
}
