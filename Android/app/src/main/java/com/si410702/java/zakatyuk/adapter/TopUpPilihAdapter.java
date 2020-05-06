package com.si410702.java.zakatyuk.adapter;

import android.app.Activity;
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
import com.si410702.java.zakatyuk.TopUpInputDataActivity;
import com.si410702.java.zakatyuk.model.PilihTopUp;

import java.util.ArrayList;

public class TopUpPilihAdapter extends RecyclerView.Adapter<TopUpPilihAdapter.ViewHolder> {
    private ArrayList<PilihTopUp> pilihTopUps = new ArrayList<>();

    public TopUpPilihAdapter(ArrayList<PilihTopUp> pilihTopUps) {
        this.pilihTopUps.clear();
        this.pilihTopUps = pilihTopUps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_pilih_topup, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(pilihTopUps.get(position));
    }

    @Override
    public int getItemCount() {
        return pilihTopUps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivListPilih;
        TextView tvListPilih;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivListPilih = itemView.findViewById(R.id.iv_list_pilih_topup);
            tvListPilih = itemView.findViewById(R.id.tv_list_pilih_topup);
            itemView.setOnClickListener(this);
        }

        void bind(PilihTopUp pilihTopUp) {
            tvListPilih.setText(pilihTopUp.getNamaPilihTopUp());
            Glide.with(itemView.getContext())
                    .load(pilihTopUp.getGambarPilihTopUp())
                    .apply(new RequestOptions())
                    .into(ivListPilih);
        }

        @Override
        public void onClick(View view) {
            PilihTopUp pilihTopUp = pilihTopUps.get(getAdapterPosition());

            Intent intent = new Intent(view.getContext(), TopUpInputDataActivity.class);
            intent.putExtra("JenisTopUp", pilihTopUp.getNamaPilihTopUp());
            view.getContext().startActivity(intent);
//            ((Activity)view.getContext()).finish();
        }
    }
}
