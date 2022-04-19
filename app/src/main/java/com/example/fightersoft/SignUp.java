package com.example.fightersoft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    // create variables for components in the sign up activity
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    // create a variable to allow for DB usage
    private FirebaseAuth mAuth;

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

        // initialize this instance of the database
        mAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get the values of the components in terms of Strings
                String username, password, email;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();
                email = textInputEditTextEmail.getText().toString();

                // if user left the username space empty
                if(username.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Username is required", Toast.LENGTH_SHORT).show();
                }

                // if the password space is less than 6 characters (firebase doesn't accept passwords under 6 characters)
                else if(password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
                }

                // if user left email space empty
                else if(email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
                }

                // if user's email isn't compatible with app
                else if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(getApplicationContext(), "Please provide a valid email", Toast.LENGTH_SHORT).show();
                }

                // if the password space is less than 6 characters (firebase doesn't accept passwords under 6 characters)
                else if(password.length() < 6)
                {
                    Toast.makeText(getApplicationContext(), "Password must be longer than 6 characters", Toast.LENGTH_SHORT).show();
                }

                // if all fields were inputted in properly, put in the info into the database
                else
                {

                    progressBar.setVisibility(View.VISIBLE);                        // show loading

                    /* WHERE INPUT INTO DATABASE IS DONE */
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            // check if the creation was successful
                            if(task.isSuccessful())
                            {
                                // create a new user with info to send to database
                                User user = new User(username, password, email);

                                // create a firebase object and store the user in it
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_LONG).show();

                                            progressBar.setVisibility(View.GONE);
                                        } else
                                        {
                                            Toast.makeText(getApplicationContext(), "Failed to Register. Try again.", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                })
                            } else
                            {
                                Toast.makeText(getApplicationContext(), "Failed to Register. Try again.", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    /*
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
                     */

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