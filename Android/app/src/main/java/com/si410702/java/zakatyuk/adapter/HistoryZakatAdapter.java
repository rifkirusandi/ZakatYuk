package com.si410702.java.zakatyuk.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.TrackingActivity;
import com.si410702.java.zakatyuk.model.Bayar;

import java.text.NumberFormat;
import java.util.ArrayList;

public class HistoryZakatAdapter extends RecyclerView.Adapter<HistoryZakatAdapter.ViewHolder> {
    private ArrayList<Bayar> bayars = new ArrayList<>();

    public void setBayar(ArrayList<Bayar> bayars) {
        this.bayars.clear();
        this.bayars = bayars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_history_zakat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(bayars.get(position));
    }

    @Override
    public int getItemCount() {
        return bayars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvJenisZakat, tvTanggalZakat, tvNominal;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJenisZakat = itemView.findViewById(R.id.tv_jenis_zakat);
            tvTanggalZakat = itemView.findViewById(R.id.tv_tanggal_zakat);
            tvNominal = itemView.findViewById(R.id.tv_nominal);
            imgView = itemView.findViewById(R.id.centang);
            itemView.setOnClickListener(this);
        }

        public void bind(Bayar bayar) {
            tvJenisZakat.setText(bayar.getJenisZakat());
            tvTanggalZakat.setText(bayar.getBayarDate() + " " + bayar.getBayarTime());
            NumberFormat format = NumberFormat.getCurrencyInstance();
            tvNominal.setText(format.format(Integer.parseInt(bayar.getBayarNominal())));
            /*if (bayar.getBayarStatus().equals(""))
                Glide.with(itemView.getContext())
                        .load(pilihTopUp.getGambarPilihTopUp())
                        .apply(new RequestOptions())
                        .into(imgView);*/
        }

        @Override
        public void onClick(View view) {
            Bayar bayar = bayars.get(getAdapterPosition());

            Intent intent = new Intent(view.getContext(), TrackingActivity.class);
            intent.putExtra("bayarId", bayar.getBayarId());
            view.getContext().startActivity(intent);
        }
    }
}
