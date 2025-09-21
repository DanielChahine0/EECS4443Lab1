package com.example.eecs4443lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private static final String authPrefs = "authentication";
    private static final String keyRemember = "remember";
    private static final String keyUsername = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String username = getIntent().getStringExtra("username");
        TextView tv = findViewById(R.id.welcome);
        if (username == null) {
            // Fallback to remembered username
            SharedPreferences prefs = getSharedPreferences(authPrefs, MODE_PRIVATE);
            username = prefs.getString(keyUsername, "user");
        }
        tv.setText("Welcome, " + username + "!");
    }

    // handles logout logic
    public void handleLogout(View v) {
        // delete the saved information
        SharedPreferences prefs = getSharedPreferences(authPrefs, MODE_PRIVATE);
        prefs.edit().putBoolean(keyRemember, false).remove(keyUsername).apply();

        // go back to login screen
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }
}
