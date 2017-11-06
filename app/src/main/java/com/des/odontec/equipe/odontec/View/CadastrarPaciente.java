package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Dao.PacienteDao;
import com.des.odontec.equipe.odontec.Model.Paciente;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class CadastrarPaciente extends AppCompatActivity {
    private EditText nome;
    private EditText idade;
    private EditText peso;
    private RadioGroup rbSexo;
    private RadioButton masculino;
    private RadioButton feminino;
    private Button button;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_paciente);
        nome = (EditText) findViewById(R.id.nomePaciente);
        idade = (EditText) findViewById(R.id.idadePaciente);
        peso = (EditText) findViewById(R.id.psPaciente);
        rbSexo = (RadioGroup) findViewById(R.id.gpSexo);
        masculino = (RadioButton) findViewById(R.id.rbM);
        feminino = (RadioButton) findViewById(R.id.rbF);
        button = (Button) findViewById(R.id.btPaci);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sexo = "";
                if (!(nome.getText().toString().isEmpty() || idade.getText().toString().isEmpty() || peso.getText().toString().isEmpty())) {
                    if (masculino.isChecked()) {
                        sexo = "Masculino";
                    } else {
                        sexo = "Femenino";
                    }
                    bundle=new Bundle();
                    bundle.putString("nome",nome.getText().toString());
                    bundle.putString("idade",idade.getText().toString());
                    bundle.putString("peso",peso.getText().toString());
                    bundle.putString("sexo",sexo);
                    Intent intent=new Intent(CadastrarPaciente.this,AlteracaoSistemica.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);

                } else {
                    Toast.makeText(CadastrarPaciente.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
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
