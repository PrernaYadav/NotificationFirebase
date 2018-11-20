package com.vmmor.femina;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    boolean status = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        status = checkInternetConnection();


        if (status) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                     Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 5000);

        }
        else {
            Toast.makeText(getApplicationContext(), "You don't have Internet connection. Try Again", Toast.LENGTH_LONG).show();

        }



/*        new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);*/

    }

    public boolean checkInternetConnection() {

        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] inf = connectivity.getAllNetworkInfo();
            if (inf != null) {
                for (int i = 0; i < inf.length; i++) {
                    return true;
                }
            }
        }
        return false;

    }
}
