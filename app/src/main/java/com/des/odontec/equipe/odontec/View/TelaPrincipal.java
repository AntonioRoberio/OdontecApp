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

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebase;
import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class TelaPrincipal extends AppCompatActivity {
    private Button sair;
    private Button atualizarDados;
    private Button delatarConta;
    private Button btnAnestesico;
    private Button novaSenha;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        sair=(Button) findViewById(R.id.logoutSistema);
        atualizarDados=(Button) findViewById(R.id.atualizarInformacoes);
        delatarConta=(Button) findViewById(R.id.excluirConta);
        novaSenha=(Button) findViewById(R.id.atualizarSenha);
        btnAnestesico=(Button) findViewById(R.id.btnAnestesico);
        UsuarioController usuarioController=new UsuarioController();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            if(bundle.getString("VALOR").toString().equals("odontec"))usuarioController.pegarDados(TelaPrincipal.this);

        }


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert= new AlertDialog.Builder(TelaPrincipal.this);
                alert.setTitle("Sair?").setMessage("Tem certeza que deseja sair?").setCancelable(true)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(TelaPrincipal.this,"Cancelado",Toast.LENGTH_SHORT).show();
                            }
                        }).setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UsuarioController usuarioController=new UsuarioController();
                        usuarioController.fazerLgoutSistema();
                        Intent intent=new Intent(TelaPrincipal.this,MainActivity_Login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alert.create();
                alert.show();
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
                Intent intent=new Intent(TelaPrincipal.this,DeletarConta.class);
                startActivity(intent);

            }
        });

        novaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TelaPrincipal.this,AtualizarSenha.class);
                startActivity(intent);
            }
        });

        btnAnestesico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TelaPrincipal.this,TipoPaciente.class);
                startActivity(intent);
            }
        });


    }
}
