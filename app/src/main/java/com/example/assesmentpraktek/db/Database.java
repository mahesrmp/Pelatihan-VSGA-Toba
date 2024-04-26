package com.example.assesmentpraktek.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assesmentpraktek.model.Mahasiswa;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String db_name = "db_data_mahasiswa";
    private static final String table_mahasiswa = "mahasiswa";
    private static final String table_users = "users";
    private static final String id_col = "id";
    private static final String email_col = "email";
    private static final String password_col = "password";
    private static final String confirm_password_col = "confirm_password";
    private static final String nim_col = "nim";
    private static final String nama_col = "nama";
    private static final String tanggal_lahir_col = "tanggal_lahir";
    private static final String jenis_kelamin_col = "jenis_kelamin";
    private static final String alamat_col = "alamat";

    ArrayList<Mahasiswa> mahasiswaArrayList = new ArrayList<Mahasiswa>();

    public Database(Context context){
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ table_mahasiswa + "("
                + id_col + " INTEGER primary key autoincrement,"
                + nim_col + " Text,"
                + nama_col + " Text,"
                + tanggal_lahir_col + " Text,"
                + jenis_kelamin_col + " Text,"
                + alamat_col + " Text);");

        db.execSQL("CREATE TABLE " + table_users + "(" +
                id_col + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                email_col + " TEXT," +
                password_col + " TEXT," +
                confirm_password_col + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ table_mahasiswa);
        db.execSQL("drop table if exists "+ table_users);
        onCreate(db);
    }

    public long createData(String nim, String nama, String tanggal_lahir, String jenis_kelamin, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nim_col, nim);
        contentValues.put(nama_col ,nama);
        contentValues.put(tanggal_lahir_col ,tanggal_lahir);
        contentValues.put(jenis_kelamin_col, jenis_kelamin);
        contentValues.put(alamat_col, alamat);
        long insert = db.insert(table_mahasiswa, null, contentValues);

        return insert;
    }

    public ArrayList<Mahasiswa> getAllMahasiswas() {
        String selectAllQuery = "SELECT * FROM " + table_mahasiswa;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                int idIndex = cursor.getColumnIndex(id_col);
                if (idIndex != -1) {
                    mahasiswa.setId(cursor.getInt(idIndex));
                }
                int nimIndex = cursor.getColumnIndex(nim_col);
                if (nimIndex != -1) {
                    mahasiswa.setNim(cursor.getString(nimIndex));
                }
                int namaIndex = cursor.getColumnIndex(nama_col);
                if (namaIndex != -1) {
                    mahasiswa.setNama(cursor.getString(namaIndex));
                }
                int tanggalLahirIndex = cursor.getColumnIndex(tanggal_lahir_col);
                if (tanggalLahirIndex != -1) {
                    mahasiswa.setTanggal_lahir(cursor.getString(tanggalLahirIndex));
                }
                int jenisKelaminIndex = cursor.getColumnIndex(jenis_kelamin_col);
                if (jenisKelaminIndex != -1) {
                    mahasiswa.setJenis_kelamin(cursor.getString(jenisKelaminIndex));
                }
                int alamatIndex = cursor.getColumnIndex(alamat_col);
                if (alamatIndex != -1) {
                    mahasiswa.setAlamat(cursor.getString(alamatIndex));
                }
                mahasiswaArrayList.add(mahasiswa);
            } while (cursor.moveToNext());
        }
        return mahasiswaArrayList;
    }

    public Mahasiswa getMahasiswaById(int id) {
        Mahasiswa mahasiswa = null;
        String selectMahasiswaByIdQuery = "SELECT * FROM " + table_mahasiswa + " where " + id_col + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectMahasiswaByIdQuery, null);

        if (cursor.moveToFirst()) {
            do {
                mahasiswa = new Mahasiswa();
                int idIndex = cursor.getColumnIndex(id_col);
                if (idIndex != -1) { // Memeriksa apakah indeks kolom valid
                    mahasiswa.setId(cursor.getInt(idIndex));
                }
                int nimIndex = cursor.getColumnIndex(nim_col);
                if (nimIndex != -1) {
                    mahasiswa.setNim(cursor.getString(nimIndex));
                }
                int namaIndex = cursor.getColumnIndex(nama_col);
                if (namaIndex != -1) {
                    mahasiswa.setNama(cursor.getString(namaIndex));
                }
                int tanggalLahirIndex = cursor.getColumnIndex(tanggal_lahir_col);
                if (tanggalLahirIndex != -1) {
                    mahasiswa.setTanggal_lahir(cursor.getString(tanggalLahirIndex));
                }
                int jenisKelaminIndex = cursor.getColumnIndex(jenis_kelamin_col);
                if (jenisKelaminIndex != -1) {
                    mahasiswa.setJenis_kelamin(cursor.getString(jenisKelaminIndex));
                }
                int alamatIndex = cursor.getColumnIndex(alamat_col);
                if (alamatIndex != -1) {
                    mahasiswa.setAlamat(cursor.getString(alamatIndex));
                }
                mahasiswaArrayList.add(mahasiswa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mahasiswa;
    }

    public int updateMahasiswa(int id, String nim, String nama, String tanggal_lahir, String jenis_kelamin, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nim_col, nim);
        values.put(nama_col, nama);
        values.put(tanggal_lahir_col, tanggal_lahir);
        values.put(jenis_kelamin_col, jenis_kelamin);
        values.put(alamat_col, alamat);

        return db.update(table_mahasiswa, values, id_col + " = ?",
                new String[]{String.valueOf(id)});

    }

    public void deleteMahasiswa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_mahasiswa, id_col + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Boolean register(String email, String password, String confirm_password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(email_col, email);
        contentValues.put(password_col, password);
        contentValues.put(confirm_password_col, confirm_password);
        long result = db.insert(table_users, null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + table_users + " where "+ email_col +" = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean cekEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ table_users +" where "+email_col +" = ? AND "+ password_col +" = ?", new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean insertToMahasiswa(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nim_col, "11421001");
        contentValues.put(nama_col, "Ahmad");
        contentValues.put(tanggal_lahir_col, "2002-12-01");
        contentValues.put(jenis_kelamin_col, "Laki-laki");
        contentValues.put(alamat_col, "Laguboti");
        long result = db.insert(table_mahasiswa, null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
}
