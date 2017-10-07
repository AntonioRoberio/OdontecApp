package com.des.odontec.equipe.odontec.View;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

public class ResetSenha extends AppCompatActivity{
    private Button salvar;
    private EditText email;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);
        email = (EditText) findViewById(R.id.emalReset);
        salvar = (Button) findViewById(R.id.envairReset);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                if(!email.getText().toString().isEmpty()){
                    usuario.setEmail(email.getText().toString());
                    UsuarioController usuarioController = new UsuarioController();
                    usuarioController.resetSenha(usuario,ResetSenha.this);
                }else{
                    Toast.makeText(ResetSenha.this, "Preenchimento de campo obrigatório.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void resetar(String resultado){
        if(resultado.contains("Um E-mail")){
            startActivity(new Intent(ResetSenha.this,MainActivity_Login.class));
        }
        Toast.makeText(ResetSenha.this,resultado, Toast.LENGTH_LONG).show();
    }



}




