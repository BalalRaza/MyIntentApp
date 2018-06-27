package com.example.figg_user.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the button */
    public void changeApp(View view) {
        PackageManager packageManager = getPackageManager();
        Intent uberIntent = packageManager.getLaunchIntentForPackage("com.ubercab");
        startActivity(uberIntent);
    }
}
