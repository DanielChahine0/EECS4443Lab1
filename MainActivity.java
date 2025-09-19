package com.example.eecs4443lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private CheckBox cbShowPassword, cbRememberMe;
    private TextView tvMessage;

    private static final String PREFS = "auth_prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";

    // Hardcoded credentials per lab
    private static final String DEMO_USER = "admin";
    private static final String DEMO_PASS = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbShowPassword = findViewById(R.id.cbShowPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvMessage = findViewById(R.id.tvMessage);

        // If "remember me" was set, skip login
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean remembered = prefs.getBoolean(KEY_REMEMBER, false);
        String rememberedUser = prefs.getString(KEY_USERNAME, null);
        if (remembered && rememberedUser != null) {
            goToWelcome(rememberedUser);
            finish();
            return;
        }

        // Show/Hide password toggle
        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setTransformationMethod(null);
            } else {
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            etPassword.setSelection(etPassword.getText().length());
        });
    }

    public void onLoginClick(View v) {
        String u = etUsername.getText().toString().trim();
        String p = etPassword.getText().toString();

        // Basic validation
        if (u.isEmpty()) { etUsername.setError("Required"); return; }
        if (p.isEmpty()) { etPassword.setError("Required"); return; }

        // Hardcoded auth (or replace with file-based later)
        if (DEMO_USER.equals(u) && DEMO_PASS.equals(p)) {
            tvMessage.setText("Login successful");

            // Remember me
            SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
            prefs.edit()
                    .putBoolean(KEY_REMEMBER, cbRememberMe.isChecked())
                    .putString(KEY_USERNAME, u)
                    .apply();

            goToWelcome(u);
            finish();
        } else {
            tvMessage.setText("Invalid username/password");
        }
    }

    public void onCancelClick(View v) {
        etUsername.setText("");
        etPassword.setText("");
        cbShowPassword.setChecked(false);
        cbRememberMe.setChecked(false);
        tvMessage.setText("");
    }

    private void goToWelcome(String username) {
        Intent i = new Intent(this, WelcomeActivity.class);
        i.putExtra("username", username);
        startActivity(i);
    }
}
