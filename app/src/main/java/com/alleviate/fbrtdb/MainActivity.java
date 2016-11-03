package com.alleviate.fbrtdb;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tv_full_name, tv_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CoLabNotes");
        getSupportActionBar().hide();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        // With disk persistence enabled, our synced data and writes will be persisted to disk across
        // app restarts and our app should work seamlessly in offline situations.

        tv_full_name = (TextView)findViewById(R.id.full_name);
        tv_user_name = (TextView)findViewById(R.id.user_name);

        FloatingActionButton save_user = (FloatingActionButton)findViewById(R.id.save_user_profile);
        save_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_ful_name = tv_full_name.getText().toString();
                String str_user_name = tv_user_name.getText().toString();

                add_user_profile(str_ful_name, str_user_name);

                Snackbar.make(view,"User Created!",Snackbar.LENGTH_SHORT).show();

            }
        });


    }

    private void add_user_profile(String str_ful_name, String str_user_name) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("colabnotes/");
        // >>> https://fbrtdb.firebaseio.com/colabnotes/

        // Since each user will have a unique username, it makes sense to use the set method here instead
        // of the push method since we already have the key and don't need to create one.

        DatabaseReference user_add_ref = databaseReference.child("user");

        Map<String, User> users = new HashMap<String, User>();
        users.put(str_user_name, new User(str_ful_name));

        user_add_ref.setValue(users);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
