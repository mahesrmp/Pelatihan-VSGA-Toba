package com.example.assesmentpraktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assesmentpraktek.db.Database;
import com.example.assesmentpraktek.model.Mahasiswa;

public class CreateMahasiswaActivity extends AppCompatActivity {

    Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mahasiswa);

        EditText nim_et, nama_et, jenis_kelamin_et, tanggal_lahir_et, alamat_et;
        Button simpan_btn = findViewById(R.id.create);
        Intent intent = getIntent();

        nim_et = findViewById(R.id.nim);
        nama_et = findViewById(R.id.nama);
        jenis_kelamin_et = findViewById(R.id.jenis_kelamin);
        tanggal_lahir_et = findViewById(R.id.tanggal_lahir);
        alamat_et = findViewById(R.id.alamat);

        simpan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim, nama, jenis_kelamin, tanggal_lahir, alamat;
                nim = nim_et.getText().toString();
                nama = nama_et.getText().toString();
                jenis_kelamin = jenis_kelamin_et.getText().toString();
                tanggal_lahir = tanggal_lahir_et.getText().toString();
                alamat = alamat_et.getText().toString();
                if (nim.isEmpty() || nama.isEmpty() || jenis_kelamin.isEmpty() || tanggal_lahir.isEmpty() || alamat.isEmpty()) {
                    Toast.makeText(CreateMahasiswaActivity.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                }else {
                    long createData =  database.createData(nim, nama, jenis_kelamin, tanggal_lahir, alamat);
                    if (createData > 0){
                        Intent intent1 = new Intent(CreateMahasiswaActivity.this, GetAllMahasiswaActivity.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(CreateMahasiswaActivity.this, "Gagal menambahkan data!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}