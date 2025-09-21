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

    // variables to store all the UI elements
    private EditText username, password;
    private CheckBox checkBoxPassword, checkBoxRemember;
    private TextView message;

    private static final String authPref = "authentication"; // for authentication sharedprefs, stored in file called authentication
    private static final String keyRemember = "remember"; // key for the place where the boolean will be stored that denotes whether to remember user or not
    private static final String keyUsername = "username"; // for the username

    // simulated user in a database
    private static final String simulatedUser = "admin";
    private static final String simulatedPassword = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        checkBoxPassword = findViewById(R.id.checkBoxPassword);
        checkBoxRemember = findViewById(R.id.checkBoxRemember);
        message = findViewById(R.id.message);

        // If the user was remembered from last session, then skip login
        SharedPreferences prefs = getSharedPreferences(authPref, MODE_PRIVATE);
        boolean remembered = prefs.getBoolean(keyRemember, false);
        String rememberedUser = prefs.getString(keyUsername, null);
        if (remembered && rememberedUser != null) {
            welcome(rememberedUser);
            finish();
            return;
        }

        // listener that listens for if the checkbox for show password is on or off, and then handles the logic for it
        checkBoxPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                password.setTransformationMethod(null); // will remove the text transformation if checkbox is checked
            } else {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance()); // applies password transformation
            }
            password.setSelection(password.getText().length());
        });
    }

    // handles a click on login button
    public void handleLogin(View v) {
        String user = username.getText().toString().trim(); // stores the username in string format, trimmed to remove spaces and ends
        String pass = password.getText().toString();

        // Basic validation
        if (user.isEmpty()) { username.setError("Required"); return; }
        if (pass.isEmpty()) { password.setError("Required"); return; }

        // authentication logic
        if (simulatedUser.equals(user) && simulatedPassword.equals(pass)) {
            message.setText("Login successful");

            // checks if the user needs to be remembered and remmembers them
            SharedPreferences prefs = getSharedPreferences(authPref, MODE_PRIVATE);
            prefs.edit()
                    .putBoolean(keyRemember, checkBoxRemember.isChecked())
                    .putString(keyUsername, user)
                    .apply();

            welcome(user);
            finish();
        } else {
            message.setText("Invalid username/password");
        }
    }

    // clears the login form
    public void handleCancel(View v) {
        username.setText("");
        password.setText("");
        checkBoxPassword.setChecked(false);
        checkBoxRemember.setChecked(false);
        message.setText("");
    }

    private void welcome(String username) {
        Intent i = new Intent(this, Welcome.class); // go from current activity to welcome
        i.putExtra("username", username); // passes the username to that activity so it can display it
        startActivity(i); // starts the activity
    }
}