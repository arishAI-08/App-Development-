package com.example.arishafatima;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout tilName, tilEmail, tilPass, tilConfirm;
    private TextInputEditText etName, etEmail, etPass, etConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPass = findViewById(R.id.tilPass);
        tilConfirm = findViewById(R.id.tilConfirm);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConfirm = findViewById(R.id.etConfirm);
        MaterialButton btn = findViewById(R.id.btnCreate);

        btn.setOnClickListener(v -> validateAndRegister());
    }

    private void validateAndRegister() {
        tilName.setError(null); tilEmail.setError(null);
        tilPass.setError(null); tilConfirm.setError(null);

        String name = String.valueOf(etName.getText()).trim();
        String email = String.valueOf(etEmail.getText()).trim();
        String pass = String.valueOf(etPass.getText());
        String confirm = String.valueOf(etConfirm.getText());

        boolean ok = true;
        if (name.isEmpty()) { tilName.setError(getString(R.string.err_required)); ok = false; }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError(getString(R.string.err_email)); ok = false;
        }
        if (pass.length() < 6 || !pass.matches(".*\\d.*")) {
            tilPass.setError(getString(R.string.err_password_len)); ok = false;
        }
        if (!pass.equals(confirm)) {
            tilConfirm.setError(getString(R.string.err_mismatch)); ok = false;
        }
        if (!ok) return;

        new UserStore(this).register(name, email, pass);
        Toast.makeText(this, R.string.msg_registered, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}