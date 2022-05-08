package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BattleEndScreen extends AppCompatActivity {
    private TextView winner;
    private TextView recIncrease;
    private Button mm;
    private Button pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_end_screen);
        Intent intent = getIntent();
        String text = intent.getStringExtra(BattleScreen.Player);


        winner = findViewById(R.id.windDeclaration);
        winner.setText(text+" Wins!");
        recIncrease = findViewById(R.id.scoreinc);
        recIncrease.setText("+1 to "+text+"'s record!");
        recIncrease.setY(recIncrease.getY()-50);
        mm = (Button) findViewById(R.id.MM);
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenuIntent = new Intent(BattleEndScreen.this, MainActivity.class);
                finish();
                startActivity(mainMenuIntent);
            }
        });
        pa=(Button) findViewById(R.id.PA);
        pa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BattleEndScreen.this, BattleScreen.class);
                finish();
                startActivity(intent);
            }
        });
    }

}