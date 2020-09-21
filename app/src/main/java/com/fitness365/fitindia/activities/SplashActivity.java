package com.fitness365.fitindia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.helper.AppConfig;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        convertToFullScreen();
        setContentView(R.layout.activity_splash);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (sharedPreferences.getBoolean(AppConfig.IS_LOGGED_IN,false)){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        new Thread(runnable).start();
    }

    private void convertToFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
