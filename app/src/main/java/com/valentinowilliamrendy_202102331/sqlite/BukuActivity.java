package com.valentinowilliamrendy_202102331.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BukuActivity extends AppCompatActivity {
    EditText kode, judul, pengarang, penerbit, isbn;
    Button simpan, tampil, edit, hapus;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);

        kode = findViewById(R.id.edtkode);
        judul = findViewById(R.id.edtjudul);
        pengarang = findViewById(R.id.edtpengarang);
        penerbit = findViewById(R.id.edtpenerbit);
        isbn = findViewById(R.id.edtisbn);
        simpan = findViewById(R.id.btnsimpan1);
        tampil = findViewById(R.id.btntampil1);
        edit = findViewById(R.id.btnedit1);
        hapus = findViewById(R.id.btnhapus1);

        DB = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kd = kode.getText().toString();
                String jd = judul.getText().toString();
                String peng = pengarang.getText().toString();
                String pen = penerbit.getText().toString();
                String isb = isbn.getText().toString();

                if (TextUtils.isEmpty(kd) || TextUtils.isEmpty(jd) || TextUtils.isEmpty(peng) || TextUtils.isEmpty(pen) || TextUtils.isEmpty(isb))
                    Toast.makeText(BukuActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                else {
                    Boolean cekDataBOOK= DB.cekDataBOOK(kd);
                    if (cekDataBOOK == false){
                        Boolean insert = DB.insertDataBOOK(kd,jd,peng,pen,isb);
                        if (insert == true){
                            Toast.makeText(BukuActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(BukuActivity.this,"Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(BukuActivity.this,"Data Mahasiswa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.tampilDataBOOK();
                if (res.getCount()==0){
                    Toast.makeText(BukuActivity.this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer =  new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("NIM Mahasiswa : "+res.getString(0)+"\n");
                    buffer.append("Nama Mahasiswa : "+res.getString(1)+"\n");
                    buffer.append("Jenis Kelamin : "+res.getString(2)+"\n");
                    buffer.append("Alamat : "+res.getString(3)+"\n");
                    buffer.append("Email : "+res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(BukuActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Biodata Mahasiswa");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kb = kode.getText().toString();
                Boolean cekHapusData = DB.hapusDataBOOK(kb);
                if (cekHapusData == true)
                    Toast.makeText(BukuActivity.this, "Data Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BukuActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kd_ = kode.getText().toString();
                String jd_ = judul.getText().toString();
                String peng_ = pengarang.getText().toString();
                String pen_ = penerbit.getText().toString();
                String isb_ = isbn.getText().toString();

                if (TextUtils.isEmpty(kd_) || TextUtils.isEmpty(jd_) || TextUtils.isEmpty(peng_) || TextUtils.isEmpty(pen_) || TextUtils.isEmpty(isb_))
                    Toast.makeText(BukuActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                else {
                    Boolean edit = DB.editDataBOOK(kd_,jd_,peng_,pen_,isb_);
                    if (edit == false){
                        Toast.makeText(BukuActivity.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(BukuActivity.this,"Data gagal diedit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}