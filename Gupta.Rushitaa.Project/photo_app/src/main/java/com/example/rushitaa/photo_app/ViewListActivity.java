package com.example.rushitaa.photo_app;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ViewListActivity extends ListActivity {
    private List<User> posts;
    String loc;
    String loc_str;
    String current_userid;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //There is a brief pause while the data loads. We will give the user an indication of this by including a loading spinner.
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_view_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            loc = extras.getString("location_id");
        }
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            loadLoginView();
        }
        current_userid = currentUser.getObjectId();

       // getcurrentLocation(current_username);
        posts = new ArrayList<User> ();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, R.layout.list_item_layout, posts);
        setListAdapter(adapter);

        refreshPostList();
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
       // getcurrentLocation(current_userid);
        User note = posts.get(position);

        Intent intent = new Intent(ViewListActivity.this, ViewActivity.class);
        intent.putExtra("username", note.getUsername());
        intent.putExtra("email", note.getEmail());
        loc_str = note.getLocation();
        intent.putExtra("location", note.getLocation());
        intent.putExtra("likes",note.getLikes());
        intent.putExtra("location_id",loc);
        startActivity(intent);

    }

    private void loadLoginView()
    {
        Intent intent = new Intent(ViewListActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void refreshPostList()
    {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("LikeDb");
        //query.whereEqualTo("author", ParseUser.getCurrentUser());
        setProgressBarIndeterminateVisibility(true);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                //This will display a progress spinner control. After the data loads, we need to hide it.
                setProgressBarIndeterminateVisibility(false);
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    posts.clear();
                    for (ParseObject post : postList) {
                        User note = new User(post.getObjectId(), post.getString("username"), post.getString("email"),post.getString("location"),post.getString("likes"));
                        posts.add(note);
                    }
                    ((ArrayAdapter<User>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

//    @Override
//    protected void onResume() {
//
//        super.onResume();
//        ViewList();
//
//    }
//public void calculate_distance(String loc1){
//
//    double distance;
//
//    Location locationA = new Location(loc1);
//
//    locationA.setLatitude(locationA.getLatitude());
//
//    locationA.setLongitude(locationA.getLongitude());
//
//    Location locationB = new Location(loc_str);
//
//    locationB.setLatitude(locationB.getLatitude());
//
//    locationB.setLongitude(locationB.getLongitude());
//
//    distance = locationA.distanceTo(locationB);
//
//    String aString = Double.toString(distance);
//    Toast.makeText(getApplicationContext(), aString , Toast.LENGTH_LONG).show();
//
//
//}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



            if(id==R.id.action_refresh)
            {
                refreshPostList();
                return true;
            }

            if(id==R.id.action_logout) {
                ParseUser.logOut();
                loadLoginView();
                return true;

            }

        return super.onOptionsItemSelected(item);
    }
}
