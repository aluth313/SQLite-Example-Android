package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteexample.crudsqlite.CRUD;
import com.example.sqliteexample.model.Mahasiswa;

public class CreateActivity extends AppCompatActivity {
    EditText etNrp, etNama;
    Button btnSimpan;
    boolean isUpdate;
    CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        crud = new CRUD(this);

        etNrp = findViewById(R.id.etNrp);
        etNama = findViewById(R.id.etNama);
        btnSimpan = findViewById(R.id.btnSimpan);

        isUpdate = getIntent().getBooleanExtra("update", false);
        if (isUpdate) {
            String nrp = getIntent().getStringExtra("nrp");
            String nama = getIntent().getStringExtra("nama");
            etNrp.setText(nrp);
            etNrp.setEnabled(false);
            etNama.setText(nama);
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nrp = etNrp.getText().toString();
                String nama = etNama.getText().toString();

                if (nrp.isEmpty()) {
                    Toast.makeText(CreateActivity.this, "NRP tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (nama.isEmpty()) {
                    Toast.makeText(CreateActivity.this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    Mahasiswa mahasiswa = new Mahasiswa(nrp,nama);
                    if (isUpdate){
                        crud.update(mahasiswa);
                    } else {
                        crud.create(mahasiswa);
                    }

                    setResult(201);
                    onBackPressed();
                }
            }
        });
    }
}
