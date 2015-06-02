package com.example.rushitaa.photo_app;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HelpActivity extends ActionBarActivity {
  String data;
    TextView help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        /**
         * The activity can be updated by the admin to help users understand the application flow properly.
         */
        data = "Dear User"+"\n\n"+"Click on My Contacts to check list of interested users."+"\n\n"+"Kindly  Register with Like app with a valid username ana emailId. And find the person of your interest and contact him through email."+"\n\n"+"Thank you"+"\n"+"Admin";
        help = (TextView)findViewById(R.id.help);
        help.setText(data);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
