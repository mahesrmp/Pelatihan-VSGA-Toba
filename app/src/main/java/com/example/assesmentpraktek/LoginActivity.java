package com.example.assesmentpraktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assesmentpraktek.db.Database;

public class LoginActivity extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email_et = findViewById(R.id.email);
        EditText password_et = findViewById(R.id.password);
        Button login_btn = findViewById(R.id.login);
        TextView registerRedirect = findViewById(R.id.registerRedirect);
        database = new Database(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email = email_et.getText().toString();
                password = password_et.getText().toString();
                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password tidak boleh kosong ", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUser = database.cekEmailPassword(email, password);
                    if (checkUser == true) {
                        Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}