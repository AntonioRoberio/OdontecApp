package com.des.odontec.equipe.odontec.View;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.R;

public class AtualizarDados extends AppCompatActivity implements Runnable{
   private EditText nome;
    private String[] s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_dados);
        nome=(EditText) findViewById(R.id.pegarNome);
        UsuarioController usuarioController=new UsuarioController();
        usuarioController.pegarDados(AtualizarDados.this);
        s=usuarioController.renoDados(AtualizarDados.this);
        nome.setText(s[0].toString());
        boolean teste=nome.getText().toString().isEmpty();
        if(teste){
            Handler handler=new Handler();
            handler.postDelayed(this,10000);
        }




    }

    @Override
    public void run() {
        UsuarioController usuarioController=new UsuarioController();
        s=usuarioController.renoDados(AtualizarDados.this);
        nome.setText(s[0].toString());
    }
}
