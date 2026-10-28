package com.example.arishafatima;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout tilUser, tilPass;
    private TextInputEditText etUser, etPass;
    private UserStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        store = new UserStore(this);

        tilUser = findViewById(R.id.tilUser);
        tilPass = findViewById(R.id.tilPass);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);
        TextView tvForgot = findViewById(R.id.tvForgot);

        btnLogin.setOnClickListener(v -> attemptLogin());
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
        tvForgot.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPasswordActivity.class)));
    }

    private void attemptLogin() {
        tilUser.setError(null);
        tilPass.setError(null);
        String user = String.valueOf(etUser.getText()).trim();
        String pass = String.valueOf(etPass.getText());

        boolean ok = true;
        if (TextUtils.isEmpty(user)) { tilUser.setError(getString(R.string.err_required)); ok = false; }
        if (TextUtils.isEmpty(pass)) { tilPass.setError(getString(R.string.err_required)); ok = false; }
        if (!ok) return;

        String name = store.validate(user, pass);
        if (name == null) {
            Toast.makeText(this, R.string.err_invalid_login, Toast.LENGTH_SHORT).show();
            return;
        }
        String email = user.contains("@") ? user : "admin@knight.com";
        store.setSession(name, email);

        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
        finish();
    }
}