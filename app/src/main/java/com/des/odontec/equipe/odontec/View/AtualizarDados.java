package com.des.odontec.equipe.odontec.View;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

public class AtualizarDados extends AppCompatActivity{
    private EditText nome;
    private Spinner estado;
    private EditText cidade;
    private Button atualizar;
    private Usuario usuario;
    private UsuarioController usuarioController;
    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_dados);
        nome = (EditText) findViewById(R.id.pegarNome);
        estado = (Spinner) findViewById(R.id.pegarEstado);
        cidade = (EditText) findViewById(R.id.pegarCidade);
        atualizar = (Button) findViewById(R.id.enviarAtualizacao);
        frame = (FrameLayout) findViewById(R.id.fl3);



        usuarioController = new UsuarioController(AtualizarDados.this);
        usuarioController.pegarDados();
        usuario = usuarioController.exibirDados();
        nome.setText(usuario.getNome());
        //-------------------------------------------------------------------------
        Resources r=getResources();
        String[] es=r.getStringArray(R.array.estados);
        String atual=es[0];
        for(int i=0;i<es.length-1;i++){
           if(es[i].equals(usuario.getEstado())){
               if(es[i].equals(es[0])){
                   break;
               }else{
                   es[0]=es[i];
                   es[i]=atual;
                   break;
               }
           }
        }
        ArrayAdapter<String> valores=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,es);
        estado.setAdapter(valores);

        //-------------------------------------------------------------------------
        cidade.setText(usuario.getCidade());

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarDados();
            }
        });


    }

    private void atualizarDados() {
        usuarioController = new UsuarioController(AtualizarDados.this);
        usuario = new Usuario();
        usuario.setNome(nome.getText().toString());
        usuario.setEstado(estado.getSelectedItem().toString());
        usuario.setCidade(cidade.getText().toString());
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.atualizarDados(usuario);
        Bundle bundle=new Bundle();
        bundle.putString("VALOR","odontec");
        Intent intent=new Intent(AtualizarDados.this,InicialActivity.class);
        frame.setVisibility(View.VISIBLE);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
