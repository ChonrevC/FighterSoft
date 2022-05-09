package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BattleEndScreen extends AppCompatActivity {
    private TextView winner;
    private TextView recIncrease;
    private Button mm;
    private Button pa;
    private String player;
    private String p1;
    private String p2;
    public static String p1userN;
    public static String p2userN;
    public static int pskin1;
    public static int pskin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_end_screen);
        Intent intent = getIntent();
        player = intent.getStringExtra(BattleScreen.Player);
        Log.d("userplayer", ""+player);
        p2=BattleScreen.getP2();
        p2userN=BattleScreen.getP2();
        p1=BattleScreen.getP1();
        p1userN=BattleScreen.getP1();

        winner = findViewById(R.id.windDeclaration);
        MainActivity.increaseGames();
        if(player.equals("player1")) {
            winner.setText(p1 + " Wins!");
            MainActivity.increaseP1Wins();
            recIncrease = findViewById(R.id.scoreinc);
            recIncrease.setText("+1 to " + p1 + "'s record!\nIt is now "+MainActivity.getP1wins()+"/"+MainActivity.getP1Games());
            recIncrease.setY(recIncrease.getY() - 50);
            pskin1=BattleScreen.getP1S();
            if(pskin1==0){
                findViewById(R.id.player1WinPortrait).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.player1WinRPortrait).setVisibility(View.VISIBLE);
            }
        } else if(player.equals("player2")){
            Log.d("userplayer2", ""+BattleScreen.getP2());
            winner.setText(p2 + " Wins!");
            MainActivity.increaseP2Wins();
            recIncrease = findViewById(R.id.scoreinc);
            recIncrease.setText("+1 to " + p2 + "'s record!\nIt is now "+MainActivity.getP2Wins()+"/"+MainActivity.getP2Games());
            recIncrease.setY(recIncrease.getY() - 50);
            pskin2=BattleScreen.getP2S();
            if(pskin2==0){
                findViewById(R.id.player2WinPortrait).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.player2WinBPortrait).setVisibility(View.VISIBLE);
            }
        } else if(player.equals("neither player")){
            winner.setText("Neither Player Wins!");
            recIncrease = findViewById(R.id.scoreinc);
            recIncrease.setText("+1 to No One's record!");
            recIncrease.setY(recIncrease.getY() - 50);
        }

        mm = findViewById(R.id.MM);
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BattleEndScreen.this, MainActivity.class)  ;      // Specify any activity here e.g. home or splash or login etc
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("EXIT", true);
                startActivity(i);
                finish();
            }
        });
        pa= findViewById(R.id.PA);
        pa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BattleEndScreen.this, BattleScreen.class);
                finish();
                intent.putExtra(p2userN, p2userN);
                intent.putExtra(p1userN, p1userN);
                startActivity(intent);
            }
        });
    }

}