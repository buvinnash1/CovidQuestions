package com.example.covidquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class PasswordProtected extends AppCompatActivity {

    PasscodeView passcodeview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_protected);

        passcodeview = (PasscodeView) findViewById(R.id.passcodeView);
        passcodeview.setPasscodeLength(6).setLocalPasscode("123456").setListener(new PasscodeView.PasscodeViewListener() {
            @Override
            public void onFail() {
                Toast.makeText(PasswordProtected.this, "Password is Incorrect", Toast.LENGTH_LONG);
            }

            @Override
            public void onSuccess(String number) {
                startActivity(new Intent(PasswordProtected.this, SettingsScreen.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PasswordProtected.this, MainActivity.class));
    }
}