package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebaseDao;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity {
    private Button salvar;
    private EditText email;
    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);
        auth = ConfiguracaoFirebaseDao.autenticarDados();
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

    private void resetar() {
        auth.sendPasswordResetEmail(usuario.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ResetSenha.this, "Um E-mail foi enviado para você. Confira sua caixa de entrada!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetSenha.this, MainActivity_Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ResetSenha.this, "Erro ao enviar E-mail para reset de senha", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}




