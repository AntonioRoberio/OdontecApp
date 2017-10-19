package com.des.odontec.equipe.odontec.Dao;

import com.des.odontec.equipe.odontec.Model.Patologia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Antonio on 18/10/2017.
 */

public class PatologiaDao {

    private DatabaseReference databaseReference=ConfiguracaoFirebaseDao.refernciaBancoFirebase();

    private void pegarPatologias(){
        databaseReference.child("patologias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Patologia p=dataSnapshot.getValue(Patologia.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
