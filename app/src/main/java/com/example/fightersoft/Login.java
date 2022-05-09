package com.example.fightersoft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fightersoft.MainActivity;
import com.example.fightersoft.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;
    private String get;

    // create a variable to allow for DB usage
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        get = intent.getStringExtra(MainActivity.send);
        Log.d("user", get);

        this.editTextEmail = findViewById(R.id.email);
        this.editTextPassword = findViewById(R.id.password);
        this.buttonLogin = findViewById(R.id.loginButton);
        this.textViewSignUp = findViewById(R.id.signupText);
        this.progressBar = findViewById(R.id.progress);

        mAuth = FirebaseAuth.getInstance();



        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                loginUser();

            }

        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveToSignup();

            }
        });

    }


    // function for logging in a user
    private void loginUser() {

        // get the values of the components in terms of Strings
        String email, password;
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        // if user left the username space empty
        if(email.isEmpty())
        {
            editTextEmail.setError("Username is required");
            editTextEmail.requestFocus();
            return;
        }

        // if the password space is less than 6 characters (firebase doesn't accept passwords under 6 characters)
        else if(password.isEmpty())
        {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        else{
            if(get.equals("one")){
                MainActivity.setPlayer1(email, password);
                MainActivity.resetP1G();
                finish();
            } else if(get.equals("two")){
                MainActivity.setPlayer2(email, password);
                MainActivity.resetP2G();
                finish();
            }
        }

    }

    // function for moving to the signup activity
    private void moveToSignup() {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
        finish();
    }
}