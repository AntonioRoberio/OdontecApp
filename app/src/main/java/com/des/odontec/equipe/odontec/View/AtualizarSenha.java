package com.des.odontec.equipe.odontec.View;

import android.content.Intent;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

public class AtualizarSenha extends AppCompatActivity {
    private EditText atual;
    private EditText senha;
    private EditText confirmar;
    private Button enviar;
    private Usuario usuario;
    private UsuarioController usuarioController;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_senha);
        atual = (EditText) findViewById(R.id.senhaAtual);
        senha = (EditText) findViewById(R.id.novaSenha);
        confirmar = (EditText) findViewById(R.id.confirmaSenha);
        enviar = (Button) findViewById(R.id.salvarNovaSenha);
        fl = (FrameLayout) findViewById(R.id.f2l);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioController = new UsuarioController(AtualizarSenha.this);
                usuarioController.pegarDados();
                usuario = usuarioController.exibirDados();
                if (!(senha.getText().toString().isEmpty() || confirmar.getText().toString().isEmpty() || atual.getText().toString().isEmpty())) {
                    if (senha.getText().toString().equals(confirmar.getText().toString())) {
                        if (!(senha.getText().toString().length() < 6)) {
                            enviar.setEnabled(false);
                            usuario.setSenha(Criptografia.md5(senha.getText().toString()));
                            fl.setVisibility(View.VISIBLE);
                            usuarioController = new UsuarioController();
                            usuarioController.atualizarSenha(atual.getText().toString(), usuario, AtualizarSenha.this,"senha","att");

                        } else {
                            Toast.makeText(AtualizarSenha.this, "A senha deve conter no mínimo 6 caracteres", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(AtualizarSenha.this, "As senhas são divergentes", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(AtualizarSenha.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void atualizarSe(String resultado) {
        if (resultado.contains("Senha Alterada")) {
            Intent intent=new Intent(AtualizarSenha.this, InicialActivity.class);
            startActivity(intent);
            setResult(10);
            finish();
        } else {
            enviar.setEnabled(false);
            fl.setVisibility(View.GONE);
        }
        Toast.makeText(AtualizarSenha.this, resultado, Toast.LENGTH_LONG).show();
    }


}
