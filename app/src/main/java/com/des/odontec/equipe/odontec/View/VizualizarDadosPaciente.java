package com.des.odontec.equipe.odontec.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.PacienteController;
import com.des.odontec.equipe.odontec.Model.Paciente;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class VizualizarDadosPaciente extends AppCompatActivity {
    private TextView nome;
    private TextView idade;
    private TextView peso;
    private TextView sexo;
    private TextView dataDeAtendimento;
    private TextView alteracao;
    private TextView anestesico;
    private TextView qtdTubetes;
    private Button atualizarDados;
    private Button deletar;
    PacienteController pacienteController;
    Paciente paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizar_dados_paciente);
        nome=(TextView) findViewById(R.id.nomePass);
        idade=(TextView) findViewById(R.id.idadePass);
        peso=(TextView) findViewById(R.id.pesoPass);
        sexo=(TextView) findViewById(R.id.sexoPass);
        dataDeAtendimento=(TextView) findViewById(R.id.dataEtenPass);
        alteracao=(TextView) findViewById(R.id.alterPass);
        anestesico=(TextView) findViewById(R.id.anesPass);
        qtdTubetes=(TextView) findViewById(R.id.qntTubsPass);
        atualizarDados=(Button) findViewById(R.id.btnDadosAtualizar);
        deletar=(Button) findViewById(R.id.btnDltDados);

        pacienteController=new PacienteController(this);
        final ArrayList<Paciente> pacientes = pacienteController.listaPacientes();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        for(Paciente p: pacientes){
            if(p.getId() == bundle.getInt("id")){
                paciente=p;
                break;
            }
        }

        nome.setText(paciente.getNome());
        idade.setText(paciente.getIdade()+"");
        peso.setText(paciente.getPeso()+"");
        sexo.setText(paciente.getSexo());
        dataDeAtendimento.setText(paciente.getDataDeAtendimento());
        alteracao.setText(paciente.getAlteracao());
        anestesico.setText(paciente.getAnestesico());
        qtdTubetes.setText(paciente.getQtdTubetes()+"");
        atualizarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",paciente.getId());
                Intent intent=new Intent(VizualizarDadosPaciente.this,AlterarDadosPaciente.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,9);
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder conf=new AlertDialog.Builder(VizualizarDadosPaciente.this);
                conf.setTitle("Deletar?").setMessage("Deseja deletar os dados desse paciente?")
                        .setCancelable(true).setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pacienteController.deletar(paciente.getId());
                                Intent intent = new Intent(VizualizarDadosPaciente.this, ListaDePacientes.class);
                                startActivity(intent);
                                setResult(1,intent);
                                finish();
                            }
                        }).create().show();
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

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(VizualizarDadosPaciente.this, ListaDePacientes.class);
        startActivity(intent);
        setResult(1,intent);
        finish();
    }


}
