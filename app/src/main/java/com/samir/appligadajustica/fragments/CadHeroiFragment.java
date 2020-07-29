package com.samir.appligadajustica.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.samir.appligadajustica.R;
import com.samir.appligadajustica.RecyclerItemClickListener;
import com.samir.appligadajustica.activities.MainActivity;
import com.samir.appligadajustica.classes.Equipamentos;
import com.samir.appligadajustica.classes.Methods;
import com.samir.appligadajustica.classes.Personagens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.samir.appligadajustica.classes.Methods.clearFields;
import static com.samir.appligadajustica.classes.Methods.verify;

public class CadHeroiFragment extends Fragment {
    private RecyclerView recyclerView, recyclerEscolhidos;
    public ArrayList<Equipamentos> equipEscolhidos = new ArrayList<>();
    private AlertDialog dialog;
    private View view;
    private Button btnCad;
    private EditText nome, codinome, especie, habili, vul, nivel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cad_heroi, container, false);
        btnCad = view.findViewById(R.id.buttonCadHeroi);
        nome = view.findViewById(R.id.editNome);
        codinome = view.findViewById(R.id.editCodnom);
        especie = view.findViewById(R.id.editEspecie);
        habili = view.findViewById(R.id.editHabili);
        vul = view.findViewById(R.id.editVuln);
        nivel = view.findViewById(R.id.editNivelAcesso);

        cadastrar();

        Button btEquip = view.findViewById(R.id.buttonCadEquip);

        btEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("Escolha um equipamento");
                View mView = getLayoutInflater().inflate(R.layout.recycle_cad_equip, null);
                recyclerView = mView.findViewById(R.id.recyclerOrig);
                Methods.configRecycler(recyclerView, MainActivity.equipamentos, getActivity(), (byte) 1);
                itemClick();
                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();
            }
        });

        return view;
    }

    public void itemClick(){
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View viewRec, int position) {
                        adapter(position);
                        dialog.hide();
                        recyclerEscolhidos.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
    }

    public void adapter(int position){
        recyclerEscolhidos = view.findViewById(R.id.recyclerViewEscolha);
        Methods.configRecycler(recyclerEscolhidos, equipEscolhidos, getActivity(), (byte) 1);
        recyclerEscolhidos.setNestedScrollingEnabled(false);
        Equipamentos equipEsco = MainActivity.equipamentos.get(position);
        equipEscolhidos.add(equipEsco);
        Methods.swipe(recyclerEscolhidos, equipEscolhidos, getActivity(), true);
    }



    public void cadastrar(){
        btnCad.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final String strNome = nome.getText().toString();
                final String strCodnom = codinome.getText().toString();
                final String strEspecie = especie.getText().toString();
                final String strHabili = habili.getText().toString();
                final String strVuln = vul.getText().toString();
                final String strNivelAcess = nivel.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                if (!verify(nome,codinome,especie,habili,vul,nivel)) {

                    builder.setTitle("Confirmar cadastro");
                    builder.setMessage("Tem certeza que deseja cadastrar este equipamento?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Personagens pers = new Personagens();
                            ArrayList<Equipamentos> equip2 = new ArrayList<>();
                            equip2.addAll(equipEscolhidos);
                            pers.setNome(strNome);
                            pers.setCodinome(strCodnom);
                            pers.setEspecie(strEspecie);
                            pers.setHabilidades(strHabili);
                            pers.setVulnerabilidades(strVuln);
                            pers.setNivelAcesso(strNivelAcess);
                            pers.setEquipamentos(equip2);
                            ConsHeroiFragment.personagens.add(pers);
                            ConsHeroiFragment.recyclerViewCons.getAdapter().notifyDataSetChanged();
                            clearFields(nome,codinome,especie,habili,vul,nivel);
                            clearRecycler();
                        }
                    }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create();
                    builder.show();

                }else {
                    Snackbar.make(view, "Preencha todos os campos",Snackbar.LENGTH_LONG).show();
                }



            }
        });
    }

    private void clearRecycler() {
        if (equipEscolhidos.size() > 0) {
            equipEscolhidos.clear();
            recyclerEscolhidos.getAdapter().notifyDataSetChanged();
        }
    }

}