package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.R;

public class TelaPrincipal extends AppCompatActivity {
    private Button sair;
    private Button atualizarDados;
    private Button delatarConta;
    private Button novaSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        sair=(Button) findViewById(R.id.logoutSistema);
        atualizarDados=(Button) findViewById(R.id.atualizarInformacoes);
        delatarConta=(Button) findViewById(R.id.excluirConta);
        novaSenha=(Button) findViewById(R.id.atualizarSenha);

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioController usuarioController=new UsuarioController();
                usuarioController.fazerLgoutSistema();
                Intent intent=new Intent(TelaPrincipal.this,MainActivity_Login.class);
                startActivity(intent);
                finish();
            }
        });

        atualizarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TelaPrincipal.this,AtualizarDados.class);
                startActivity(intent);
            }
        });

        delatarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioController usuarioController=new UsuarioController();
                usuarioController.apagarConta();
                Intent intent=new Intent(TelaPrincipal.this,MainActivity_Login.class);
                startActivity(intent);
                usuarioController.fazerLgoutSistema();
                finish();
            }
        });

        novaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TelaPrincipal.this,AtualizarSenha.class);
                startActivity(intent);
            }
        });


    }
}
