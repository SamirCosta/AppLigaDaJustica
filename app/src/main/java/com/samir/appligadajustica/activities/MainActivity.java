package com.samir.appligadajustica.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.samir.appligadajustica.R;
import com.samir.appligadajustica.classes.Equipamentos;
import com.samir.appligadajustica.fragments.EquipamentosFragment;
import com.samir.appligadajustica.fragments.HeroisFragment;
import com.samir.appligadajustica.fragments.HomeFragment;
import com.samir.appligadajustica.fragments.ViloesFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Equipamentos> equipamentos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavBottom();

        FragmentManager fragmentManager =   getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPagerBottom, new HomeFragment()).commit();
    }

    private void configNavBottom(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNav);
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(true);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        onClickItem(bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    private void onClickItem(BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        fragmentTransaction.replace(R.id.viewPagerBottom, new HomeFragment()).commit();
                        return true;
                    case R.id.ic_heroi:
                        fragmentTransaction.replace(R.id.viewPagerBottom, new HeroisFragment()).commit();
                        return true;
                    case R.id.ic_vilao:
                        fragmentTransaction.replace(R.id.viewPagerBottom, new ViloesFragment()).commit();
                        return true;
                    case R.id.ic_equip:
                        fragmentTransaction.replace(R.id.viewPagerBottom, new EquipamentosFragment()).commit();
                        return true;
                }
                return false;
            }
        });

    }
}