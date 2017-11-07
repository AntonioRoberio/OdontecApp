package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.Controller.ResultadoFinalController;
import com.des.odontec.equipe.odontec.Dao.PacienteDao;
import com.des.odontec.equipe.odontec.Model.Paciente;
import com.des.odontec.equipe.odontec.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultadoFinal extends AppCompatActivity {
    private TextView tipoPa;
    private TextView tipoAlt;
    private TextView tipoAnes;
    private TextView pesoPaciente;
    private TextView tubetes;
    private Intent intent;
    private Bundle valores;
    private String resultado;
    private double peso;
    private Paciente paciente;
    private TextView nome;
    private Button voltaInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultado_final);
        tipoPa = (TextView) findViewById(R.id.TipodePaciente);
        tipoAlt = (TextView) findViewById(R.id.alteracao);
        tipoAnes = (TextView) findViewById(R.id.anestesico);
        pesoPaciente = (TextView) findViewById(R.id.peso);
        tubetes = (TextView) findViewById(R.id.quantidade);
        nome = (TextView) findViewById(R.id.textnome);
        voltaInicio = (Button) findViewById(R.id.voltaInicio);


        voltaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoFinal.this, InicialActivity.class);
                startActivity(intent);
            }
        });


        paciente = new Paciente();
        intent = getIntent();

        if (intent != null) {
            valores = intent.getExtras();
            if (valores != null) {
                nome.setText("Paciente " + valores.get("nome").toString());
                tipoPa.setText(" (" + valores.get("tipo").toString() + ")");
                pesoPaciente.setText("Peso" + valores.get("peso").toString() + "Kg");
                tipoAlt.setText("Condições Sistêmica : " + valores.get("alt").toString());
                tipoAnes.setText("Anestésico escolhido: " + valores.get("tipoAnestesico").toString());


                paciente.setNome(valores.getString("nome"));
                paciente.setAlteracao(valores.get("alt").toString());
                paciente.setAnestesico(valores.get("tipoAnestesico").toString());
                paciente.setPeso(Double.parseDouble(valores.get("peso").toString()));
                paciente.setIdade(Integer.parseInt(valores.getString("idade")));
                paciente.setSexo(valores.getString("sexo"));

            }
        }

        ResultadoFinalController resultadoFinalController = new ResultadoFinalController();
        peso = Double.parseDouble(valores.get("peso").toString());
        resultado = resultadoFinalController.resutado(valores.get("tipoAnestesico").toString(), peso);

        tubetes.setText("Quantidade de Tubetes: " + resultado);
        paciente.setQtdTubetes(Double.parseDouble(resultado));
        Date dataAtual = new Date();
        SimpleDateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        paciente.setDataDeAtendimento(df.format(dataAtual));
        PacienteDao pacienteDao=new PacienteDao(this);
        pacienteDao.salvarPaciente(paciente);
        Intent intent = getIntent();

        setResult(1, intent);
    }

}
