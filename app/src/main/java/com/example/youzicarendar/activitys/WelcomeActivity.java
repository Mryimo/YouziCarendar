package com.example.youzicarendar.activitys;

import android.content.Intent;
import android.os.Bundle;

import com.example.youzicarendar.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    private void init() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                toMain();
            }
        }, 3*1000);
    }

    private void toMain() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
