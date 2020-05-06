package com.si410702.java.zakatyuk.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si410702.java.zakatyuk.AlokasiZakatDetail;
import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.model.Alokasi;
import com.si410702.java.zakatyuk.model.Bayar;
import com.si410702.java.zakatyuk.model.User;

import java.text.NumberFormat;
import java.util.ArrayList;

public class AlokasiZakatListAdapter extends RecyclerView.Adapter<AlokasiZakatListAdapter.ViewHolder> {

    private ArrayList<Bayar> userListBayar = new ArrayList<>();
    private ArrayList<User> user = new ArrayList<>();
    private ArrayList<Alokasi> alokasi = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();
    String name, email, telp;


    public void setUserListBayar(ArrayList<Bayar> userListBayar) {
        this.userListBayar.clear();
        this.userListBayar = userListBayar;
        notifyDataSetChanged();
    }

    public void setUserList(ArrayList<User> user) {
        this.user.clear();
        this.user = user;
        notifyDataSetChanged();
    }

    public void setAlokasiList(ArrayList<Alokasi> alokasi) {
        this.alokasi.clear();
        this.alokasi = alokasi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlokasiZakatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_alokasi_user, viewGroup, false);
        return new AlokasiZakatListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlokasiZakatListAdapter.ViewHolder holder, int position) {
        holder.bind(userListBayar.get(position));

    }

    @Override
    public int getItemCount() {
        return userListBayar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvUsername, tvJumlahZakat;
        ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.list_user_name);
            tvJumlahZakat = itemView.findViewById(R.id.list_user_jumlahzakat);
            ivIcon = itemView.findViewById(R.id.keterangan_icon);
            itemView.setOnClickListener(this);
        }

        public void bind(Bayar userList) {

            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getId().equals(userList.getBayarUserId())) {
                    name = user.get(i).getUsername();
                }
            }

            ivIcon.setBackgroundResource(R.drawable.ic_app_hourglass);
            for (int i = 0; i < alokasi.size(); i++) {
                if (alokasi.get(i).getIdBayar().equals(userList.getBayarId())) {
                    ivIcon.setImageDrawable(null);
                    ivIcon.setBackgroundResource(R.drawable.ic_app_done);
                    status.add("sudah");
                } else {
                    status.add("belum");
                }
            }

            NumberFormat format = NumberFormat.getCurrencyInstance();
            tvUsername.setText(name);
            tvJumlahZakat.setText(format.format(Integer.parseInt(userList.getBayarNominal())));
        }

        @Override
        public void onClick(View view) {
            Bayar userBayar = userListBayar.get(getAdapterPosition());

            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getId().equals(userBayar.getBayarUserId())) {
                    name = user.get(i).getUsername();
                    email = user.get(i).getEmail();
                    telp = user.get(i).getTelp();
                }
            }

            Intent intent = new Intent(view.getContext(), AlokasiZakatDetail.class);
            intent.putExtra("jumlahZakat", userBayar.getBayarNominal());
            intent.putExtra("username", name);
            intent.putExtra("email", email);
            intent.putExtra("telp", telp);
            intent.putExtra("idPembayaran", userBayar.getBayarId());
            intent.putExtra("jenisZakat", userBayar.getJenisZakat());
            intent.putExtra("tgl", userBayar.getBayarDate());
            intent.putExtra("idUser", userBayar.getBayarUserId());
            intent.putExtra("status", status.get(getAdapterPosition()));
            view.getContext().startActivity(intent);
        }
    }
}
