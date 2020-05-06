package com.si410702.java.zakatyuk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.model.PilihTopUp;
import com.si410702.java.zakatyuk.model.TopUp;

import java.util.ArrayList;

public class TopUpSaldoAdapter extends RecyclerView.Adapter<TopUpSaldoAdapter.ViewHolder> {
    private ArrayList<TopUp> topUps = new ArrayList<>();

    public void setTopUps(ArrayList<TopUp> topUps) {
        this.topUps.clear();
        this.topUps = topUps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_history_topup, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(topUps.get(position));
    }

    @Override
    public int getItemCount() {
        return topUps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvListJenis, tvListIdTr, tvListJumlah, tvListDateTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListJenis = itemView.findViewById(R.id.tv_history_jenis);
            tvListIdTr = itemView.findViewById(R.id.tv_history_transaksi);
            tvListJumlah = itemView.findViewById(R.id.tv_history_jumlah);
            tvListDateTime = itemView.findViewById(R.id.tv_history_waktu);
            itemView.setOnClickListener(this);
        }

        public void bind(TopUp topUp) {
            tvListJenis.setText("Jenis Top Up: "+topUp.getTopUpName());
            tvListIdTr.setText("ID Transaksi: "+topUp.getTopUpId());
            tvListJumlah.setText("Jumlah: "+topUp.getTopUpValue());
            tvListDateTime.setText(topUp.getTopUpDate() + " " + topUp.getTopUpTime());
        }

        @Override
        public void onClick(View view) {

        }
    }
}
