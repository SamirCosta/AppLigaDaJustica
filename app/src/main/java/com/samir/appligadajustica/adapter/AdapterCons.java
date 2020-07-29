package com.samir.appligadajustica.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.classes.Equipamentos;
import com.samir.appligadajustica.classes.Personagens;

import java.util.ArrayList;

public class AdapterCons extends RecyclerView.Adapter<AdapterCons.MyViewHolder> {
    ArrayList<Personagens> personagensArrayList;
    Context context;

    public AdapterCons(ArrayList<Personagens> personagensArrayList, Context context) {
        this.personagensArrayList = personagensArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consulta, parent,false);
        return new AdapterCons.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Personagens personagens = personagensArrayList.get(position);
        Log.i("ADAPTER", personagens.getNome());
        holder.tvNome.setText(personagens.getNome());
        holder.tvCodnom.setText(personagens.getCodinome());
    }

    @Override
    public int getItemCount() {
        return personagensArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public static TextView tvNome, tvCodnom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomePers);
            tvCodnom = itemView.findViewById(R.id.tvCodnomPers);

        }
    }

}
