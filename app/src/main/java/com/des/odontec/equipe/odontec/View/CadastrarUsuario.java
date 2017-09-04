package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebase;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarUsuario extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText confimarSenha;
    private EditText idade;
    private EditText estado;
    private EditText cidade;
    private RadioGroup sexo;
    private RadioButton mf;
    private    UsuarioController usuarioController;
    private Button salvar;
    private Usuario usuario;
    private FirebaseAuth aut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);


        nome=(EditText) findViewById(R.id.nomeUsuario);
        email=(EditText) findViewById(R.id.emailUsuario);
        senha=(EditText) findViewById(R.id.senhaUsuario);
        idade=(EditText) findViewById(R.id.idadeUsuario);
        estado=(EditText) findViewById(R.id.estadoUsuario);
        cidade=(EditText) findViewById(R.id.cidadeUsuario);
        sexo=(RadioGroup) findViewById(R.id.selecionarSexo);
        confimarSenha=(EditText) findViewById(R.id.confirSenhaUsuario);
        salvar=(Button) findViewById(R.id.btSalvar);
        int escolha=sexo.getCheckedRadioButtonId();
        mf=(RadioButton) findViewById(escolha);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nome.getText().toString().isEmpty() || !email.getText().toString().isEmpty() || !senha.getText().toString().isEmpty() || !idade.getText().toString().isEmpty() || !estado.getText().toString().isEmpty() || !cidade.getText().toString().isEmpty()){
                    if(senha.getText().toString().equals(confimarSenha.getText().toString())){
                        usuario=new Usuario();
                        aut= ConfiguracaoFirebase.autenticarDados();
                        usuario.setNome(nome.getText().toString());
                        usuario.setEmail(email.getText().toString());
                        usuario.setSenha(senha.getText().toString());
                        usuario.setIdade(idade.getText().toString());
                        usuario.setEstado(estado.getText().toString());
                        usuario.setCidade(cidade.getText().toString());
                        usuario.setSexo(mf.getText().toString());
                        cadastraUsuario();
                    }else{
                        Toast.makeText(CadastrarUsuario.this,"As senhas são divergentes",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CadastrarUsuario.this,"Preencha todos os campos!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void cadastraUsuario(){
        aut= ConfiguracaoFirebase.autenticarDados();

        aut.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=aut.getCurrentUser();
                    String id=user.getUid();
                    usuario.setId(id);
                    usuarioController=new UsuarioController();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(CadastrarUsuario.this,"Usuário cadastrado com sucesso",Toast.LENGTH_SHORT).show();
                                usuarioController.cdtUsuario(usuario);
                                finish();
                                Intent intent=new Intent(CadastrarUsuario.this,TelaPrincipal.class);
                                startActivity(intent);
                            }else{
                                usuarioController.apagarConta();
                                Toast.makeText(CadastrarUsuario.this,"Esse endereço de E-mial não é valido",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }else{
                    Toast.makeText(CadastrarUsuario.this,"Erro ao cadastrar usuário",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
