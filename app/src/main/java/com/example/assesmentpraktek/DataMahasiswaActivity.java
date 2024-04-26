package com.example.assesmentpraktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assesmentpraktek.model.Mahasiswa;

import java.util.ArrayList;

public class DataMahasiswaActivity extends ArrayAdapter<Mahasiswa> {

    private Context context;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    public DataMahasiswaActivity(Context context, ArrayList<Mahasiswa> mahasiswaArrayList) {
        super(context, 0, mahasiswaArrayList);
        this.context = context;
        this.mahasiswaArrayList = mahasiswaArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mahasiswa mahasiswa = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_data_mahasiswa, parent, false);
        }
        TextView nama = convertView.findViewById(R.id.nama);
        nama.setText(mahasiswa.getNama());
        return convertView;
    }
}