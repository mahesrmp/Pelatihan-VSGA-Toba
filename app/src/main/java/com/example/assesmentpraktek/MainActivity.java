    package com.example.assesmentpraktek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.assesmentpraktek.db.Database;

    public class MainActivity extends AppCompatActivity {

        private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lihatData, inputData, informasi, logout;

        lihatData = findViewById(R.id.lihat_data);
        inputData= findViewById(R.id.input_data);
        informasi = findViewById(R.id.informasi);
        logout = findViewById(R.id.logout);

        lihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, GetAllMahasiswaActivity.class);
                startActivity(intent);
            }
        });

        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, CreateMahasiswaActivity.class);
                startActivity(intent);
            }
        });

        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, InformasiActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(MainActivity.this);
                deleteBuilder.setTitle("Yakin ingin Logout?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog deleteDialog = deleteBuilder.create();
                deleteDialog.show();
            }
        });
    }
}