package com.samir.appligadajustica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.activities.MainActivity;
import com.samir.appligadajustica.classes.Methods;

public class ConsEquipFragment extends Fragment {
    public static RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cons_equip, container, false);
        recyclerView = view.findViewById(R.id.recyclerConsEquip);

        Methods.configRecycler(recyclerView, MainActivity.equipamentos, getActivity(), (byte) 1);
        Methods.swipe(recyclerView, MainActivity.equipamentos, getActivity(), false);

        return view;
    }
}