package com.samir.appligadajustica.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.RecyclerItemClickListener;
import com.samir.appligadajustica.activities.InfoActivity;
import com.samir.appligadajustica.adapter.AdapterCons;
import com.samir.appligadajustica.adapter.AdapterEquip;
import com.samir.appligadajustica.classes.Methods;
import com.samir.appligadajustica.classes.Personagens;

import java.util.ArrayList;

public class ConsHeroiFragment extends Fragment {
    public static RecyclerView recyclerViewCons;
    public static ArrayList<Personagens> personagens = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cons_heroi, container, false);
        recyclerViewCons = view.findViewById(R.id.recyclerConsHeroi);

        Methods.configRecycler(recyclerViewCons,personagens,getActivity(), (byte) 2);
        Methods.swipe(recyclerViewCons,personagens,getActivity(), false);

        recyclerViewCons.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),
                recyclerViewCons,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), InfoActivity.class);
                        intent.putExtra("posicao", position);
                        intent.putExtra("personagens", 1);
                        startActivity(intent);
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