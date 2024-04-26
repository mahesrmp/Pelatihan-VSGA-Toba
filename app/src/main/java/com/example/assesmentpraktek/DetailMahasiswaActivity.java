package com.example.assesmentpraktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assesmentpraktek.db.Database;
import com.example.assesmentpraktek.model.Mahasiswa;

public class DetailMahasiswaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        EditText nim, nama, jenis_kelamin, tanggal_lahir, alamat;
        Button home_btn = findViewById(R.id.home_btn);

        Intent intent = getIntent();
        int idMahasiswa = intent.getIntExtra("id_mahasiswa", -13);

        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        alamat = findViewById(R.id.alamat);

        Database database = new Database(this);
        Mahasiswa mahasiswa = database.getMahasiswaById(idMahasiswa);

        if (mahasiswa != null){
            nim.setText(mahasiswa.getNim());
            nama.setText(mahasiswa.getNama());
            jenis_kelamin.setText(mahasiswa.getJenis_kelamin());
            tanggal_lahir.setText(mahasiswa.getTanggal_lahir());
            alamat.setText(mahasiswa.getAlamat());
        }

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailMahasiswaActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}