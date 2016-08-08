package com.alleviate.fbrtdb;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText notes;
    TextView logs;
    String contributor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("FBRTDB");

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        notes = (EditText)findViewById(R.id.note);
        logs = (TextView)findViewById(R.id.logs);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        contributor = preferences.getString("Contributor","");

        if (contributor.equals("")) {

            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
            adb.setTitle("Set Your Name");
            LayoutInflater inflater = getLayoutInflater();
            View dialogue_view = inflater.inflate(R.layout.dialogue, null);
            final EditText contributor_name = (EditText)dialogue_view.findViewById(R.id.user);
            adb.setView(dialogue_view);
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    contributor = contributor_name.getText().toString();
                    SharedPreferences.Editor preferences_editor = preferences.edit();
                    preferences_editor.putString("Contributor",contributor);
                    preferences_editor.apply();
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            AlertDialog alertDialog = adb.create();
            alertDialog.show();

        }



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
        if (id == R.id.action_save) {

            String title = "Test";

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat sdf_date = new SimpleDateFormat("dd MMM");

            String log_str = "Edited by "+contributor+" at "+sdf_time.format(cal.getTime())+" on "+sdf_date.format(cal.getTime());

            String notes_str = notes.getText().toString();
            logs.setText(notes_str+" :: "+log_str);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            Notes notes = new Notes(title, notes_str, contributor, log_str);

            databaseReference.child("file").child("title").setValue(title);
            databaseReference.child("file").child("data").setValue(notes_str);
            databaseReference.child("file").child("contributors").setValue(contributor);
            databaseReference.child("file").child("logs").setValue(log_str);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
