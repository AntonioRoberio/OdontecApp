package com.des.odontec.equipe.odontec.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.R;
import com.google.firebase.auth.FirebaseAuth;

public class DeletarConta extends AppCompatActivity {
    private Button apagarConta;
    private EditText senha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_conta);
        senha = (EditText) findViewById(R.id.deletarConta);
        apagarConta = (Button) findViewById(R.id.confirmeExclusao);
        Preferencias arquivosDePreferencia = new Preferencias(this);
        if(!arquivosDePreferencia.retornaLogin().equals("odontec")){
            senha.setEnabled(false);
        }

        apagarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertaConfirmacao = new AlertDialog.Builder(DeletarConta.this);
                alertaConfirmacao.setTitle("Apagar Conta?").setMessage("Confirmação de exclusão de conta")
                        .setCancelable(false).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DeletarConta.this, "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UsuarioController usuarioController = new UsuarioController();
                        senha.setText("Campo desabilitado.");
                        usuarioController.apagarConta(senha.getText().toString(),DeletarConta.this);
                    }
                });
                alertaConfirmacao.create();
                alertaConfirmacao.show();
            }
        });
    }

    public void apagar(String resultado) {
                   if(resultado.contains("sucesso")){
                       startActivity( new Intent(DeletarConta.this, MainActivity_Login.class));
                       finish();
                   }else{
                       Toast.makeText(DeletarConta.this,resultado,Toast.LENGTH_LONG).show();
                   }


    }
}
