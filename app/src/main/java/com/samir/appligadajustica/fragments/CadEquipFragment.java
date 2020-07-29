package com.samir.appligadajustica.fragments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.samir.appligadajustica.R;
import com.samir.appligadajustica.activities.MainActivity;
import com.samir.appligadajustica.classes.Equipamentos;

import static com.samir.appligadajustica.classes.Methods.clearFields;
import static com.samir.appligadajustica.classes.Methods.verify;


public class CadEquipFragment extends Fragment {
    private Button btnCad;
    private ImageView bat;
    private EditText nome, descricao, finalidade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cad_equip, container, false);
        nome = view.findViewById(R.id.editNameEquip);
        descricao = view.findViewById(R.id.editDescrEquip);
        finalidade = view.findViewById(R.id.editFinali);

        bat = view.findViewById(R.id.imageViewBat);
        bat.setVisibility(View.VISIBLE);

        btnCad = view.findViewById(R.id.buttonCadEquip);
        bat.setVisibility(View.INVISIBLE);
        btnCad.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final String strNome = nome.getText().toString();
                final String strDescr = descricao.getText().toString();
                final String strFinal = finalidade.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                if (!verify(nome,descricao,finalidade)) {

                    builder.setTitle("Confirmar cadastro");
                    builder.setMessage("Tem certeza que deseja cadastrar este equipamento?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Equipamentos equi = new Equipamentos();
                            equi.setNomeEquip(strNome);
                            equi.setDescr(strDescr);
                            equi.setFinalidade(strFinal);
                            MainActivity.equipamentos.add(equi);
                            ConsEquipFragment.recyclerView.getAdapter().notifyDataSetChanged();
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.batman_logo);
                            bat.startAnimation(animation);
                            clearFields(nome,descricao,finalidade);
                        }
                    }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create();
                    builder.show();

                }else {

                    Snackbar.make(v, "Preencha todos os campos",Snackbar.LENGTH_LONG).show();

                }


            }
        });
        return view;
    }

}