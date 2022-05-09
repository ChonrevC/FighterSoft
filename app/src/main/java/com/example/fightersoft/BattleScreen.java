package com.example.fightersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;


public class BattleScreen extends AppCompatActivity implements KeyEvent.Callback {
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeleftMS = 90000;//keep at 90000
    private boolean isRunning;

    private ImageView threeView;
    private ImageView twoView;
    private ImageView oneView;
    private ImageView TussleView;

    private ImageView hoBarView;
    private ImageView waBarView;

    private GifImageView hoopoeIdleView;
    private GifImageView hoopoeAttackView;

    private GifImageView waloopoeIdleView;
    private GifImageView waloopoeAttackView;

    private TextView player1View;
    private TextView player2View;

    private static String p1Name;
    private static String p2Name;

    private int attackHeight;

    private boolean isPlaying = false;
    private boolean hMovable=true;
    private boolean wMovable=true;
    private boolean hattackable=true;
    private boolean wattackable=true;
    private boolean hoStretch=false;
    private boolean waStretch=false;

    private int hoHealth=100;
    private int waHealth=100;
    private static int skinP1;
    private static int skinP2;

    public static final String Player = "";
    public static String getP1(){
        return p1Name;
    }
    public static String getP2(){
        return p2Name;
    }
    public static int getP1S(){ return skinP1;}
    public static int getP2S(){ return skinP2;}

    public void reducehealth(int player, int damage){
        if(player ==0){
            hoHealth-=damage;
            if(hoHealth<0){
                hoHealth = 0;
            }
            hoBarView.setScaleX((float) (hoBarView.getScaleX()-0.08));
        }else if(player==1){
            waHealth-=damage;
            if(waHealth<0){
                waHealth=0;
            }
            waBarView.setScaleX((float) (waBarView.getScaleX()-0.08));
        }
        if(waHealth == 0 && hoHealth == 0){
            isPlaying = false;
            hMovable=false;
            wMovable=false;
            hattackable=false;
            wattackable=false;
            hoStretch=false;
            waStretch=false;
            Intent intent = new Intent(BattleScreen.this, BattleEndScreen.class);
            intent.putExtra(Player, "neither player");
            startActivity(intent);
            stopTimer();
        }else if (waHealth>0 && hoHealth<=0){
            isPlaying = false;
            hMovable=false;
            wMovable=false;
            hattackable=false;
            wattackable=false;
            hoStretch=false;
            waStretch=false;
            Intent intent = new Intent(BattleScreen.this, BattleEndScreen.class);
            intent.putExtra(Player, "player2");
            startActivity(intent);
            stopTimer();
        }else if (waHealth<=0 && hoHealth>0){
            isPlaying = false;
            hMovable=false;
            wMovable=false;
            hattackable=false;
            wattackable=false;
            hoStretch=false;
            waStretch=false;
            Intent intent = new Intent(BattleScreen.this, BattleEndScreen.class);
            intent.putExtra(Player, "player1");
            startActivity(intent);
            stopTimer();
        }
    }


