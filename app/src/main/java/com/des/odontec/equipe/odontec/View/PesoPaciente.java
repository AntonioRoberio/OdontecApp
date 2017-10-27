package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.R;

public class PesoPaciente extends AppCompatActivity {
    private String tipoPa;
    private String tipoAlt;
    private String tipoAnes;
    private EditText peso;
    private TextView nomeAnestesico;
    private Button envairPeso;
    private Bundle bundle;
    private Intent intent;
    private Bundle valores;
    private static final int RESULT_CLOSE_ALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso_paciente);
        peso=(EditText) findViewById(R.id.pesoPaciente);
        nomeAnestesico=(TextView) findViewById(R.id.anestesicoEscolhido);
        envairPeso=(Button) findViewById(R.id.btnPeso);

        intent = getIntent();
        if(intent != null){
            valores = intent.getExtras();
            if(valores!=null){
                tipoPa = valores.getString("tipo");
                tipoAlt = valores.getString("alt");
                tipoAnes=valores.getString("tipoAnestesico");
            }else{
                tipoPa = "sem valor";
                tipoAlt = "sem valor";
                tipoAnes= "sem valor";
            }
        }

        envairPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!peso.getText().toString().isEmpty()){
                    bundle=new Bundle();
                    bundle.putString("tipo",tipoPa);
                    bundle.putString("alt",tipoAlt);
                    bundle.putString("tipoAnestesico",tipoAnes);
                    bundle.putString("peso",peso.getText().toString());
                    Intent intent=new Intent(PesoPaciente.this,ResultadoFinal.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,RESULT_CLOSE_ALL);

                }else{
                    Toast.makeText(PesoPaciente.this,"Informe o peso do paciente.",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RESULT_CLOSE_ALL && resultCode==1){
            setResult(1,data);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
