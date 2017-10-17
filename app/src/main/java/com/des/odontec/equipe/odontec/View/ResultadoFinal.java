package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.R;

public class ResultadoFinal extends AppCompatActivity {
    private TextView tipoPa;
    private TextView tipoAlt;
    private TextView tipoAnes;
    private TextView pesoPaciente;
    private TextView tubetes;
    private Intent intent;
    private Bundle valores;
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
                tipoAnes.setText("Anestésico Indicado: "+valores.get("tipoAnestesico").toString());
                pesoPaciente.setText("Peso do Paciente: "+valores.get("peso").toString());
            }
        }

        tubetes.setText("Quantidade de Tubetes: "+resutado());
    }

    private String resutado(){
        String rst="";

        String anestesico=valores.getString("tipoAnestesico").toString();
        char[] arrayAnestesico=anestesico.toCharArray();
        char caracteres=' ';
        String valor="";
        double peso=Double.parseDouble(valores.get("peso").toString());
        double mg;
        double dosagemMaxima;
        double resultado;

        for(int i=0;i<arrayAnestesico.length-1;i++){
            caracteres=arrayAnestesico[i];
            String n=String.valueOf(caracteres);
            if(n.matches("\\d")){
                valor+=n;
            }else if(n.equalsIgnoreCase(".")){
                valor+=n;
            }
        }

        mg=Double.parseDouble(valor);
        mg=(mg*10)*1.8;

        if(anestesico.contains("Articaína")){
            dosagemMaxima=7.0;
        }else if(anestesico.contains("Prilocaína")){
            dosagemMaxima=6.0;
        }else if(anestesico.contains("Bupivacaína")){
            dosagemMaxima=1.3;
        }else{
            dosagemMaxima=4.4;
        }
        resultado=(dosagemMaxima*peso)/mg;

        return rst=String.valueOf(Math.floor(resultado));
    }
}
