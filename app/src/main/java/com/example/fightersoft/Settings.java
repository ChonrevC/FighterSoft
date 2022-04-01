package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Settings extends AppCompatActivity {

    // declare the components in the activity
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    TextView textViewSettings;
    Button updateButton, colorButton, deleteButton;
    int colorCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // get the username & password from the main screen to allow editing of DB
        Intent fromIntent = getIntent();
        String currUsername = fromIntent.getStringExtra("USERNAME");
        String currPassword = fromIntent.getStringExtra("PASSWORD");


        // function that plays when deleteButton is clicked
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // if all fields were inputted in properly, put in the info into the database
                if(!currUsername.equals("") && !currPassword.equals(""))
                {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = currUsername;
                            data[1] = currPassword;

                            // put the data in the database via the deleteRow.php link location (replace local host with IP address)
                            PutData putData = new PutData("http://192.168.1.103/LoginRegister/deleteRow.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    // Sign Up Success comes from the signup.php link, if the data transfer was successful
                                    if(result.equals("Account Deleted"))
                                    {
                                        // if successful, open up the login activity
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                }

                // if the fields are not inputted in properly, throw up a toast error
                else
                {
                    Toast.makeText(getApplicationContext(), "Could not delete account", Toast.LENGTH_SHORT).show();
                }

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get the values of the components in terms of Strings
                String username, password;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                // if all fields were inputted in properly, put in the info into the database
                if(!username.equals("") && !password.equals(""))
                {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            // put the data in the database via the signup.php link location (replace local host with IP address)
                            PutData putData = new PutData("http://192.168.1.103/LoginRegister/updateRow.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    // Sign Up Success comes from the signup.php link, if the data transfer was successful
                                    if(result.equals("Account Updated"))
                                    {
                                        // if successful, open up the login activity
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("USERNAME", username);          // pass the username of the person who logged in
                                        intent.putExtra("PASSWORD", password);          // pass the password of the person who logged in
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                }

                // if the fields are not inputted in properly, throw up a toast error
                else
                {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });

        colorButton.setOnClickListener(new View.OnClickListener() {
            private Activity onClick;

            @Override
            public void onClick(View view) {

                if(colorCount == 3) colorCount = 0;

                colorCount++;

                if(colorCount == 1) Utils.changeTheme(this.onClick, Utils.THEME_DEFAULT);
                if(colorCount == 2) Utils.changeTheme(this.onClick, Utils.THEME_WHITE);
                if(colorCount == 3) Utils.changeTheme(this.onClick, Utils.THEME_BLUE);

            }
        });

    }
}