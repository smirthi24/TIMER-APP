package com.myapp.smirthi.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextview;
    SeekBar timerSeekBar;
    Boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        timerTextview.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("go");
        counterIsActive=false;


    }
    public void buttonClicked(View view){
      if (counterIsActive){
          resetTimer();
      }else {
          counterIsActive = true;
          timerSeekBar.setEnabled(false);
          goButton.setText("stop");


          countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {


              @Override
              public void onTick(long l) {
                  updateTimer((int) l / 1000);

              }

              @Override
              public void onFinish() {

                  MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.servicebell);
                  mplayer.start();
                  resetTimer();

              }
          }.start();
      }


    }

    public void updateTimer( int secondLeft){
        int minutes=secondLeft/60;
        int seconds = secondLeft - (minutes*60);

        String secondString=Integer.toString(seconds);

        if(seconds<=9){
            secondString ="0" + secondString;
        }
        timerTextview.setText(Integer.toString(minutes) + ":" +secondString  );


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar=findViewById(R.id.timerSeekBar);
        timerTextview=findViewById(R.id.countdownTextView);
         goButton=findViewById(R.id.goButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
