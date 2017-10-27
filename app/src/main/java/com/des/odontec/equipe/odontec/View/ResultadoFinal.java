package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.Controller.ResultadoFinalController;
import com.des.odontec.equipe.odontec.R;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);
        tipoPa=(TextView) findViewById(R.id.TipodePaciente);
        tipoAlt=(TextView) findViewById(R.id.alteracao);
        tipoAnes=(TextView) findViewById(R.id.anestesico);
        pesoPaciente=(TextView) findViewById(R.id.peso);
        tubetes=(TextView) findViewById(R.id.quantidade);

        intent = getIntent();
        if(intent != null){
            valores = intent.getExtras();
            if(valores!=null){
                tipoPa.setText("Tipo de Paciente: "+valores.get("tipo").toString());
                tipoAlt.setText("Alteração Sistemica: "+valores.get("alt").toString());
                tipoAnes.setText("Anestésico Selecionado: "+valores.get("tipoAnestesico").toString());
                pesoPaciente.setText("Peso do Paciente: "+valores.get("peso").toString());
            }
        }

        ResultadoFinalController resultadoFinalController=new ResultadoFinalController();
        peso=Double.parseDouble(valores.get("peso").toString());
        resultado=resultadoFinalController.resutado(valores.get("tipoAnestesico").toString(),peso);

        tubetes.setText("Quantidade de Tubetes: "+resultado);

        Intent intent=getIntent();

        setResult(1,intent);
    }

}
