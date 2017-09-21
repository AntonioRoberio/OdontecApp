package com.des.odontec.equipe.odontec.View;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

public class AtualizarDados extends AppCompatActivity implements Runnable{
    private EditText nome;
    private EditText estado;
    private EditText cidade;
    private Button atualizar;
    private boolean teste;
    private String[] dados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_dados);
        nome=(EditText) findViewById(R.id.pegarNome);
        estado=(EditText) findViewById(R.id.pegarEstado);
        cidade=(EditText) findViewById(R.id.pegarCidade);
        atualizar=(Button) findViewById(R.id.enviarAtualizacao);

        UsuarioController usuarioController=new UsuarioController();

        dados=usuarioController.renoDados(AtualizarDados.this);
        teste=nome.getText().toString().isEmpty();
        if(teste){
            usuarioController.pegarDados(AtualizarDados.this);
        }

        nome.setText(dados[0].toString());
        estado.setText(dados[1].toString());
        cidade.setText(dados[2].toString());
        teste=nome.getText().toString().isEmpty();
        if(teste){
            Handler handler=new Handler();
            handler.postDelayed(this,10000);
        }

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarDados();
            }
        });


    }

    @Override
    public void run() {
        UsuarioController usuarioController=new UsuarioController();
        dados=usuarioController.renoDados(AtualizarDados.this);
        nome.setText(dados[0].toString());
        estado.setText(dados[1].toString());
        cidade.setText(dados[2].toString());
    }

    private void atualizarDados(){
        Usuario usuario=new Usuario();
        usuario.setNome(nome.getText().toString());
        usuario.setEstado(estado.getText().toString());
        usuario.setCidade(cidade.getText().toString());
        UsuarioController usuarioController=new UsuarioController();
        usuarioController.atualizarDados(usuario);
    }
}
