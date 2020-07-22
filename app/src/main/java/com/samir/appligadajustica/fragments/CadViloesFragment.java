package com.samir.appligadajustica.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.samir.appligadajustica.R;
import com.samir.appligadajustica.RecyclerItemClickListener;
import com.samir.appligadajustica.activities.MainActivity;
import com.samir.appligadajustica.classes.Equipamentos;
import com.samir.appligadajustica.classes.Methods;
import com.samir.appligadajustica.classes.Personagens;
import com.samir.appligadajustica.classes.Viloes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CadViloesFragment extends Fragment {
    private EditText nome, codinome, especie, habili, vul, heroiRival, esconderijo, editText;
    public ArrayList<Equipamentos> equipEscolhidos = new ArrayList<>();
    private Button btnCadEquip, btnCadVilao;
    private RecyclerView recyclerView, recyclerEscolhidos;
    private AlertDialog dialog;
    private String data;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cad_viloes, container, false);
        nome = view.findViewById(R.id.editNome);
        codinome = view.findViewById(R.id.editCodnom);
        especie = view.findViewById(R.id.editEspecie);
        habili = view.findViewById(R.id.editHabili);
        vul = view.findViewById(R.id.editVuln);
        heroiRival = view.findViewById(R.id.editHeoriRival);
        esconderijo = view.findViewById(R.id.editEsconderijo);
        editText = view.findViewById(R.id.editTextDate);

        btnCadEquip = view.findViewById(R.id.buttonCadEquip);
        btnCadVilao = view.findViewById(R.id.buttonCadVilao);
        recyclerEscolhidos = view.findViewById(R.id.recyclerViewEscolha);

        cadastrar();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("CLIQUE", "CLICOU");
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("Escolha uma data");
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.i("OK", "CLICOU NO OK");
                        if(data != null) {
                            editText.setText(data);
                        }
                    }
                });
                View mView = getLayoutInflater().inflate(R.layout.date_dialog, null);
                CalendarView calendarView = mView.findViewById(R.id.calendarView);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, final int year, final int month, final int dayOfMonth) {
                        //Log.i("CALENDARIO", "CLICOU");
                        int mes = month+1;
                        data = dayOfMonth+"/"+mes+"/"+year;
                    }
                });

            }
        });

        btnCadEquip.setOnClickListener(new View.OnClickListener() {
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
        btnCadVilao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strNome = nome.getText().toString();
                final String strCodnom = codinome.getText().toString();
                final String strEspecie = especie.getText().toString();
                final String strHabili = habili.getText().toString();
                final String strVuln = vul.getText().toString();
                final String strHeroiRival = heroiRival.getText().toString();
                final String strEsconderijo = esconderijo.getText().toString();
                final String strData = editText.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                if (!strNome.isEmpty() && !strCodnom.isEmpty() && !strEspecie.isEmpty() && !strHabili.isEmpty() && !strVuln.isEmpty() && !strHeroiRival.isEmpty() && !strEsconderijo.isEmpty() && !strData.isEmpty())
                {

                    builder.setTitle("Confirmar cadastro");
                    builder.setMessage("Tem certeza que deseja cadastrar este equipamento?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Viloes viloes = new Viloes();
                                ArrayList<Equipamentos> equip2 = new ArrayList<>();
                                equip2.addAll(equipEscolhidos);
                                viloes.setNome(strNome);
                                viloes.setCodinome(strCodnom);
                                viloes.setEspecie(strEspecie);
                                viloes.setHabilidades(strHabili);
                                viloes.setVulnerabilidades(strVuln);
                                viloes.setHeroisRivais(strHeroiRival);
                                viloes.setEsconderijos(strEsconderijo);
                                viloes.setDataAtaques(new SimpleDateFormat("dd/MM/yyyy").parse(strData));
                                viloes.setEquipamentos(equip2);
                                ConsViloesFragment.viloes.add(viloes);
                                ConsViloesFragment.recyclerViewConsVilao.getAdapter().notifyDataSetChanged();
                                clear();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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

    private void clear() {

        nome.setText("");
        codinome.setText("");
        especie.setText("");
        habili.setText("");
        vul.setText("");
        heroiRival.setText("");
        esconderijo.setText("");
        editText.setText("");
        equipEscolhidos.clear();
        recyclerEscolhidos.getAdapter().notifyDataSetChanged();

    }

}