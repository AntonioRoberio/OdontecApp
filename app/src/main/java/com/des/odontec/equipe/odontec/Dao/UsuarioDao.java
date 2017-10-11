package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioDao {
    private SQLiteDatabase banco;
    private BDSqlieDao bdUsuario;
    private Context context;
    public UsuarioDao() {

    }
    public UsuarioDao(Context context) {
        this.context=context;
        bdUsuario=new BDSqlieDao(context,"usuarios");
        banco=bdUsuario.getWritableDatabase();
    }

    //método que faz o cadastro dos dados de um novo usuário no banco de dados do firebase
    public void salvarBD(Usuario usuario) {
        FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
        FirebaseUser user = aut.getCurrentUser();
        user.sendEmailVerification();
        String id = user.getUid();
        usuario.setId(id);
        DatabaseReference dados = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
        dados.child("user").child(String.valueOf(usuario.getId())).setValue(usuario.toMap());
    }

    public void pegarDados() {
        DatabaseReference reference = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
        FirebaseAuth auth = ConfiguracaoFirebaseDao.autenticarDados();
        final FirebaseUser user = auth.getCurrentUser();
        String id = user.getUid().toString();
        reference.child("user").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    Usuario u = dataSnapshot.getValue(Usuario.class);
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("nome", u.getNome().toString());
                    contentValues.put("estado", u.getEstado().toString());
                    contentValues.put("cidade", u.getCidade().toString());
                    contentValues.put("email", u.getEmail().toString());
                    contentValues.put("_id",user.getUid().toString());
                    banco.insert("usuarios",null,contentValues);



                banco.close();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Usuario listarDados() {
        Usuario usuario=new Usuario();
        String[] tabela={"_id","nome","estado","cidade","email"};
       Cursor cursor= banco.query("usuarios",tabela,null,null,null,null,null);
        if(cursor.moveToFirst()){
            FirebaseAuth auth = ConfiguracaoFirebaseDao.autenticarDados();
            final FirebaseUser user = auth.getCurrentUser();
            do{
                if(cursor.getString(0).equals(user.getUid().toString())){
                    usuario.setId(cursor.getString(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setEstado(cursor.getString(2));
                    usuario.setCidade(cursor.getString(3));
                    usuario.setEmail(cursor.getString(4));
                    break;
                }

            }while (cursor.moveToNext());
        }
        return usuario;
    }



    //atualização das informações do usuário contido em banco

    public void upDados(Usuario usuario) {
        FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
        DatabaseReference atualizar = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
        FirebaseUser user = aut.getCurrentUser();
        Map<String, Object> up = new HashMap<>();
        up.put("nome", usuario.getNome());
        up.put("estado", usuario.getEstado());
        up.put("cidade", usuario.getCidade());
        atualizar.child("user").child(String.valueOf(user.getUid().toString())).updateChildren(up);
    }

    public void fazerLgout() {
        FirebaseAuth sair = ConfiguracaoFirebaseDao.autenticarDados();
        sair.signOut();
    }

    public Task<Void> deletar(String senha) {
        FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
        FirebaseUser user = aut.getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail().toString(),
                Criptografia.md5(senha.toString()));
        return user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
                    FirebaseUser user = aut.getCurrentUser();
                    DatabaseReference remover = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
                    remover.child("user").child(user.getUid().toString()).removeValue();
                    user.delete();
                }
            }
        });



    }

    public Task<Void> resetar(Usuario usuario) {
        FirebaseAuth auth; auth= ConfiguracaoFirebaseDao.autenticarDados();
        return auth.sendPasswordResetEmail(usuario.getEmail());
    }

    public Task<Void> atualizarSe(String atual, final Usuario usuario) {
        FirebaseAuth auth = ConfiguracaoFirebaseDao.autenticarDados();
        final FirebaseUser user = auth.getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail().toString(),
                Criptografia.md5(atual.toString()));
        return user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                user.updatePassword(usuario.getSenha().toString());
            }
        });


    }

    public Task<AuthResult> cadastraUsuario(Usuario usuario) {
        FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
        return aut.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha());
    }

    public Task<AuthResult> logar(Usuario usuario){
        FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
      return aut.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha());
    }

}
