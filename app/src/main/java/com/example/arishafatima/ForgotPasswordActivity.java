package com.example.arishafatima;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        TextInputLayout til = findViewById(R.id.tilEmail);
        TextInputEditText et = findViewById(R.id.etEmail);
        MaterialButton btn = findViewById(R.id.btnSend);

        btn.setOnClickListener(v -> {
            til.setError(null);
            String email = String.valueOf(et.getText()).trim();
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                til.setError(getString(R.string.err_email));
                return;
            }
            // Simulated recovery: pretend a link was emailed and the user opened it
            Toast.makeText(this, R.string.msg_link_sent, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ResetPasswordActivity.class);
            i.putExtra("email", email);
            startActivity(i);
            finish();
        });
    }
}