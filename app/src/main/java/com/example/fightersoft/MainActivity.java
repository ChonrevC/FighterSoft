package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button buttonSet;
    private Button buttonLog;
    private Button buttonRegister;
    private Button buttonGame;
    private Button exitButton;
    private TextView playerNum;

    public static String p1userN="";
    public static String p1password="";
    public static int p1wins = 0;
    public static int p1Games = 0;
    public static int p1Skin = 0;
    public static String p2userN="";
    public static String p2password="";
    public static int p2wins = 0;
    public static int p2Games = 0;
    public static int p2Skin = 0;
    public static String send;

    public static void resetP1G(){p1wins=0;p1Games=0;}
    public static void resetP2G(){p2wins=0;p2Games=0;}
    public static void increaseGames(){p2Games+=1;p1Games+=1;}
    public static void increaseP1Wins() {p1wins+=1;}
    public static void increaseP2Wins(){p2wins+=1;}
    public static int getP1wins(){return p1wins;}
    public static int getP2Wins(){return p2wins;}
    public static int getP1Games(){return p1Games;}
    public static int getP2Games(){return p2Games;}
    public static void setP1Skin(int i){p1Skin=i;}
    public static void setP2Skin(int i){p2Skin=i;}
    public static int getP1Skin(){return p1Skin;}
    public static int getP2Skin(){return p2Skin;}
    public static void setPlayer1(String p1u, String p1p){p1userN=p1u;p1password=p1p;p1wins=0;p1Games=0;}
    public static void setPlayer2(String p2u, String p2p){p2userN=p2u;p2password=p2p;p2wins=0;p2Games=0;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerNum = findViewById(R.id.noeEnoughPlayers);
        exitButton= findViewById(R.id.exitB);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        buttonSet = findViewById(R.id.settingsB);
        buttonSet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                openSettings();
            }
        });

        buttonLog = findViewById(R.id.loginP1);
        buttonLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View n){
                openLog1();

            }
        });

        buttonLog = findViewById(R.id.loginP2);
        buttonLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View n){
                openLog2();
            }
        });

        buttonRegister = findViewById(R.id.signUp);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View r){
                openRegister();
            }
        });
        buttonGame = findViewById(R.id.startButton);
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
    public void openLog1(){
        Intent intention = new Intent(this, Login.class);
        intention.putExtra(send, "one");
        startActivity(intention);
    }
    public void openLog2(){
        Intent intention = new Intent(this, Login.class);
        intention.putExtra(send, "two");
        startActivity(intention);
    }
    public void openRegister(){
        Intent impartial = new Intent(this, SignUp.class);
        startActivity(impartial);
    }

    public void openGame(){
        if(p1userN.length() != 0 && p2userN.length() != 0 ) {
            Intent moveToFight = new Intent(this, BattleScreen.class);
            moveToFight.putExtra(p1userN, p1userN);
            moveToFight.putExtra(p2userN, p2userN);
            finish();
            startActivity(moveToFight);
        }else if(p1userN.length()==0 || p2userN.length()==0){
            playerNum.setVisibility(View.VISIBLE);
            new CountDownTimer(1000, 1000){
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    playerNum.setVisibility(View.GONE);
                }
            }.start();
        }
    }
}


