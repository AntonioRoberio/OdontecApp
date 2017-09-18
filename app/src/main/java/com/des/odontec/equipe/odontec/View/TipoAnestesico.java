package com.des.odontec.equipe.odontec.View;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;


public class TipoAnestesico extends AppCompatActivity {
    private Spinner list;
    private String tipoPa;
    private String tipoAlt;
    private ArrayList<String> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_anestesico);
        list=(Spinner) findViewById(R.id.listaResultado);

        lista=listaAnestesico();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);

        list.setAdapter(adapter);



    }

    private ArrayList<String> listaAnestesico(){

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tipoPa=bundle.getString("tipo");
        tipoAlt=bundle.getString("alt");

        String[] anestesicos={"Lidocaína 2% (com adrenalina)","Lidocaína 2% (Sem vasoconstritor)","Prilocaína 3% (com felipressina)",
                "Mepivacaína 2% (com vasoconstritor)","Mepivacaína 3% (sem vasoconstritor)","Mepivacaína 2% (com adrelanina)",
                "Bupivacaína 0.5%","Articaína 4% (com adrenalina)","Prilocaína 4%"};

        ArrayList<String> listaAnes=new ArrayList<>();

        if(tipoPa.equals("Criança") && tipoAlt.equals("Norma Sistêmica")){
            listaAnes.add(anestesicos[0]);
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[5]);
       }else if((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && (tipoAlt.equals("diabete") || tipoAlt.equals("hipertensão"))){
            listaAnes.add(anestesicos[2]);
        }else if((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && tipoAlt.equals("hiperteriodismo")){
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[4]);

        }else if((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && (tipoAlt.equals("anemia") || tipoAlt.equals("asma"))){
            listaAnes.add(anestesicos[0]);
            listaAnes.add(anestesicos[2]);
        }else if(tipoPa.equals("Adulto") && tipoAlt.equals("Norma Sistêmica")){
            listaAnes.add(anestesicos[0]);
            listaAnes.add(anestesicos[1]);
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[8]);
            listaAnes.add(anestesicos[3]);
            listaAnes.add(anestesicos[4]);
            listaAnes.add(anestesicos[6]);
            listaAnes.add(anestesicos[7]);
        }else if(tipoPa.equals("Idoso") && tipoAlt.equals("Norma Sistêmica")){
            listaAnes.add(anestesicos[0]);
            listaAnes.add(anestesicos[5]);
        }


        return listaAnes;
    }

}