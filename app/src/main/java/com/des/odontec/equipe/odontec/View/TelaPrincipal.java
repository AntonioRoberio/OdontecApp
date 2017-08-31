package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.R;

public class TelaPrincipal extends AppCompatActivity {
    private Button sair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        sair=(Button) findViewById(R.id.logoutSistema);

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
    }
}
