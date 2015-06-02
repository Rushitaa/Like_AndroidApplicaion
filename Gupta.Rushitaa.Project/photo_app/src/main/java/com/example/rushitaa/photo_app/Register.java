package com.example.rushitaa.photo_app;
import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class Register extends ActionBarActivity implements AdapterView.OnItemSelectedListener {


    EditText username;
    EditText email;
    TextView location;
    String name;
    String emailid;
   // EditText location;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("user_name");
            emailid = extras.getString("user_email");

        }
        username = (EditText) findViewById(R.id.usernameField);
        email = (EditText) findViewById(R.id.emailField);
        location = (TextView) findViewById(R.id.locationField);



        savetoUser2();
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateEmail();
            }
        });

    }
    public void validateEmail()
    {
        String email_str = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email_str.matches(emailPattern) && email_str.length() > 0) {
            String id = save();
            Intent intent = new Intent(Register.this, GeofencingActivity.class);

//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("username_id", id);
            intent.putExtra("user_name",name);
            finish();
            startActivity(intent);


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage(R.string.register_error_message)
                    .setTitle(R.string.register_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    public String save()
    {

    String username_str = username.getText().toString();
    String email_str = email.getText().toString().trim();
    ParseObject likeObject = new ParseObject("LikeDb");

    likeObject.put("username", username_str);
    likeObject.put("email", email_str);
    likeObject.saveInBackground();
    Log.d("TAG", "Data saved");

    return username_str;
    }

    public void savetoUser2()
    {

        ParseObject signObject = new ParseObject("User2");

        signObject.put("Username", name);
        signObject.put("Email", emailid);
        signObject.saveInBackground();
        Log.d("TAG", "Data saved");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }


        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //parent.getItemAtPosition(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
