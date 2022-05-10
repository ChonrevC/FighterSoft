package com.example.fightersoft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import pl.droidsonroids.gif.GifImageView;

public class Settings extends AppCompatActivity {

    // declare the components in the activity
    EditText editTextEmail, editTextPassword;
    TextView textViewSettings;
    TextView settings;
    Button updateButton, p1Skin, p2Skin, deleteButton, updateSKNS;
    int hoops = 0, woops = 0;
    int colorCount = 0;

    private GifImageView hpidl;
    private GifImageView wpidl;
    private GifImageView rdidl;
    private GifImageView blidl;
    // create a variable to allow for DB usage
    private FirebaseAuth mAuth;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // function that plays when deleteButton is clicked
        deleteButton=findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
        /*
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                resetPassword();

            }
        });*/

        p1Skin = findViewById(R.id.p1Change);
        p1Skin.setOnClickListener(new View.OnClickListener() {
            private Activity onClick;

            @Override
            public void onClick(View view) {

                if(hoops == 0){
                    hoops=1;
                    hpidl=findViewById(R.id.hoopidl);
                    hpidl.setVisibility(View.INVISIBLE);
                    rdidl=findViewById(R.id.rdidle);
                    rdidl.setVisibility(View.VISIBLE);
                    MainActivity.setP1Skin(hoops);
                }else{
                    hoops=0;
                    hpidl=findViewById(R.id.hoopidl);
                    hpidl.setVisibility(View.VISIBLE);
                    rdidl=findViewById(R.id.rdidle);
                    rdidl.setVisibility(View.INVISIBLE);
                    MainActivity.setP1Skin(hoops);
                }

            }
        });
        p2Skin = findViewById(R.id.p2Change);
        p2Skin.setOnClickListener(new View.OnClickListener() {
            private Activity onClick;
            @Override
            public void onClick(View view) {

                if(woops == 0){
                    woops=1;
                    wpidl=findViewById(R.id.wlpidle);
                    wpidl.setVisibility(View.INVISIBLE);
                    blidl=findViewById(R.id.wblidl);
                    blidl.setVisibility(View.VISIBLE);
                    MainActivity.setP2Skin(woops);
                }else{
                    woops=0;
                    wpidl=findViewById(R.id.wlpidle);
                    wpidl.setVisibility(View.VISIBLE);
                    blidl=findViewById(R.id.wblidl);
                    blidl.setVisibility(View.INVISIBLE);
                    MainActivity.setP2Skin(woops);
                }

            }
        });

        updateSKNS = findViewById(R.id.changeSK);
        updateSKNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeSkins(hoops, woops);
            }
        });

    }

    public void storeSkins(int h, int w){
        Log.d("Skins", ""+h+" "+w);
    }
    // Function for deleting a user
    private void deleteUser() {

        String email, password;
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Enter email to delete account");
            //editTextEmail.requestFocus();
            return;
        }

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /*else if(email != user.getEmail())
        {
            editTextEmail.setError("Incorrect email");
            //editTextEmail.requestFocus();
            return;
        }*/

        else if(password.isEmpty())
        {
            editTextPassword.setError("Enter correct password");
           // editTextPassword.requestFocus();
            return;
        }

        else
        {
            if(email.equals(MainActivity.getPlayer1UN()) && password.equals(MainActivity.getPlayer1PW())){
                MainActivity.setPlayer1("", "");
            }else if(email.equals(MainActivity.getPlayer2UN()) && password.equals(MainActivity.getPlayer2PW())){
                MainActivity.setPlayer2("", "");
            }
            /*AuthCredential credential = EmailAuthProvider.getCredential(email, password);

            if(user != null)
            {
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    Toast.makeText(getApplicationContext(), "Deleted User Successfully", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Incorrect email or password", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                });
            }*/
        }

    }

    // Function for resetting a user's password
    private void resetPassword() {

        // get the values of the components in terms of Strings
        String email;
        email = editTextEmail.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Email required to change password");
            editTextEmail.requestFocus();
            return;
        }
        /*

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Check email to reset password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_LONG).show();
                }

            }
        });*/

    }

}