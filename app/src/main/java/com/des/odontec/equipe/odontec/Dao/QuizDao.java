package com.des.odontec.equipe.odontec.Dao;


import android.support.annotation.NonNull;

import com.des.odontec.equipe.odontec.Model.Quiz;
import com.des.odontec.equipe.odontec.View.SalvarBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Antonio on 01/11/2017.
 */

public class QuizDao {
    public QuizDao() {

    }

    public void salvarDados(Quiz quiz, final SalvarBD salvarBD) {
        DatabaseReference db = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
        db.child("quiz").child(quiz.getId()).setValue(quiz.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                SalvarBD volta = salvarBD;
                ;
                if (task.isSuccessful()) {
                    volta.salvarQuiz("sucesso");
                } else {
                    volta.salvarQuiz("falha");
                }
            }
        });
    }
}
