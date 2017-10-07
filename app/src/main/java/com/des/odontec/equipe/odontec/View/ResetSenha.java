package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.ArquivosDePreferencia;
import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebaseDao;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity implements Runnable{
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
                    resetar();
                }else{
                    Toast.makeText(ResetSenha.this, "Preenchimento de campo obrigatório.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    //vai para o model ou arquivo tipo fire
    private void resetar() {
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.resetSenha(usuario,ResetSenha.this);

        ArquivosDePreferencia arquivosDePreferencia = new ArquivosDePreferencia(ResetSenha.this);

        String s = arquivosDePreferencia.retornostatusDeVerificacao();
        if (!s.equals("sucesso")) {
            Handler handler = new Handler();
            handler.postDelayed(this, 6000);
        } else {
            Intent intent = new Intent(ResetSenha.this,MainActivity_Login.class);
            startActivity(intent);
            Toast.makeText(ResetSenha.this,"Um E-mail foi enviado para você. Confira sua caixa de entrada!", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void run(){
        ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(ResetSenha.this);
        String s=arquivosDePreferencia.retornostatusDeVerificacao();
        if(s.equals("sucesso")){
            Intent intent=new Intent(ResetSenha.this,MainActivity_Login.class);
            startActivity(intent);
            Toast.makeText(ResetSenha.this,"Um E-mail foi enviado para você. Confira sua caixa de entrada!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ResetSenha.this,"Erro ao enviar E-mail para reset de senha", Toast.LENGTH_LONG).show();
        }
    }
}




