package com.si410702.java.zakatyuk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si410702.java.zakatyuk.R;

import java.util.ArrayList;

public class PenerimaZakatAdapter extends RecyclerView.Adapter<PenerimaZakatAdapter.ViewHolder> {
    private ArrayList<String> zakat = new ArrayList<>();

    public PenerimaZakatAdapter(ArrayList<String> zakat) {
        this.zakat = zakat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_penerima_zakat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(zakat.get(position));
    }

    @Override
    public int getItemCount() {
        return zakat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPenerima;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPenerima = itemView.findViewById(R.id.tv_penerima_zakat);
        }

        void bind(String penerima) {
            tvPenerima.setText(penerima);
        }
    }
}
