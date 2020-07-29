package com.samir.appligadajustica.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.RecyclerItemClickListener;
import com.samir.appligadajustica.activities.InfoActivity;
import com.samir.appligadajustica.adapter.AdapterCons;
import com.samir.appligadajustica.adapter.AdapterConsVil;
import com.samir.appligadajustica.classes.Methods;
import com.samir.appligadajustica.classes.Personagens;
import com.samir.appligadajustica.classes.Viloes;

import java.util.ArrayList;

public class ConsViloesFragment extends Fragment {
    public static ArrayList<Viloes> viloes = new ArrayList<>();
    public static RecyclerView recyclerViewConsVilao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cons_viloes, container, false);
        recyclerViewConsVilao = view.findViewById(R.id.recyclerConsVilao);

        Methods.configRecycler(recyclerViewConsVilao,viloes,getActivity(), (byte) 3);
        Methods.swipe(recyclerViewConsVilao,viloes,getActivity(), false);

        recyclerViewConsVilao.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),
                recyclerViewConsVilao,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), InfoActivity.class);
                        intent.putExtra("posVila", position);
                        intent.putExtra("personagens", 2);
                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), AdapterConsVil.MyViewHolderVil.tvNomeVil, ViewCompat.getTransitionName(AdapterConsVil.MyViewHolderVil.tvNomeVil));
                        startActivity(intent, optionsCompat.toBundle());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));

        return view;
    }
}