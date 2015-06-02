package com.example.rushitaa.photo_app;
/**
 * Used custom adapter to implement the list view. It is done successfully using ViewHolder.
 */
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class Mylikes extends ActionBarActivity implements MyAction.MyQuestionListener {

    private List<Hobbies> hobby = new ArrayList<>();
    private ListView listView;
    CustomAdapter dataAdapter = null;
    Cursor cursor;
    StringBuffer responseText = new StringBuffer();
    String pos;
    String loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pos = extras.getString("username_id");
            loc = extras.getString("location_id");
        }
        displayView();
        checkButtonClick();
        submitButtonClick();
    }
    private void displayView(){
        listView = (ListView) findViewById(R.id.list);
        hobby.add(new Hobbies("Foot Ball",false));
        hobby.add(new Hobbies("Basket Ball",false));
        hobby.add(new Hobbies("Badminton",false));
        hobby.add(new Hobbies("Flute",false));
        hobby.add(new Hobbies("Guitar",false));
        hobby.add(new Hobbies("Piano",false));
        hobby.add(new Hobbies("Contemporary",false));
        hobby.add(new Hobbies("Fusion",false));
        hobby.add(new Hobbies("Salsa",false));
        hobby.add(new Hobbies("Italian",false));
        hobby.add(new Hobbies("Indian",false));
        hobby.add(new Hobbies("Thai",false));

        dataAdapter = new CustomAdapter(this, R.layout.activity_mylikes, hobby);

        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                Hobbies hobby = (Hobbies) parent.getItemAtPosition(position);
            }
        });

    }
    private void submitButtonClick() {


        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                MyAction fragment = new MyAction();
                fragment.show(getSupportFragmentManager(),"Question");


            }
        });

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        //Toast.makeText(getApplicationContext(), "action taken", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (Mylikes.this, ViewListActivity.class);
        intent.putExtra("location_id", loc);
        finish();
        startActivity(intent);
    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "No action taken", Toast.LENGTH_LONG).show();

    }



    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //responseText.append("My Selected Likes are...\n");


                List<Hobbies> hobby = dataAdapter.hobby;
                for (int i = 0; i < hobby.size(); i++) {
                    Hobbies hob = hobby.get(i);
                    if (hob.isSelected()) {
                        responseText.append(hob.getName()+"-");
                    }
                }
                responseText.deleteCharAt(responseText.length()-1);
                Toast.makeText(getApplicationContext(),
                       responseText, Toast.LENGTH_LONG).show();
                   save();

            }
        });

    }
    public void save() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LikeDb");
        query.whereEqualTo("username", pos);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject likeObject, ParseException e) {
                if (e == null) {
                    likeObject.put("likes", responseText.toString());
                    likeObject.saveInBackground();

                } else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
