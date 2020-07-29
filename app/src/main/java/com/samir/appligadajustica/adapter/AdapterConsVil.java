package com.samir.appligadajustica.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.classes.Personagens;
import com.samir.appligadajustica.classes.Viloes;

import java.util.ArrayList;

public class AdapterConsVil extends RecyclerView.Adapter<AdapterConsVil.MyViewHolderVil> {
    ArrayList<Viloes> viloesArrayList;
    Context context;

    public AdapterConsVil(ArrayList<Viloes> viloesArrayList, Context context) {
        this.viloesArrayList = viloesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolderVil onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consulta, parent,false);
        return new AdapterConsVil.MyViewHolderVil(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderVil holder, int position) {
        Viloes viloes = viloesArrayList.get(position);
        holder.tvNomeVil.setText(viloes.getNome());
        holder.tvCodnom.setText(viloes.getCodinome());
    }

    @Override
    public int getItemCount() {
        return viloesArrayList.size();
    }

    public static class MyViewHolderVil extends RecyclerView.ViewHolder{
        public static TextView tvNomeVil, tvCodnom;

        public MyViewHolderVil(@NonNull View itemView) {
            super(itemView);
            tvNomeVil = itemView.findViewById(R.id.tvNomePers);
            tvCodnom = itemView.findViewById(R.id.tvCodnomPers);

        }
    }

}
