package com.example.rushitaa.photo_app;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.List;


public class ViewActivity extends ActionBarActivity {

    TextView user;
    TextView email;
    TextView user_location;
    TextView likes;
    String id;
    String email_str;
    String loc_str;
    private User user_obj;
    String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view);
        Intent intent = this.getIntent();

        user  = (TextView) findViewById(R.id.usernameTextView);
        email = (TextView) findViewById(R.id.emailidTextView);
        user_location = (TextView) findViewById(R.id.locationTextView);
        likes = (TextView) findViewById(R.id.likesTextView);

       if (intent.getExtras() != null)
       {

           user_obj = new User(intent.getStringExtra("username"), intent.getStringExtra("email"), intent.getStringExtra("location"),intent.getStringExtra("likes"));
           user.setText(user_obj.getUsername());
           email.setText(user_obj.getEmail());
           user_location.setText(user_obj.getLocation());
           likes.setText(user_obj.getLikes());

          }


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            loc = extras.getString("location_id");

        }


        email_str=user_obj.getEmail();
        loc_str=user_obj.getLocation();

        user_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=(" + loc_str + ")"));

                startActivity(intent);


            }
        });


        find_direction();
       // Email send implementation
        final Button send_mail = (Button)findViewById(R.id.email_button);
        send_mail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String emailSubject = "Likes Match";
                String emailText = "We have common hobbies "
                        + "Can we meet sometime?";
                String[] emailReceiverList = {email_str};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("vnd.android.cursor.dir/email");
                intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailText);
                startActivity(intent);



            }
        }
       );


    }
    private void loadLoginView()
    {
        Intent intent = new Intent(ViewActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void find_direction(){

        double distance;

        Location locationA = new Location(loc);

        locationA.setLatitude(locationA.getLatitude());


        locationA.setLongitude(locationA.getLongitude());

        Location locationB = new Location(loc_str);

        locationB.setLatitude(locationB.getLatitude());

        locationB.setLongitude(locationB.getLongitude());

        distance = locationA.distanceTo(locationB);

        String aString = Double.toString(distance);
       // Toast.makeText(getApplicationContext(), aString , Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_help) {
            Intent HelpIntent = new Intent(ViewActivity.this, HelpActivity.class);
            startActivity(HelpIntent);
            return true;
        }

        if(id== R.id.action_logout) {
            ParseUser.logOut();
            loadLoginView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
