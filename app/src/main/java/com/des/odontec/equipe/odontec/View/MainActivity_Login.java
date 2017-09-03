package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebase;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity_Login extends AppCompatActivity {
    private TextView cadastrar;
    private TextView resetSenha;
    private Button logar;
    private EditText email;
    private EditText senha;
    private FirebaseAuth aut;
    private FirebaseAuth.AuthStateListener verificarUsuario;
    private Usuario usuario;
    private String TAG;
    private CallbackManager callbackManager;
    private Button loginFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.emialLogin);
        senha=(EditText) findViewById(R.id.senhaLogin);
        logar=(Button) findViewById(R.id.logarSistema);
        aut= ConfiguracaoFirebase.autenticarDados();
        cadastrar=(TextView) findViewById(R.id.cadatrarUusario);
        resetSenha=(TextView) findViewById(R.id.recuperarSenha);
        loginFace=(Button) findViewById(R.id.logarSistemaFacebook);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebooktToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
              Toast.makeText(MainActivity_Login.this,"Erro ao logar"+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                autenticarUsuario();
            }


        });
        resetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity_Login.this,ResetSenha.class);
                startActivity(intent);
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity_Login.this,CadastrarUsuario.class);
                startActivity(intent);
            }
        });

        loginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFacebook();
            }
        });

        verificarUsuario = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void loginFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_friends","email"));

    }

    private void facebooktToken(AccessToken accessToken){
        final FirebaseAuth auten= ConfiguracaoFirebase.autenticarDados();
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auten.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser us=auten.getCurrentUser();
                    logar(us);
                    Toast.makeText(MainActivity_Login.this,"Seja bem vindo",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(MainActivity_Login.this,"Erro ao logar",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = aut.getCurrentUser();
        aut.addAuthStateListener(verificarUsuario);
        logar(currentUser);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (verificarUsuario != null) {
            aut.removeAuthStateListener(verificarUsuario);
        }
    }

    private void autenticarUsuario(){
        aut.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser us=aut.getCurrentUser();
                    logar(us);
                    Toast.makeText(MainActivity_Login.this,"Seja bem vindo",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(MainActivity_Login.this,"Erro ao logar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void logar(FirebaseUser user){
        if(user!=null){
            Intent intent= new Intent(MainActivity_Login.this,TelaPrincipal.class);
            startActivity(intent);
        }


    }
}
