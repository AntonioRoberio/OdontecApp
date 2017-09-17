package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebase;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AtualizarSenha extends AppCompatActivity {
    private EditText atual;
    private EditText senha;
    private EditText confirmar;
    private Button envair;
    private Usuario usuario;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_senha);
        atual=(EditText) findViewById(R.id.senhaAtual);
        senha=(EditText) findViewById(R.id.novaSenha);
        confirmar=(EditText) findViewById(R.id.confirmaSenha);
        envair=(Button) findViewById(R.id.salvarNovaSenha);

        envair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=new Usuario();
                if(!(senha.getText().toString().isEmpty() || confirmar.getText().toString().isEmpty() || atual.getText().toString().isEmpty())){
                    if (senha.getText().toString().equals(confirmar.getText().toString())){
                        usuario.setSenha(Criptografia.md5(senha.getText().toString()));
                        atualizarSe();
                    }else{
                        Toast.makeText(AtualizarSenha.this,"As senhas são divergentes",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(AtualizarSenha.this,"Preencha todos os campos",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void atualizarSe(){
        FirebaseAuth auth= ConfiguracaoFirebase.autenticarDados();
        user=auth.getCurrentUser();
        AuthCredential authCredential= EmailAuthProvider.getCredential(user.getEmail().toString(),
                Criptografia.md5(atual.getText().toString()));
        user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(usuario.getSenha().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AtualizarSenha.this,"Senha Alterada com sucesso!",Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(AtualizarSenha.this, InicialActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AtualizarSenha.this,"Falha na atualização da senha",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(AtualizarSenha.this,"Erro na atualização da senha. Confira se sua senha atual está correta.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
