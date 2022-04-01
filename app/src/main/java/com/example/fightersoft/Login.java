package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fightersoft.MainActivity;
import com.example.fightersoft.SignUp;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.textInputEditTextUsername = findViewById(R.id.username);
        this.textInputEditTextPassword = findViewById(R.id.password);
        this.buttonLogin = findViewById(R.id.loginButton);
        this.textViewSignUp = findViewById(R.id.signupText);
        this.progressBar = findViewById(R.id.progress);



        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get the values of the components in terms of Strings
                String username, password;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                // if all fields were inputted in properly, put in the info into the database
                if(!username.equals("") && !password.equals(""))
                {

                    progressBar.setVisibility(View.VISIBLE);                        // show loading
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
                            PutData putData = new PutData("http://192.168.1.103/FighterSoft/login.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    // when completed, take away the progress bar and store the result in a string to check success
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    // Sign Up Success comes from the signup.php link, if the data transfer was successful
                                    if(result.equals("Login Success"))
                                    {
                                        // if successful, open up the login activity
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }
}