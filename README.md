# KnightApp - Android Assignment 2

An Android application (Java + XML) with common app screens, built with a **Knight Blue (#0B2447)** and **Dark Blue (#061326)** theme, including light and dark mode support.

## Features

- **Splash Screen:** shows the app logo and name, then automatically navigates to Login.
- **Login:** email/username and password fields with validation, plus Login, Register and Forgot Password options.
- **Register:** name, email, password and confirm password with validation (valid email, minimum 6 characters with at least 1 digit, passwords must match).
- **Forgot Password:** accepts an email and starts a simulated password recovery.
- **Reset Password:** new password and confirm password with validation.
- **Dashboard:** main modules shown as cards in a grid, each with an icon and text.
- **Profile:** displays user information and provides a Logout option.

## Demo Login

| Field    | Value             |
|----------|-------------------|
| Email    | admin@knight.com  |
| Password | Knight@123        |

Any account created through the Register screen also works. Authentication and password recovery are simulated locally using SharedPreferences. No backend is used.

## Navigation Flow

```
Splash -> Login -> Dashboard -> Profile -> (Logout) -> Login
            |
            |-> Register -> Login
            |
            |-> Forgot Password -> Reset Password -> Login
```

All screens are connected using **explicit Intents**.

## Technical Details

- **Language:** Java
- **UI:** XML layouts with Material Components
- **Layouts used:**
  - ConstraintLayout: Splash, Forgot Password, Profile
  - LinearLayout: Login, Register, Reset Password
  - GridLayout: Dashboard
- **Input controls:** `textEmailAddress` for email, `textPassword` (with show/hide toggle) for passwords, `textPersonName` for name
- **Resources:**
  - `strings.xml` for all text
  - `colors.xml` for the default (light) theme
  - `values-night/colors.xml` for night-mode colors
  - `themes.xml` and drawable files for gradients and the logo
- **Validation:** empty fields, email format, password length and digit rule, password match

## Screens and Files

| Screen          | Java file                   | Layout file                    |
|-----------------|-----------------------------|--------------------------------|
| Splash          | SplashActivity.java         | activity_splash.xml            |
| Login           | LoginActivity.java          | activity_login.xml             |
| Register        | RegisterActivity.java       | activity_register.xml          |
| Forgot Password | ForgotPasswordActivity.java | activity_forgot_password.xml   |
| Reset Password  | ResetPasswordActivity.java  | activity_reset_password.xml    |
| Dashboard       | DashboardActivity.java      | activity_dashboard.xml         |
| Profile         | ProfileActivity.java        | activity_profile.xml           |

Helper class: `UserStore.java` (local storage for the simulated login).

## How to Run

1. Clone the repository: `git clone https://github.com/arishAI-08/App-Development-.git`
2. Open the project in Android Studio.
3. Let Gradle sync, then run the app on an emulator or a physical device (minSdk 24).

## Author

Arisha Fatima
