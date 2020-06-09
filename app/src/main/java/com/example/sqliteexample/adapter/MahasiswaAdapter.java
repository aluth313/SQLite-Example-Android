package com.example.sqliteexample.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteexample.CreateActivity;
import com.example.sqliteexample.MainActivity;
import com.example.sqliteexample.R;
import com.example.sqliteexample.model.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    MainActivity ctx;
    private ArrayList<Mahasiswa> dataList;

    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList, MainActivity ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, final int position) {
        holder.tvNrp.setText(dataList.get(position).getNrp());
        holder.tvNama.setText(dataList.get(position).getNama());
        holder.linearRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk update
                Intent intent = new Intent(ctx, CreateActivity.class);
                intent.putExtra("update",true);
                intent.putExtra("nrp",dataList.get(position).getNrp());
                intent.putExtra("nama",dataList.get(position).getNama());
                ctx.startActivityForResult(intent,201);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNrp, tvNama;
        LinearLayoutCompat linearRoot;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNrp = itemView.findViewById(R.id.tvNrp);
            tvNama = itemView.findViewById(R.id.tvNama);
            linearRoot = itemView.findViewById(R.id.layoutRoot);
        }
    }
}
