package com.samir.appligadajustica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.samir.appligadajustica.R;

public class HomeFragment extends Fragment {
    private TextView tvBemVindo, tvMsg1, tvMsg2;
    private ImageView fundo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvBemVindo = view.findViewById(R.id.textViewBemVindo);
        tvMsg1 = view.findViewById(R.id.textViewMsg1);
        tvMsg2 = view.findViewById(R.id.textViewMsg2);
        fundo = view.findViewById(R.id.imageViewFundo);

        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.inicio_anim);
        tvBemVindo.startAnimation(animation);
        tvMsg1.startAnimation(animation);
        tvMsg2.startAnimation(animation);
        fundo.startAnimation(animation);

        return view;
    }
}