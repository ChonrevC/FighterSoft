package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Settings extends AppCompatActivity {

    // declare the components in the activity
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    TextView textViewSettings;
    Button updateButton, colorButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}