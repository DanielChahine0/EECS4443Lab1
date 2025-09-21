package com.example.eecs4443lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Register screen for creating a new local user (Optional extension for Lab 1).
 * Stores credentials in SharedPreferences for demo purposes only.
 */
public class Register extends AppCompatActivity {

    private EditText etNewUsername, etNewPassword, etConfirmPassword;
    private TextView tvRegisterMsg;

    // Simple local storage for demo (acceptable per lab’s optional extension)
    // You can reuse the same SharedPreferences your login uses.
    private static final String PREFS = "users_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvRegisterMsg   = findViewById(R.id.tvRegisterMsg);

        findViewById(R.id.btnCreateAccount).setOnClickListener(this::onCreateAccountClick);
        findViewById(R.id.btnCancelRegister).setOnClickListener(v -> finish());
    }

    private void onCreateAccountClick(View v) {
        String u = etNewUsername.getText().toString().trim();
        String p = etNewPassword.getText().toString();
        String c = etConfirmPassword.getText().toString();

        // --- Basic validation (aligns with lab’s “ensure fields are completed”) ---
        if (TextUtils.isEmpty(u) || TextUtils.isEmpty(p) || TextUtils.isEmpty(c)) {
            tvRegisterMsg.setText("Please fill in all fields.");
            return;
        }
        if (p.length() < 4) {
            tvRegisterMsg.setText("Password must be at least 4 characters.");
            return;
        }
        if (!p.equals(c)) {
            tvRegisterMsg.setText("Passwords do not match.");
            return;
        }

        // --- Store the new user locally for demo purposes (optional extension) ---
        SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
        if (sp.contains(u)) {
            tvRegisterMsg.setText("Username already exists.");
            return;
        }
        sp.edit().putString(u, p).apply();

        // Option 1: return the new username to the Login screen so you can prefill it
        Intent result = new Intent();
        result.putExtra("new_username", u);
        setResult(RESULT_OK, result);

        tvRegisterMsg.setText("Account created.");
        finish();
    }
}
