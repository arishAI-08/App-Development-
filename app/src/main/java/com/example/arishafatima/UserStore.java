package com.example.arishafatima;

import android.content.Context;
import android.content.SharedPreferences;

public class UserStore {
    private static final String PREF = "knight_prefs";
    private static final String DEFAULT_EMAIL = "admin@knight.com";
    private static final String DEFAULT_PASS = "Knight@123";
    private static final String DEFAULT_NAME = "Knight Admin";

    private final SharedPreferences sp;

    public UserStore(Context c) {
        sp = c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void register(String name, String email, String pass) {
        sp.edit().putString("name", name).putString("email", email)
                .putString("pass", pass).apply();
    }

    /** Returns display name if credentials are valid, otherwise null. */
    public String validate(String idOrEmail, String pass) {
        String id = idOrEmail.trim();
        if ((id.equalsIgnoreCase(DEFAULT_EMAIL) || id.equalsIgnoreCase("admin"))
                && pass.equals(DEFAULT_PASS)) {
            return DEFAULT_NAME;
        }
        String email = sp.getString("email", null);
        String savedPass = sp.getString("pass", null);
        if (email != null && id.equalsIgnoreCase(email) && pass.equals(savedPass)) {
            return sp.getString("name", "User");
        }
        return null;
    }

    public void updatePassword(String email, String newPass) {
        String saved = sp.getString("email", null);
        if (saved != null && saved.equalsIgnoreCase(email)) {
            sp.edit().putString("pass", newPass).apply();
        }
    }

    public void setSession(String name, String email) {
        sp.edit().putString("s_name", name).putString("s_email", email).apply();
    }

    public String getSessionName() { return sp.getString("s_name", "Guest"); }
    public String getSessionEmail() { return sp.getString("s_email", "guest@example.com"); }

    public void clearSession() {
        sp.edit().remove("s_name").remove("s_email").apply();
    }
}