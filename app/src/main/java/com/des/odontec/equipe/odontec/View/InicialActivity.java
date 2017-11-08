package com.des.odontec.equipe.odontec.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.R;

public class InicialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TableRow botao;
    private TableRow btnPatologia;
    private TableRow jogo;
    private TableRow listPacientes;
    NavigationView escolhaMenu;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UsuarioController usuarioController = new UsuarioController(InicialActivity.this);
        botao = (TableRow) findViewById(R.id.btnTesteAne);
        btnPatologia = (TableRow) findViewById(R.id.btnPatologia);
        jogo = (TableRow) findViewById(R.id.btnQuizP);
        listPacientes = (TableRow) findViewById(R.id.tbPstPacientes);
        escolhaMenu = (NavigationView) findViewById(R.id.nav_view);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, CadastrarPaciente.class);
                startActivity(intent);
            }
        });

        btnPatologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, SelecionarPatologia.class);
                startActivity(intent);
            }
        });

        jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, Quizz.class);
                startActivityForResult(intent, 10);
            }
        });

        listPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, ListaDePacientes.class);
                startActivityForResult(intent, 10);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Recuperação de Dados

        Preferencias arquivosDePreferencia = new Preferencias(this);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey("VALOR")) {
                if (bundle.getString("VALOR").toString().equals("odontec")) {
                    usuarioController.pegarDados();
                }

                arquivosDePreferencia.login(bundle.getString("VALOR").toString());
            }
        }

        if (arquivosDePreferencia.retornaLogin().equals("odontec")) {
            escolhaMenu.inflateMenu(R.menu.activity_inicial_drawer);
            usuarioController.exibirDados();
        } else {
            escolhaMenu.inflateMenu(R.menu.activity_inicio_drawer);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_inicial_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------------------------------0k
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.AlterarSenha) {
            Intent intent = new Intent(InicialActivity.this, AtualizarSenha.class);
            startActivityForResult(intent, 10);
        } else if (id == R.id.EditarInformacoes) {
            Intent intent = new Intent(InicialActivity.this, AtualizarDados.class);
            startActivityForResult(intent, 10);
        } else if (id == R.id.ExcluirConta) {
            Intent intent = new Intent(InicialActivity.this, DeletarConta.class);
            startActivity(intent);
        } else if (id == R.id.Sair) {

            AlertDialog.Builder alert = new AlertDialog.Builder(InicialActivity.this);
            alert.setTitle("Sair?").setMessage("Tem certeza que deseja sair?").setCancelable(true)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(InicialActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UsuarioController usuarioController = new UsuarioController();
                    usuarioController.fazerLgoutSistema();
                    Intent intent = new Intent(InicialActivity.this, MainActivity_Login.class);
                    startActivity(intent);
                    finish();
                }
            });
            alert.create();
            alert.show();
        } else if (id == R.id.quiz) {
            Intent intent = new Intent(InicialActivity.this, SalvarBD.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 10) {
            finish();
        }
    }


}
