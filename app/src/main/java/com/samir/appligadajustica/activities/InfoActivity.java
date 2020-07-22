package com.samir.appligadajustica.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samir.appligadajustica.R;
import com.samir.appligadajustica.classes.Methods;
import com.samir.appligadajustica.classes.Personagens;
import com.samir.appligadajustica.classes.Viloes;
import com.samir.appligadajustica.fragments.ConsHeroiFragment;
import com.samir.appligadajustica.fragments.ConsViloesFragment;

import java.text.SimpleDateFormat;

public class InfoActivity extends AppCompatActivity {
    private TextView tvNome, tvCodnom, tvEspecie, tvHabi, tvVuln, tvNivel, tvHeroiRival, tvEsconderijo, tvDataAtaque, tvEquip;
    private RecyclerView recyclerViewInfo;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tvNome = findViewById(R.id.tvNomeInfo);
        tvCodnom = findViewById(R.id.tvCodinomeInfo);
        tvEspecie = findViewById(R.id.tvEspecieInfo);
        tvHabi = findViewById(R.id.tvHabilIndo);
        tvVuln = findViewById(R.id.tvVulnInfo);
        tvNivel = findViewById(R.id.tvNivelAcessoInfo);
        tvHeroiRival = findViewById(R.id.tvHeroisRivaisInfo);
        tvEsconderijo = findViewById(R.id.tvEsconderijoInfo);
        tvDataAtaque = findViewById(R.id.tvDataAtaqueInfo);
        tvEquip = findViewById(R.id.tvEquipInfo);
        recyclerViewInfo = findViewById(R.id.recyclerInfo);

        tvNivel.setVisibility(View.GONE);
        tvHeroiRival.setVisibility(View.GONE);
        tvEsconderijo.setVisibility(View.GONE);
        tvDataAtaque.setVisibility(View.GONE);
        tvEquip.setVisibility(View.GONE);
        recyclerViewInfo.setVisibility(View.GONE);

        bundle = getIntent().getExtras();
        int activity = bundle.getInt("personagens");
        if (activity == 1){
            mostraPers();
        }
        if(activity == 2){
            mostraVilao();
        }
    }

    public void mostraPers(){
        int pos = bundle.getInt("posicao");
        Personagens personagens = ConsHeroiFragment.personagens.get(pos);
        tvNome.setText(personagens.getNome());
        tvCodnom.setText("Codinome: "+personagens.getCodinome());
        tvEspecie.setText("Espécie: "+personagens.getEspecie());
        tvHabi.setText("Habilidades: "+personagens.getHabilidades());
        tvVuln.setText("Vulnerabilidades: "+personagens.getVulnerabilidades());
        tvNivel.setText("Nível de acesso: "+personagens.getNivelAcesso());
        Methods.configRecycler(recyclerViewInfo,personagens.getEquipamentos(),getApplicationContext(), (byte) 1);

        tvNivel.setVisibility(View.VISIBLE);
        if(personagens.getEquipamentos().size() > 0)tvEquip.setVisibility(View.VISIBLE);
        recyclerViewInfo.setVisibility(View.VISIBLE);
    }

    public void mostraVilao(){
        int pos = bundle.getInt("posVila");
        Viloes viloes = ConsViloesFragment.viloes.get(pos);
        tvNome.setText(viloes.getNome());
        tvCodnom.setText("Codinome: "+ viloes.getCodinome());
        tvEspecie.setText("Espécie: "+ viloes.getEspecie());
        tvHabi.setText("Habilidades: "+ viloes.getHabilidades());
        tvVuln.setText("Vulnerabilidades: "+ viloes.getVulnerabilidades());
        tvHeroiRival.setText("Herói Rival: "+ viloes.getHeroisRivais());
        tvEsconderijo.setText("Esconderijo: "+ viloes.getEsconderijos());
        tvDataAtaque.setText("Data de ataque: "+ new SimpleDateFormat("dd/MM/yyyy").format(viloes.getDataAtaques()));
        Methods.configRecycler(recyclerViewInfo,viloes.getEquipamentos(),getApplicationContext(), (byte) 1);

        tvHeroiRival.setVisibility(View.VISIBLE);
        tvEsconderijo.setVisibility(View.VISIBLE);
        tvDataAtaque.setVisibility(View.VISIBLE);
        if(viloes.getEquipamentos().size() > 0)tvEquip.setVisibility(View.VISIBLE);
        recyclerViewInfo.setVisibility(View.VISIBLE);

    }

}