package com.example.covidquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AcceptedScreen extends AppCompatActivity {

    Button startButton;
    TextView timer;
    CountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_screen);

        startButton = (Button) findViewById(R.id.start_button);
        timer = (TextView) findViewById(R.id.timer_view);
        timer.setVisibility(View.INVISIBLE);
        myCountDownTimer = new CountDownTimer(7000,1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(AcceptedScreen.this, MainActivity.class));
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
                myCountDownTimer.cancel();
                startActivity(new Intent(AcceptedScreen.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}