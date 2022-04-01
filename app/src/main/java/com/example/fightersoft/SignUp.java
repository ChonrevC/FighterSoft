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

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize all components on activity
        this.textInputEditTextUsername = findViewById(R.id.username);
        this.textInputEditTextPassword = findViewById(R.id.password);
        this.textInputEditTextEmail = findViewById(R.id.email);
        this.buttonSignUp = findViewById(R.id.signupButton);
        this.textViewLogin = findViewById(R.id.loginText);
        this.progressBar = findViewById(R.id.progress);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get the values of the components in terms of Strings
                String username, password, fullname, email;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();
                email = textInputEditTextEmail.getText().toString();
                fullname = username;

                // if all fields were inputted in properly, put in the info into the database
                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals(""))
                {

                    progressBar.setVisibility(View.VISIBLE);                        // show loading
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;

                            // put the data in the database via the signup.php link location (replaced local host with IP address)
                            PutData putData = new PutData("http://192.168.1.103/LoginRegister/signup.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    // when completed, take away the progress bar and store the result in a string to check success
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    // Sign Up Success comes from the signup.php link, if the data transfer was successful
                                    if(result.equals("Sign Up Success"))
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
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }

            }

        });

        // if user selects the login option
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}