    public void checkBoarders(char c, int player){
        if(c == 'r' && player == 0){
            if((waloopoeIdleView.getX() - hoopoeIdleView.getX() > (hoopoeIdleView.getWidth()+15))){
                hoopoeIdleView.setX(hoopoeIdleView.getX()+60);
            }
        } else if(c == 'l' && player == 0){
            if(hoopoeIdleView.getX() > -160){
                hoopoeIdleView.setX(hoopoeIdleView.getX()-60);
            }
        } else if(c == 'r' && player == 1) {
            if(waloopoeIdleView.getX() < 1900){
                waloopoeIdleView.setX(waloopoeIdleView.getX()+60);
            }
        } else if(c == 'l' && player == 1) {
            if((waloopoeIdleView.getX() - hoopoeIdleView.getX() > (hoopoeIdleView.getWidth()+15))){
                waloopoeIdleView.setX(waloopoeIdleView.getX()-60);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isPlaying) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_D:
                    if(hMovable==true) {
                        checkBoarders('r', 0);
                    }
                    return true;
                case KeyEvent.KEYCODE_A:
                    if(hMovable==true) {
                        checkBoarders('l', 0);
                    }
                    return true;
                case KeyEvent.KEYCODE_L:
                    if(wMovable==true) {
                        checkBoarders('l', 1);
                    }
                    return true;
                case KeyEvent.KEYCODE_APOSTROPHE:
                    if(wMovable==true) {
                        checkBoarders('r', 1);
                    }
                    return true;
                case KeyEvent.KEYCODE_S:
                    if(hattackable==true) {
                        hattackable=false;
                        hoopoeIdleView.setVisibility(View.GONE);
                        if(skinP1 < 0.5) {
                            hoopoeAttackView.setImageResource(R.drawable.hoopoeca);
                        }else{
                            hoopoeAttackView.setImageResource(R.drawable.hrca);
                        }
                        hoopoeAttackView.setX(hoopoeIdleView.getX() + 79);
                        hoopoeAttackView.setY(attackHeight);
                        hoopoeAttackView.setVisibility(View.VISIBLE);
                        hMovable = false;
                        new CountDownTimer(300, 300){
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                hoStretch=true;
                            }
                        }.start();
                        new CountDownTimer(750,750){
                            public void onTick(long millisUntilFinished){}
                            public void onFinish(){
                                if(waloopoeAttackView.getVisibility()==View.GONE){
                                    int distance  = (int) (waloopoeIdleView.getX() - hoopoeIdleView.getX());
                                    if(distance <926){
                                        checkBoarders('r', 1);
                                        checkBoarders('l', 0);
                                        reducehealth(1, 20);
                                    }
                                }else{
                                    int distance  = (int) (waloopoeAttackView.getX() - hoopoeIdleView.getX());
                                    int boundry;
                                    if(waStretch == true){
                                        boundry = 727;
                                    }else{
                                        boundry = 600;
                                    }
                                    if(distance <boundry) {
                                        checkBoarders('r', 1);
                                        checkBoarders('l', 0);
                                        reducehealth(1, 20);
                                    }
                                }
                            }
                        }.start();
                        new CountDownTimer(900, 900) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                hoopoeAttackView.setVisibility(View.GONE);
                                hoopoeIdleView.setVisibility(View.VISIBLE);
                                new CountDownTimer(200, 200) {
                                    public void onTick(long millisUntilDone) {
                                    }

                                    @Override
                                    public void onFinish() {
                                        hMovable = true;
                                        hattackable=true;
                                        hoStretch=false;
                                    }
                                }.start();
                            }
                        }.start();
                    }
                    return true;
                case KeyEvent.KEYCODE_SEMICOLON:
                    if(wattackable==true) {
                        wattackable=false;
                        waloopoeIdleView.setVisibility(View.GONE);
                        if(skinP2 <0.5) {
                            waloopoeAttackView.setImageResource(R.drawable.waloopoeca);
                        }else{
                            waloopoeAttackView.setImageResource(R.drawable.wbca);
                        }
                        waloopoeAttackView.setVisibility(View.VISIBLE);
                        waloopoeAttackView.setX(waloopoeIdleView.getX()-379);
                        waloopoeAttackView.setY(attackHeight);
                        wMovable = false;
                        new CountDownTimer(300, 300){
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                waStretch=true;
                            }
                        }.start();
                        new CountDownTimer(750,750){
                            public void onTick(long millisUntilFinished){}
                            public void onFinish(){
                                if(hoopoeAttackView.getVisibility()==View.GONE){
                                    int distance  = (int) (waloopoeAttackView.getX() - hoopoeIdleView.getX());
                                    if(distance <600){
                                        checkBoarders('r', 1);
                                        checkBoarders('l', 0);
                                        reducehealth(0, 20);
                                    }
                                }else{
                                    int distance  = (int) (waloopoeAttackView.getX() - hoopoeIdleView.getX());
                                    int boundry;
                                    if(hoStretch == true){
                                        boundry = 727;
                                    }else{
                                        boundry = 600;
                                    }
                                    if(distance <boundry) {
                                        checkBoarders('r', 1);
                                        checkBoarders('l', 0);
                                        reducehealth(0, 20);
                                    }
                                }
                            }
                        }.start();
                        new CountDownTimer(900, 900) {
                            public void onTick(long millisUntilFinished) {}
                            public void onFinish() {
                                waloopoeAttackView.setVisibility(View.GONE);
                                waloopoeIdleView.setVisibility(View.VISIBLE);
                                new CountDownTimer(200, 200) {
                                    public void onTick(long millisUntilDone) {
                                    }
                                    @Override
                                    public void onFinish() {
                                        wMovable = true;
                                        wattackable=true;
                                        waStretch=false;
                                    }
                                }.start();
                            }
                        }.start();
                    }
                    return true;
                default:
                    return super.onKeyDown(keyCode, event);
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeleftMS =90000;
        hoHealth=100;
        waHealth=100;
        setContentView(R.layout.activity_battle_screen);
        countdownText = findViewById(R.id.clock);
        Intent intent = getIntent();
        if(intent.getStringExtra(MainActivity.p1userN)!= null) {
            Log.d("names", ""+intent.getStringExtra(MainActivity.p1userN));
            p1Name = intent.getStringExtra(MainActivity.p1userN);
            p2Name = intent.getStringExtra(MainActivity.p2userN);
        }else {
            Log.d("names", ""+intent.getStringExtra(BattleEndScreen.p2userN));
            p1Name = intent.getStringExtra(BattleEndScreen.p1userN);
            p2Name = intent.getStringExtra(BattleEndScreen.p2userN);
        }
        startupAnimations();
        hoBarView=findViewById(R.id.hbrownbar);
        waBarView=findViewById(R.id.waloppurpBard);
        skinP1 = MainActivity.getP1Skin();
        skinP2 = MainActivity.getP2Skin();
        Log.d("SKIN", ""+skinP1);
        Log.d("SKIN", ""+skinP2);
        if(skinP1==0) {
            hoopoeIdleView = findViewById(R.id.hoopidl);
            hoopoeIdleView.setVisibility(View.VISIBLE);
            hoopoeAttackView = findViewById(R.id.hoopatk);
        }else{
            hoopoeIdleView = findViewById(R.id.redidle);
            hoopoeIdleView.setVisibility(View.VISIBLE);
            hoopoeAttackView = findViewById(R.id.hrca);
        }
        if(skinP2 ==0){
            waloopoeIdleView = findViewById(R.id.wlpidle);
            waloopoeIdleView.setVisibility(View.VISIBLE);
            waloopoeAttackView = findViewById(R.id.waloopatk);
        }else{
            waloopoeIdleView = findViewById(R.id.wblidl);
            waloopoeIdleView.setVisibility(View.VISIBLE);
            waloopoeAttackView = findViewById(R.id.wbca);
        }
        player1View=findViewById(R.id.player1);
        player1View.setText("Hoopoe - "+p1Name);
        player2View=findViewById(R.id.player2);
        player2View.setText("Waloopoe - "+p2Name);
        attackHeight = (int) (hoopoeIdleView.getY()-180);
        isPlaying = false;
        hMovable=true;
        wMovable=true;
        hattackable=true;
        wattackable=true;
        hoStretch=false;
        waStretch=false;
    }



    public void startStop(){
        if(isRunning){
            stopTimer();
        }else{
            startTimer();
            isPlaying=true;
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeleftMS, 1000) {
            @Override
            public void onTick(long l) {
                timeleftMS = l;
                updateTimer();
            }
            @Override
            public void onFinish() {
                if(waHealth ==  hoHealth){
                    Intent intent = new Intent(BattleScreen.this, BattleEndScreen.class);
                    intent.putExtra(Player, "neither player");
                    startActivity(intent);
                    stopTimer();
                }else if (waHealth>hoHealth){
                    Intent intent = new Intent(BattleScreen.this, BattleEndScreen.class);
                    intent.putExtra(Player, p1Name);
                    startActivity(intent);
                    stopTimer();
                }else if (waHealth<hoHealth){
                    Intent intent = new Intent(BattleScreen.this, BattleEndScreen.class);
                    intent.putExtra(Player, p2Name);
                    startActivity(intent);
                    stopTimer();
                }
            }
        }.start();
        isRunning=true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        isRunning=false;
    }

    public void updateTimer(){
        int seconds = (int) timeleftMS/1000;
        String timeLeftText;
        timeLeftText = ""+seconds;
        countdownText.setText(timeLeftText);
    }

    public void startupAnimations() {
        threeView = findViewById(R.id.three);
        twoView = findViewById(R.id.two);
        Animation fadeintwo= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.twofadein);
        Animation fadeouttwo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.twofadeout);
        oneView = findViewById(R.id.one);
        Animation fadeinone= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onefadein);
        Animation fadeoutone= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onefadeout);
        TussleView = findViewById(R.id.tussle);
        Animation tusslein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tusslein);
        Animation tussleout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tussleout);
        threeView.animate()
                .alpha(0)
                .setDuration(100)
                .setStartDelay(800)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        threeView.setVisibility(View.GONE);
                    }
                });
        AnimationSet twoSet = new AnimationSet(false);
        twoSet.addAnimation(fadeintwo);
        twoSet.addAnimation(fadeouttwo);
        twoView.setAnimation(twoSet);
        AnimationSet oneSet = new AnimationSet(false);
        oneSet.addAnimation(fadeinone);
        oneSet.addAnimation(fadeoutone);
        oneView.setAnimation(oneSet);
        AnimationSet tussleSet = new AnimationSet(false);
        tussleSet.addAnimation(tusslein);
        tussleout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                TussleView.setVisibility(View.GONE);
                startStop();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        tussleSet.addAnimation(tussleout);
        TussleView.setAnimation(tussleSet);
    }

}

