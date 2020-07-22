package com.samir.appligadajustica.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.classes.Equipamentos;

import java.util.ArrayList;

public class AdapterEquip extends RecyclerView.Adapter<AdapterEquip.MyViewHolder> {
    ArrayList<Equipamentos> equipa;
    Context c;

    public AdapterEquip(ArrayList<Equipamentos> equipam, Context context) {
        this.equipa = equipam;
        this.c = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_equip, parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("AQUI","AQUIIIII");
        Equipamentos equipamentos = equipa.get(position);

        holder.tvNome.setText(equipamentos.getNomeEquip());
        holder.tvDescr.setText("Descrição: " + equipamentos.getDescr());
        holder.tvFinal.setText("Finalidade: " + equipamentos.getFinalidade());
        Log.i("TEXTVIEW", holder.tvFinal.getText().toString());
    }

    @Override
    public int getItemCount() {

        return equipa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNome, tvDescr, tvFinal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i("Layout",""+itemView);
            tvNome = itemView.findViewById(R.id.textViewNomeEquipRecycler);
            tvDescr = itemView.findViewById(R.id.textViewDescrRecycler);
            tvFinal = itemView.findViewById(R.id.textViewFinalRecycler);

        }
    }

}
