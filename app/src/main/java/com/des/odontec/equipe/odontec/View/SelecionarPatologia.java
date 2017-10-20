package com.des.odontec.equipe.odontec.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.PatologiaController;
import com.des.odontec.equipe.odontec.Dao.PatologiaDao;
import com.des.odontec.equipe.odontec.Model.Patologia;
import com.des.odontec.equipe.odontec.R;

public class SelecionarPatologia extends AppCompatActivity {
    private Spinner spinnerPatologia;
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patologia);
        spinnerPatologia=(Spinner) findViewById(R.id.opcaoPatologia);
        editText=(EditText) findViewById(R.id.salvarPato);
        button=(Button) findViewById(R.id.btnSalvarPtl);

        PatologiaController patologiaController=new PatologiaController(SelecionarPatologia.this);
        patologiaController.pegarDados();

       ArrayAdapter<String> pt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,patologis());
        spinnerPatologia.setAdapter(pt);

        //--------------------------------------------------------------------------
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patologia patologia=new Patologia();
                patologia.setTipoPatologia(editText.getText().toString());
                patologia.setId("Odontec_Patologia_"+editText.getText().toString());
                PatologiaDao patologiaDao=new PatologiaDao(SelecionarPatologia.this);
                patologiaDao.salvarBD(patologia);
                editText.setText("");
            }
        });


    }

    private String[] patologis(){
        String[] ptls;

        PatologiaController patologiaController=new PatologiaController(SelecionarPatologia.this);

        return  ptls=patologiaController.listarPatologias();
    }
}
