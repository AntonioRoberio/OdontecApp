package com.des.odontec.equipe.odontec.Dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Antonio on 31/08/2017.
 */

public class ConfiguracaoFirebase {
    private static DatabaseReference refenciaBanco;
    private static FirebaseAuth autenticar;

    public  static DatabaseReference refernciaBancoFirebase(){
        if(refenciaBanco==null){
            return refenciaBanco= FirebaseDatabase.getInstance().getReference();
        }
        return refenciaBanco;
    }

    public static FirebaseAuth autenticarDados(){
        if(autenticar==null){
            return  autenticar=FirebaseAuth.getInstance();
        }
        return autenticar;
    }
}
