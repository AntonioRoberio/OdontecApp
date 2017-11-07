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
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados_paciente);
        nome = (EditText) findViewById(R.id.nomePacienteEdt);
        idade = (EditText) findViewById(R.id.idadePacienteEdt);
        peso = (EditText) findViewById(R.id.psPacienteEdt);
        btnAtl = (Button) findViewById(R.id.btnSalvarEd);
        spinner = (Spinner) findViewById(R.id.spnAlter);
        pacienteController = new PacienteController(this);
        alteracaoController = new AlteracaoController(this);
        String[] alteracaos = alteracaoController.listarAlteracoes();
        final ArrayList<Paciente> pacientes = pacienteController.listaPacientes();


        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        for (Paciente p : pacientes) {
            if (p.getId() == bundle.getInt("id")) {
                paciente = p;
            }
        }
        String atual = alteracaos[0];
        if (!paciente.getAlteracao().equals(alteracaos[0])) {
            for (int i = 1; i < alteracaos.length - 1; i++) {
                if (!alteracaos[i].equals(paciente.getAlteracao())) {
                    alteracaos[0] = paciente.getAlteracao();
                    j++;
                    break;
                }
           }
        }
        //alteracaos[j]="auhauaha";


        nome.setText(paciente.getNome());
        idade.setText(paciente.getIdade() + "");
        peso.setText(paciente.getPeso() + "");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alteracaos);
        spinner.setAdapter(arrayAdapter);

        btnAtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(nome.getText().toString().isEmpty() || idade.getText().toString().isEmpty() || peso.getText().toString().isEmpty())) {
                    paciente.setNome(nome.getText().toString());
                    paciente.setPeso(Double.parseDouble(peso.getText().toString()));
                    paciente.setIdade(Integer.parseInt(idade.getText().toString()));
                    paciente.setAlteracao(spinner.getSelectedItem().toString());
                    PacienteDao pacienteDao = new PacienteDao(AlterarDadosPaciente.this);
                    pacienteDao.atualizarDadosPaciente(paciente);
                    Intent intent = new Intent(AlterarDadosPaciente.this, TipoAnestesico.class);
                    bnd = new Bundle();
                    bnd.putInt("id", paciente.getId());
                    if (paciente.getIdade() <= 14) {
                        bnd.putString("tipo", "Criança");
                    } else if (paciente.getIdade() >= 15 || paciente.getIdade() <= 64) {
                        bnd.putString("tipo", "Adulto");
                    } else {
                        bnd.putString("tipo", "Idoso");
                    }
                    bnd.putString("alt", paciente.getAlteracao());
                    intent.putExtras(bnd);
                    startActivityForResult(intent, 2);
                } else {
                    Toast.makeText(AlterarDadosPaciente.this, "Campos em branco não são permitidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
