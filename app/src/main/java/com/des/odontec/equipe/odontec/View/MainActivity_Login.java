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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class MainActivity_Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
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
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN=777;
    private Button loginFace;
    private Button loginGoogle;

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
        loginGoogle=(Button) findViewById(R.id.logarSistemaGoog);

        callbackManager = CallbackManager.Factory.create();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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

        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
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
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);

        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        aut.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser us=aut.getCurrentUser();
                    logar(us);
                    Toast.makeText(MainActivity_Login.this,"Seja bem vindo",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String mensagemErro="";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        mensagemErro="Senha fraca. digite uma senha contendo no mínimo 6 caracteres.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        mensagemErro="Endereço de E-MAIL invalido.";
                    }catch (FirebaseAuthUserCollisionException e){
                        mensagemErro="Este E-MAIL já está sendo usado";
                    }catch (Exception e){
                        mensagemErro="Erro ao se cadastrar";
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity_Login.this,mensagemErro,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loginFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_friends","email"));

    }

    private void facebooktToken(AccessToken accessToken){
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        aut.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser us=aut.getCurrentUser();
                    logar(us);
                    Toast.makeText(MainActivity_Login.this,"Seja bem vindo",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String mensagemErro="";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        mensagemErro="Senha fraca. digite uma senha contendo no mínimo 6 caracteres.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        mensagemErro="Endereço de E-MAIL invalido.";
                    }catch (FirebaseAuthUserCollisionException e){
                        mensagemErro="Este E-MAIL já está sendo usado";
                    }catch (Exception e){
                        mensagemErro="Erro ao se cadastrar";
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity_Login.this,mensagemErro,Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void loginGoogle(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

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
                    Toast.makeText(MainActivity_Login.this,"Erro ao logar",Toast.LENGTH_LONG).show();
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(MainActivity_Login.this,"Erro ao logar"+connectionResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }
}
