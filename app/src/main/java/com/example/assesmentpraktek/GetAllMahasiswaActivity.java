package com.example.assesmentpraktek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assesmentpraktek.db.Database;
import com.example.assesmentpraktek.model.Mahasiswa;

import java.util.ArrayList;

public class GetAllMahasiswaActivity extends AppCompatActivity {
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private DataMahasiswaActivity dataMahasiswaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_mahasiswa);

        ListView listDataMahasiswa = findViewById(R.id.list_data_mahasiswa);
        TextView addDataMahasiswa = findViewById(R.id.tambah_data);

        Database database = new Database(this);
        mahasiswaArrayList = database.getAllMahasiswas();

        dataMahasiswaActivity = new DataMahasiswaActivity(this, mahasiswaArrayList);
        listDataMahasiswa.setAdapter(dataMahasiswaActivity);

        listDataMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            final CharSequence[] options = {"Lihat Data", "Update Data", "Hapus Data"};
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mahasiswa mahasiswaGetId = mahasiswaArrayList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(GetAllMahasiswaActivity.this);
                builder.setTitle("Pilihan")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedOption = options[which].toString();
                                switch (selectedOption) {
                                    case "Lihat Data":
                                        Intent intent = new Intent(GetAllMahasiswaActivity.this, DetailMahasiswaActivity.class);
                                        intent.putExtra("id_mahasiswa", mahasiswaGetId.getId());
                                        startActivity(intent);
                                        break;
                                    case "Update Data":
                                        Intent intent1 = new Intent(GetAllMahasiswaActivity.this, UpdateMahasiswaActivity.class);
                                        intent1.putExtra("id_mahasiswa", mahasiswaGetId.getId());
                                        startActivity(intent1);
                                        break;
                                    case "Hapus Data":
                                        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(GetAllMahasiswaActivity.this);
                                        deleteBuilder.setTitle("Yakin ingin menghapus?")
                                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        database.deleteMahasiswa(mahasiswaGetId.getId());
                                                        refreshMahasiswaList();
                                                    }
                                                })
                                                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                        AlertDialog deleteDialog = deleteBuilder.create();
                                        deleteDialog.show();
                                        break;
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        addDataMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetAllMahasiswaActivity.this, CreateMahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshMahasiswaList();
    }

    private void refreshMahasiswaList() {
        Database database = new Database(this);
        mahasiswaArrayList.clear();
        mahasiswaArrayList.addAll(database.getAllMahasiswas());
        dataMahasiswaActivity.notifyDataSetChanged();
    }
}