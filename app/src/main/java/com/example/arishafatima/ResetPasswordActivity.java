package com.example.arishafatima;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        String email = getIntent().getStringExtra("email");
        TextInputLayout tilNew = findViewById(R.id.tilNew);
        TextInputLayout tilConfirm = findViewById(R.id.tilConfirm);
        TextInputEditText etNew = findViewById(R.id.etNew);
        TextInputEditText etConfirm = findViewById(R.id.etConfirm);
        MaterialButton btn = findViewById(R.id.btnReset);

        btn.setOnClickListener(v -> {
            tilNew.setError(null);
            tilConfirm.setError(null);
            String p1 = String.valueOf(etNew.getText());
            String p2 = String.valueOf(etConfirm.getText());

            boolean ok = true;
            if (p1.length() < 6 || !p1.matches(".*\\d.*")) {
                tilNew.setError(getString(R.string.err_password_len)); ok = false;
            }
            if (!p1.equals(p2)) {
                tilConfirm.setError(getString(R.string.err_mismatch)); ok = false;
            }
            if (!ok) return;

            if (email != null) new UserStore(this).updatePassword(email, p1);
            Toast.makeText(this, R.string.msg_reset_ok, Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }
}