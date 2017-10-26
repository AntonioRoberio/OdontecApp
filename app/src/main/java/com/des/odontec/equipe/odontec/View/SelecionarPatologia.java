package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.PatologiaController;
import com.des.odontec.equipe.odontec.Dao.PatologiaDao;
import com.des.odontec.equipe.odontec.Model.Patologia;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class SelecionarPatologia extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private Button salvar;
    private Bundle bundle;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patologia);
        listView=(ListView) findViewById(R.id.opcaoPatologia);
        button=(Button) findViewById(R.id.btnEscolher);
        salvar=(Button) findViewById(R.id.btnEscolher);

        PatologiaController patologiaController=new PatologiaController(SelecionarPatologia.this);
        patologiaController.pegarDados();

        ArrayList<String[]> tra=patologis();
        final String[] patologia=new String[tra.size()];
        final String[] ids=new String[tra.size()];
        for(String[] s:tra){
            patologia[i]=s[0];
            ids[i]=s[1];
            i++;
        }
        listView.setAdapter(new LayoutsAdpater(SelecionarPatologia.this,patologia));
        final View[] v = {new View(this)};
        bundle=new Bundle();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(v[0] != null){
                    v[0].setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.parseColor("#d3eef5"));
                bundle.putString("ptlg",patologia[position]);
                bundle.putString("id",ids[position]);
                v[0] =view;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle.containsKey("id")){

                    Intent intent=new Intent(SelecionarPatologia.this,SelecionarTratamento.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SelecionarPatologia.this, "Selecione uma opção", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private ArrayList<String[]> patologis(){
        String[] ptls;

        PatologiaController patologiaController=new PatologiaController(SelecionarPatologia.this);
        ArrayList<String[]> listaTratamento=patologiaController.listarPatologias();

        return listaTratamento;
    }

}
