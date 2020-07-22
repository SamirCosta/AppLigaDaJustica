package com.samir.appligadajustica.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.samir.appligadajustica.R;
import com.samir.appligadajustica.classes.Equipamentos;

import java.util.ArrayList;
import java.util.List;

public class EquipamentosFragment extends Fragment {
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipamentos, container, false);
        smartTabLayout = view.findViewById(R.id.viewPagerTab3);
        viewPager = view.findViewById(R.id.viewPager3);

        FragmentPagerAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(),
                FragmentPagerItems.with(getActivity())
                        .add("Cadastro", CadEquipFragment.class)
                        .add("Consulta", ConsEquipFragment.class)
                        .create()
        );
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);

        return view;
    }
}