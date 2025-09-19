package com.example.loginlab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    private static final String PREFS = "auth_prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String username = getIntent().getStringExtra("username");
        TextView tv = findViewById(R.id.tvWelcome);
        if (username == null) {
            // Fallback to remembered username
            SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
            username = prefs.getString(KEY_USERNAME, "user");
        }
        tv.setText("Welcome, " + username + "!");
    }

    public void onLogoutClick(View v) {
        // Clear "remember me" so login shows next time
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_REMEMBER, false).apply();
        finish();
    }
}
