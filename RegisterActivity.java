package com.example.eecs4443lab1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    // variables to store all the releavent UI elements
    private EditText username, password;

    // will simulate the DB
    private static final String databasePref = "registeredUsers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);




    }

    public void handleRegister(View v) {
        String user = username.getText().toString().trim(); // stores the username in string format, trimmed to remove spaces and ends
        String pass = password.getText().toString();

        // Basic validation
        if (user.isEmpty()) { username.setError("Required"); return; }
        if (pass.isEmpty()) { password.setError("Required"); return; }

        // enter user into db
        SharedPreferences sp = getSharedPreferences(databasePref, MODE_PRIVATE);
        if (sp.contains(user)) {
            return;
        }
        sp.edit().putString(user, pass).apply();

        finish();


    }

    public void handleCancel(View v) {
        username.setText("");
        password.setText("");
    }
}
