package com.samir.appligadajustica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.samir.appligadajustica.R;


public class HeroisFragment extends Fragment {
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_herois, container, false);
        smartTabLayout = view.findViewById(R.id.viewPagerTab);
        viewPager = view.findViewById(R.id.viewPager);

        FragmentPagerAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(),
                FragmentPagerItems.with(getActivity())
                .add("Cadastro", CadHeroiFragment.class)
                .add("Consulta", ConsHeroiFragment.class)
                .create()
        );
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);

        return view;

    }



}