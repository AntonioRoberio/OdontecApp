package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.AlteracaoController;
import com.des.odontec.equipe.odontec.Controller.PacienteController;
import com.des.odontec.equipe.odontec.Dao.PacienteDao;
import com.des.odontec.equipe.odontec.Model.Alteracao;
import com.des.odontec.equipe.odontec.Model.Paciente;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class AlterarDadosPaciente extends AppCompatActivity {
private EditText nome;
    private EditText idade;
    private EditText peso;
    private Button btnAtl;
    private PacienteController pacienteController;
    private AlteracaoController alteracaoController;
    private Paciente paciente;
    private Bundle bnd;
    private Spinner spinner;
    private int i=0;
    private String alt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados_paciente);
        nome=(EditText) findViewById(R.id.nomePacienteEdt);
        idade=(EditText) findViewById(R.id.idadePacienteEdt);
        peso=(EditText) findViewById(R.id.psPacienteEdt);
        btnAtl=(Button) findViewById(R.id.btnSalvarEdt);
        spinner=(Spinner) findViewById(R.id.spnAlter);
        pacienteController=new PacienteController(this);
        alteracaoController=new AlteracaoController(this);
        final String[] alteracaos = alteracaoController.listarAlteracoes();
        final ArrayList<Paciente> pacientes = pacienteController.listaPacientes();



        Intent intent=getIntent();
        final Bundle bundle=intent.getExtras();
        for(Paciente p: pacientes){
            if(p.getId() == bundle.getInt("id")){
               paciente=p;
           }
        }
        String atual=alteracaos[0];
        for(int i=0;i<alteracaos.length-1;i++){
            if(alteracaos[i].equalsIgnoreCase(paciente.getAlteracao())){
                if (paciente.getAlteracao().equals(alteracaos[0])) {
                    break;
                } else {
                    alteracaos[0] = paciente.getAlteracao();
                    alteracaos[i] = atual;
                }
            }
        }


        nome.setText(paciente.getNome());
        idade.setText(paciente.getIdade()+"");
        peso.setText(paciente.getPeso()+"");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alteracaos);
        spinner.setAdapter(arrayAdapter);

        btnAtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(nome.getText().toString().isEmpty() || idade.getText().toString().isEmpty() || peso.getText().toString().isEmpty())){
                    paciente.setNome(nome.getText().toString());
                    paciente.setPeso(Double.parseDouble(peso.getText().toString()));
                    paciente.setIdade(Integer.parseInt(idade.getText().toString()));
                    PacienteDao pacienteDao=new PacienteDao(AlterarDadosPaciente.this);
                    pacienteDao.atualizarDadosPaciente(paciente);
                    finish();
                    Intent intent=new Intent(AlterarDadosPaciente.this, InicialActivity.class);
                    bnd=new Bundle();
                    //bnd.putString("nome",nome.getText().toString());
                    //bnd.putString("idade",idade.getText().toString());
                    //bnd.putString("peso",peso.getText().toString());
                    //bnd.putString("sexo",paciente.getSexo());
                    //intent.putExtras(bundle);
                    startActivityForResult(intent,11);
                }else{
                    Toast.makeText(AlterarDadosPaciente.this, "Campos em branco não são permitidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
