package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fightersoft.MainActivity;
import com.example.fightersoft.SignUp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    // create a variable to allow for DB usage
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextEmail = findViewById(R.id.email);
        this.editTextPassword = findViewById(R.id.password);
        this.buttonLogin = findViewById(R.id.loginButton);
        this.textViewSignUp = findViewById(R.id.signupText);
        this.progressBar = findViewById(R.id.progress);



        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get the values of the components in terms of Strings
                String username, password;
                username = editTextUsername.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                // if user left the username space empty
                if(email.isEmpty())
                {
                    editTextUsername.setError("Username is required");
                    editTextUsername.requestFocus();
                    return;
                }

                // if the password space is less than 6 characters (firebase doesn't accept passwords under 6 characters)
                if(password.isEmpty())
                {
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                    return;
                }

                // if all fields were inputted in properly, put in the info into the database
                progressBar.setVisibility(View.VISIBLE);                        // show loading

                mAuth.signInWith

                    /*
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
                            PutData putData = new PutData("http://192.168.1.103/LoginRegister/login.php", "POST", field, data);

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
                     */

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