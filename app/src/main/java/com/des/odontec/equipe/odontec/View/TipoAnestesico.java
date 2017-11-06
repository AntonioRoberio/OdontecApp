package com.des.odontec.equipe.odontec.View;


import android.content.Intent;
import android.graphics.Color;
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
    private ListView list;
    private String tipoPa;
    private String tipoAlt;
    private Button enviar;
    private String[] lista;
    private Bundle bundle;
    private Bundle valores;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_anestesico);
        list = (ListView) findViewById(R.id.listaResultado);
        enviar=(Button) findViewById(R.id.btnEnviarAnestesico);
        bundle=new Bundle();
        intent = getIntent();
        if(intent != null){
            valores = intent.getExtras();
            if(valores!=null){
                tipoPa = valores.getString("tipo");
                tipoAlt = valores.getString("alt");
                bundle.putString("nome",valores.getString("nome"));
                bundle.putString("idade",valores.getString("idade"));
                bundle.putString("peso",valores.getString("peso"));
                bundle.putString("sexo",valores.getString("sexo"));
            }else{
                tipoPa = "sem valor";
                tipoAlt = "sem valor";
            }
        }

        AnestesicoController anestesicoController=new AnestesicoController(this);
        lista = anestesicoController.listaAnestesico(tipoPa,tipoAlt);
        list.setAdapter(new LayoutsAdpater(TipoAnestesico.this,lista));
        final View[] v = {new View(this)};

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (v[0] != null) {
                    v[0].setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.parseColor("#d3eef5"));
                bundle.putString("tipoAnestesico", lista[position]);
                v[0] = view;
            }
        });


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(bundle.containsKey("tipoAnestesico")){
                   Intent intent=new Intent(TipoAnestesico.this,ResultadoFinal.class);
                   bundle.putString("alt",tipoAlt);
                   bundle.putString("tipo",tipoPa);
                   intent.putExtras(bundle);
                   startActivityForResult(intent,3);
               }else{
                   Toast.makeText(TipoAnestesico.this, "Selecione uma opção", Toast.LENGTH_LONG).show();
               }

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1){
            setResult(1,data);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
