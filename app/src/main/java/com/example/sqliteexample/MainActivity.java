package com.example.sqliteexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqliteexample.adapter.MahasiswaAdapter;
import com.example.sqliteexample.crudsqlite.CRUD;
import com.example.sqliteexample.model.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMahasiswa;
    private MahasiswaAdapter adapter;

    CRUD crud;
    static final int CREATE = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMahasiswa = findViewById(R.id.rvMahasiswa);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, CreateActivity.class), CREATE);
            }
        });

        getData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE){
            getData();
        }
    }

    private void getData() {
        crud = new CRUD(this);
        ArrayList<Mahasiswa> mahasiswas = crud.selectAll();

        System.out.println(mahasiswas.size());
        adapter = new MahasiswaAdapter(mahasiswas,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        rvMahasiswa.setLayoutManager(layoutManager);
        rvMahasiswa.setAdapter(adapter);
    }
}
