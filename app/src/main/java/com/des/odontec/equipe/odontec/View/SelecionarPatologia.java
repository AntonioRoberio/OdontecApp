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
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.PatologiaController;
import com.des.odontec.equipe.odontec.Controller.TratamentoController;
import com.des.odontec.equipe.odontec.Dao.PatologiaDao;
import com.des.odontec.equipe.odontec.Model.Patologia;
import com.des.odontec.equipe.odontec.Model.Tratamento;
import com.des.odontec.equipe.odontec.R;

public class SelecionarPatologia extends AppCompatActivity {
    private Spinner spinnerPatologia;
    private EditText editText;
    private Button button;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patologia);
        spinnerPatologia=(Spinner) findViewById(R.id.opcaoPatologia);
        editText=(EditText) findViewById(R.id.salvarPato);
        button=(Button) findViewById(R.id.btnEscolher);

        PatologiaController patologiaController=new PatologiaController(SelecionarPatologia.this);
        patologiaController.pegarDados();
        TratamentoController tratamentoController=new TratamentoController(SelecionarPatologia.this);
        tratamentoController.pegarDados();

        final String[] patologia=patologis();
        ArrayAdapter<String> pt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,patologia);
        spinnerPatologia.setAdapter(pt);

        spinnerPatologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bundle.putString("ptlg",patologia[position]);
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

    private String[] patologis(){
        String[] ptls;

        PatologiaController patologiaController=new PatologiaController(SelecionarPatologia.this);

        return  ptls=patologiaController.listarPatologias();
    }
}
