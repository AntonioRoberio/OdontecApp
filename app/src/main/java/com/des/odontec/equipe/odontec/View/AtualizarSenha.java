package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.ArquivosDePreferencia;
import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

public class AtualizarSenha extends AppCompatActivity  implements Runnable{
    private EditText atual;
    private EditText senha;
    private EditText confirmar;
    private Button enviar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_senha);
        atual = (EditText) findViewById(R.id.senhaAtual);
        senha = (EditText) findViewById(R.id.novaSenha);
        confirmar = (EditText) findViewById(R.id.confirmaSenha);
        enviar = (Button) findViewById(R.id.salvarNovaSenha);
        final FrameLayout fl = (FrameLayout) findViewById(R.id.f2l);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                if (!(senha.getText().toString().isEmpty() || confirmar.getText().toString().isEmpty() || atual.getText().toString().isEmpty())) {
                    if (senha.getText().toString().equals(confirmar.getText().toString())) {
                        usuario.setSenha(Criptografia.md5(senha.getText().toString()));
                        //fl.setVisibility(View.VISIBLE);
                        atualizarSe();
                    } else {
                        Toast.makeText(AtualizarSenha.this, "As senhas são divergentes", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(AtualizarSenha.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void atualizarSe() {

        UsuarioController usuarioController=new UsuarioController();
        usuarioController.atualizarSenha(AtualizarSenha.this,atual.getText().toString(),usuario);
        ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(AtualizarSenha.this);

        String s=arquivosDePreferencia.retornostatusDeVerificacao();
        if(!s.equals("Usuário cadastrado com sucesso")){
            Handler handler=new Handler();
            handler.postDelayed(this,6000);
        }else{
            Intent intent=new Intent(AtualizarSenha.this,InicialActivity.class);
            startActivity(intent);
            Toast.makeText(AtualizarSenha.this, s, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void run() {
        ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(AtualizarSenha.this);
        String s=arquivosDePreferencia.retornostatusDeVerificacao();
        if(s.equals("Usuário cadastrado com sucesso")){
            Intent intent=new Intent(AtualizarSenha.this,InicialActivity.class);
            startActivity(intent);
            Toast.makeText(AtualizarSenha.this, s, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(AtualizarSenha.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
