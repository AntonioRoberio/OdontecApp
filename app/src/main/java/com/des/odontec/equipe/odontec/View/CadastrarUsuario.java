package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

public class CadastrarUsuario extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText confimarSenha;
    private Spinner estado;
    private EditText cidade;
    private String senhaCript;
    private Button salvar;
    private Usuario usuario;
    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_cadastrar_usuario);


        nome = (EditText) findViewById(R.id.nomeUsuario);
        email = (EditText) findViewById(R.id.emailUsuario);
        senha = (EditText) findViewById(R.id.senhaUsuario);
        estado = (Spinner) findViewById(R.id.estadoUsuario);
        cidade = (EditText) findViewById(R.id.cidadeUsuario);
        confimarSenha = (EditText) findViewById(R.id.confirSenhaUsuario);
        salvar = (Button) findViewById(R.id.btSalvar);
        fl = (FrameLayout) findViewById(R.id.fl2);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(nome.getText().toString().isEmpty() || email.getText().toString().isEmpty() || senha.getText().toString().isEmpty() || cidade.getText().toString().isEmpty())) {
                    if (senha.getText().toString().equals(confimarSenha.getText().toString())) {
                        if (!(senha.getText().toString().length() < 6)) {
                            senhaCript = Criptografia.md5(senha.getText().toString());
                            usuario = new Usuario();
                            usuario.setNome(nome.getText().toString());
                            usuario.setEmail(email.getText().toString());
                            usuario.setSenha(senhaCript.toString());
                            usuario.setEstado(estado.getSelectedItem().toString());
                            usuario.setCidade(cidade.getText().toString());
                            fl.setVisibility(View.VISIBLE);
                            UsuarioController usuarioController = new UsuarioController();
                            usuarioController.cdtUsuario(usuario, CadastrarUsuario.this);
                        } else {
                            Toast.makeText(CadastrarUsuario.this, "A senha deve conter no mínimo 6 caracteres", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CadastrarUsuario.this, "As senhas são divergentes", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastrarUsuario.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void cadastraUsuario(String resultado) {
        if (resultado.contains("Usuário cadastrado")) {
            Bundle bundle=new Bundle();
            bundle.putString("VALOR", "odontec");
            Intent intent=new Intent(CadastrarUsuario.this, MainActivity_Login.class);
            intent.putExtras(bundle);
            startActivity(intent);
            setResult(8,intent);
            UsuarioController usuarioController = new UsuarioController(CadastrarUsuario.this);
            usuarioController.pegarDados();
            finish();
        } else {
            fl.setVisibility(View.GONE);
        }
        Toast.makeText(CadastrarUsuario.this, resultado, Toast.LENGTH_LONG).show();
    }
}
