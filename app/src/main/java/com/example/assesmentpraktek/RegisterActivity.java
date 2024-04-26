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

public class RegisterActivity extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email_et, password_et, confirm_password_et;
        Button register_btn;

        email_et = findViewById(R.id.email);
        password_et = findViewById(R.id.password);
        confirm_password_et = findViewById(R.id.confirm_password);
        register_btn = findViewById(R.id.register);
        TextView loginRedirect = findViewById(R.id.loginRedirect);
        database = new Database(this);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, confirm_password;

                email = email_et.getText().toString();
                password = password_et.getText().toString();
                confirm_password = confirm_password_et.getText().toString();
                if (email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Masih terdapat field yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirm_password)) {
                        Boolean checkUserEmail = database.checkEmail(email);
                        if (checkUserEmail == false) {
                            Boolean insert = database.register(email, password, confirm_password);
                            if (insert == true) {
                                Toast.makeText(RegisterActivity.this, "Register Berhasil!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register Gagal!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User sudah ada! Lakukan login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password Salah!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}