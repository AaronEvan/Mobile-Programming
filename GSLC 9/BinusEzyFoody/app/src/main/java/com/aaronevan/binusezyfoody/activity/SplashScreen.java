package com.aaronevan.binusezyfoody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aaronevan.binusezyfoody.R;

public class SplashScreen extends AppCompatActivity {
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        count = 0;
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(count==2){
                    backMain();
                    return;
                }else{
                    count++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void backMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}