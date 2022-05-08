package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import android.widget.TextView;
import java.io.*;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private Button buttonSet;
    private Button buttonLog;
    private Button buttonRegister;
    private Button buttonGame;
    private Button exitButton;

    private MediaPlayer mediaPlayer;

    private String p1userN;
    private int p1wins;
    private int p1Games;
    private String p2userN;
    private int p2wins;
    private int p2Games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.qfts);
        Thread t = new Thread(){
            public void run(){
                playBMG();
            }
        };
        t.start();

        exitButton= (Button) findViewById(R.id.exitB);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopBGM();
                System.exit(0);
            }
        });
        buttonSet = (Button) findViewById(R.id.settingsB);
        buttonSet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openSettings();
            }
        });

        buttonLog = (Button) findViewById(R.id.loginP1);
        buttonLog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View n)
            {
                openLog();

            }
        });

        buttonLog = (Button) findViewById(R.id.loginP2);
        buttonLog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View n)
            {
                openLog();

            }
        });

        buttonRegister = (Button) findViewById(R.id.signUp);
        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View r)
            {
                openRegister();

            }
        });
        buttonGame = (Button) findViewById(R.id.startButton);
        buttonGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openGame();
            }
        });
    }
    public void openSettings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openLog(){
        Intent intention = new Intent(this, Login.class);
        startActivity(intention);
    }
    public void openRegister(){
        Intent impartial = new Intent(this, SignUp.class);
        startActivity(impartial);
    }

    public void openGame(){
        mediaPlayer.stop();
        Intent moveToFight = new Intent(this, BattleScreen.class);
        finish();
        startActivity(moveToFight);
        Log.d("game", "Gamer Time");
    }
    public void playBMG(){
        mediaPlayer.start();
    }
    public void stopBGM(){mediaPlayer.stop();mediaPlayer.release();}
}


