# EECS4443Lab1

## Group Members:
- Stefewn Johnson
- Muhammmad Zamin
- Daniel Chahine
- Yuriy Kotyashko

## Project Rules & Naming (IMPORTANT)

- **Project name:** `EECS4443Lab1` (Android Studio project/module name).
- **Package name (lowercase):** `com.example.eecs4443lab1`  
  > Android requires lowercase package names. We should keep the project name exactly `EECS4443Lab1`, but the Java package header must be lowercase.

- **Activities and filenames (must match exactly, including case):**
  - `MainActivity.java` → `public class MainActivity`
  - `WelcomeActivity.java` → `public class Welcome`
  - `RegisterActivity.java` → `public class Register`

### Creating a new activity:
When creating a new activity for the welcome screen and the register screen we need to make sure that the activity is called `WelcomeActivity` and `RegisterActivity` (not any other variation).

## Features

### Login Screen
- Two `EditText` fields: **username**, **password**
- `Login` button → triggers validation and navigation
- `Cancel` button → clears inputs and message
- `TextView` for feedback messages (e.g., “Login Successful”, “Invalid username/password”)
- **Show Password** checkbox → toggles password visibility
- **Remember Me** checkbox → stores login status/credentials in `SharedPreferences`
- **On relaunch**: if remembered, skip login and go directly to **Welcome** screen

### Welcome Screen
- On successful login, opens `Welcome` activity
- Greets the user by **username** (passed via `Intent` extras)

### Register Screen
- `Register` activity and `activity_register.xml` (basic form: username, password, confirm password)
- Local-only validation (no DB); optional return to Login


## Requirements Checklist:
- [x] Build a login page using XML layouts
- [x] The UI must contain:
  - [x] 2 EditText fields for username and password
  - [x] A login button
  - [x] A Cancel button
  - [x] A TextView for displaying error messages or success messages
- [x] Validation Logic in Java
  - [x] Ensure all fields are completed 
  - [x] Validation against hardcoded credentials (admin, 1234)
- [x] Add a "Show Password" checkbox
- [x] Implement a "Remember Me" feature
  - [x] Checkbox to remember username
  - [x] Store username using SharedPreferences
  - [x] On app launch, if the username is remembered, skip login page and go to welcome screen
- [x] Welcome Screen
  - [x] On successful login, navigate to a new activity using intents.
- [ ] Optional Extensions:
  - [ ] Create a registration page that allows users to input
    - [ ] Username
    - [ ] Password
  - [ ] Store these credentials in memory or a simple file
  - [ ] Adjust login validation to check against registered users
- [ ] Points to Remmember:
  - [x] Use approipriate layout 
  - [ ] Add comments to the code
  - [x] Keep project oraganized
  - [ ] Short README file:
    - [x] List group members
    - [x] Include a short group contribution
    - [ ] Limitations (if any) or incomplete parts
  - [ ] Ensures that the project runs without errors

## Key Notes:
1. The project should be named "EECS4443Lab1" in order to match with the java package headers.
2. When creating a new activity for the welcome screen, make sure the activity is called "Welcome" (not WelcomeActivity or any other variation).
3. When creating a another new activity for the registration screen, make sure the activity is called "Register" (not RegisterActivity or any other variation).

## Group Contributions:

## Limitations: