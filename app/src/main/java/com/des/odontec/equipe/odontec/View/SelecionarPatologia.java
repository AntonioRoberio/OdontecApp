package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.des.odontec.equipe.odontec.Controller.PatologiaController;
import com.des.odontec.equipe.odontec.Dao.PatologiaDao;
import com.des.odontec.equipe.odontec.Model.Patologia;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class SelecionarPatologia extends AppCompatActivity {
    private Spinner spinnerPatologia;
    private EditText patologias;
    private EditText tratamento;
    private Button button;
    private Button salvar;
    private Bundle bundle;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patologia);
        spinnerPatologia=(Spinner) findViewById(R.id.opcaoPatologia);
        patologias=(EditText) findViewById(R.id.salvarPato);
        tratamento=(EditText) findViewById(R.id.salvarTratamento);
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


        ArrayAdapter<String> pt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,patologia);
        spinnerPatologia.setAdapter(pt);
        bundle=new Bundle();
        spinnerPatologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               bundle.putString("ptlg",patologia[position]);
                bundle.putString("id",ids[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelecionarPatologia.this,SelecionarTratamento.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
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
