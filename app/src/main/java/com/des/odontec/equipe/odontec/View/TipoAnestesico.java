package com.des.odontec.equipe.odontec.View;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.des.odontec.equipe.odontec.Controller.AnestesicoController;
import com.des.odontec.equipe.odontec.Model.Anestesico;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;


public class TipoAnestesico extends AppCompatActivity {
    private Spinner list;
    private String tipoPa;
    private String tipoAlt;
    private Button enviar;
    private int i=0;
    private ArrayList<String> lista;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_anestesico);
        list = (Spinner) findViewById(R.id.listaResultado);
        enviar=(Button) findViewById(R.id.btnEnviarAnestesico);

        lista = listaAnestesico();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);


        list.setAdapter(adapter);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bundle=new Bundle();
                bundle.putString("tipoAnestesico",lista.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TipoAnestesico.this,PesoPaciente.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }

    private ArrayList<String> listaAnestesico() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tipoPa = bundle.getString("tipo");
        tipoAlt = bundle.getString("alt");

        AnestesicoController anestesicoController = new AnestesicoController(TipoAnestesico.this);
        ArrayList<Anestesico> listaAnestesicos = anestesicoController.listarAnestesicos();
        String[] anestesicos = new String[listaAnestesicos.size()];

        for (Anestesico an : listaAnestesicos) {
            anestesicos[i] = an.getTipoAnestesico();
            i++;
        }

        ArrayList<String> listaAnes = new ArrayList<>();
        //daqui pra cima vai pro controller
        if (tipoPa.equals("Criança") && tipoAlt.equals("Norma Sistêmica ")) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[7]);
            listaAnes.add(anestesicos[4]);
        } else if ((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && (tipoAlt.equals("Diabete ") || tipoAlt.equals("Hipertensão "))) {
            listaAnes.add(anestesicos[7]);
        } else if ((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && tipoAlt.equals("Hipertireoidismo ")) {
            listaAnes.add(anestesicos[7]);
            listaAnes.add(anestesicos[6]);

        } else if ((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && (tipoAlt.equals("Anemia") || tipoAlt.equals("Asma"))) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[7]);
        } else if (tipoPa.equals("Adulto") && tipoAlt.equals("Norma Sistêmica ")) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[3]);
            listaAnes.add(anestesicos[7]);
            listaAnes.add(anestesicos[8]);
            listaAnes.add(anestesicos[5]);
            listaAnes.add(anestesicos[6]);
            listaAnes.add(anestesicos[1]);
            listaAnes.add(anestesicos[0]);
        } else if (tipoPa.equals("Idoso") && tipoAlt.equals("Norma Sistêmica ")) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[4]);
        }


        return listaAnes;
    }

}
