package com.example.covidquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DeniedScreen extends AppCompatActivity {

    TextView timer;
    Button startButton;
    CountDownTimer myCountDownTimer;
    MediaPlayer alarmSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denied_screen);


        startButton = (Button) findViewById(R.id.start_button);
        timer = (TextView) findViewById(R.id.timer_view);
        timer.setVisibility(View.INVISIBLE);
        alarmSound = MediaPlayer.create(this, R.raw.alarmsound);
        alarmSound.start();
        alarmSound.setVolume(1,1);

        myCountDownTimer = new CountDownTimer(7000,1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000) + "s");
                if(l/1000 == 2){
                    alarmSound.stop();
                };
            }
            @Override
            public void onFinish() {
                alarmSound.stop();
                startActivity(new Intent(DeniedScreen.this, MainActivity.class));
            }
        }.start();
        onBackPressed();
        startOver();
    }

    public void startOver(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setEnabled(false);
                alarmSound.stop();
                myCountDownTimer.cancel();
                startActivity(new Intent(DeniedScreen.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}