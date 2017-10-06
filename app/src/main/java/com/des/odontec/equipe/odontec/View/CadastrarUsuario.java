package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebaseDao;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarUsuario extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText confimarSenha;
    private EditText estado;
    private EditText cidade;
    private String senhaCript;
    private int valor;
    private UsuarioController usuarioController;
    private Button salvar;
    private Usuario usuario;
    private FirebaseAuth aut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);


        nome = (EditText) findViewById(R.id.nomeUsuario);
        email = (EditText) findViewById(R.id.emailUsuario);
        senha = (EditText) findViewById(R.id.senhaUsuario);
        estado=(EditText) findViewById(R.id.estadoUsuario);
        cidade = (EditText) findViewById(R.id.cidadeUsuario);
        confimarSenha = (EditText) findViewById(R.id.confirSenhaUsuario);
        salvar = (Button) findViewById(R.id.btSalvar);
        final FrameLayout fl = (FrameLayout) findViewById(R.id.fl2);


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(nome.getText().toString().isEmpty() || email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()  || cidade.getText().toString().isEmpty())) {
                    if (senha.getText().toString().equals(confimarSenha.getText().toString())) {

                        senhaCript = Criptografia.md5(senha.getText().toString());
                        usuario = new Usuario();
                        aut = ConfiguracaoFirebaseDao.autenticarDados();
                        usuario.setNome(nome.getText().toString());
                        usuario.setEmail(email.getText().toString());
                        usuario.setSenha(senhaCript.toString());
                        usuario.setEstado(estado.getText().toString());
                        usuario.setCidade(cidade.getText().toString());
                        fl.setVisibility(View.VISIBLE);
                        cadastraUsuario();
                    } else {
                        Toast.makeText(CadastrarUsuario.this, "As senhas são divergentes", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastrarUsuario.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    //vai para o model ou outro arqivo firebase
    public void cadastraUsuario() {
        aut = ConfiguracaoFirebaseDao.autenticarDados();

        aut.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = aut.getCurrentUser();
                    String id = user.getUid();
                    usuario.setId(id);
                    usuarioController = new UsuarioController();
                    usuarioController.cdtUsuario(usuario);
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> verificar) {
                            if (verificar.isSuccessful()) {
                                Toast.makeText(CadastrarUsuario.this,"Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CadastrarUsuario.this, InicialActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(CadastrarUsuario.this, "Este esdereço de E-MAIL não existe. Digite um email valido", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                } else {

                    String mensagemErro = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        mensagemErro = "Senha fraca. digite uma senha contendo no mínimo 6 caracteres.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        mensagemErro = "Endereço de E-MAIL invalido.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        mensagemErro = "Este E-MAIL já está sendo usado";
                    } catch (Exception e) {
                        mensagemErro = "Erro ao se cadastrar";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastrarUsuario.this, mensagemErro, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
