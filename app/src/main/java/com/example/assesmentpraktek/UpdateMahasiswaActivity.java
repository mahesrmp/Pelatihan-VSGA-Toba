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

public class UpdateMahasiswaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        EditText nim, nama, jenis_kelamin, tanggal_lahir, alamat;
        Button simpan = findViewById(R.id.update);
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

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimBaru = nim.getText().toString();
                String namaBaru = nama.getText().toString();
                String tanggalLahirBaru = tanggal_lahir.getText().toString();
                String jenisKelaminBaru = jenis_kelamin.getText().toString();
                String alamatBaru = alamat.getText().toString();

                if (nimBaru.isEmpty() || namaBaru.isEmpty() || tanggalLahirBaru.isEmpty() || jenisKelaminBaru.isEmpty() || alamatBaru.isEmpty()) {
                    Toast.makeText(UpdateMahasiswaActivity.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                }else{
                    int updateData = database.updateMahasiswa(idMahasiswa, nimBaru, namaBaru, tanggalLahirBaru, jenisKelaminBaru, alamatBaru);

                    if (updateData > 0){
                        Toast.makeText(UpdateMahasiswaActivity.this, "Data Mahasiswa berhasil diperbarui", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(UpdateMahasiswaActivity.this, DetailMahasiswaActivity.class);
                        intent1.putExtra("id_mahasiswa", idMahasiswa);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(UpdateMahasiswaActivity.this, "Gagal memperbarui data Mahasiswa", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}