package com.des.odontec.equipe.odontec.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.Model.Quiz;
import com.des.odontec.equipe.odontec.Model.VersaoDados;
import com.des.odontec.equipe.odontec.View.SalvarBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Antonio on 01/11/2017.
 */

public class QuizDao {

    private Context context;
    private SQLiteDatabase bd;
    private BDSqlieDao bdSqlieDao;
    private DatabaseReference databaseReference = ConfiguracaoFirebaseDao.refernciaBancoFirebase();

    public QuizDao(Context context) {
        this.context = context;
        bdSqlieDao = new BDSqlieDao(context);
        bd = bdSqlieDao.getWritableDatabase();
    }


    public void pegarDadosBD(){

        databaseReference.child("versoes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VersaoDados versaoDados=dataSnapshot.getValue(VersaoDados.class);
                Preferencias arquivosDePreferencia=new Preferencias(context);
                if(!versaoDados.getJogo().toString().equals(arquivosDePreferencia.retornoVersao("quiz"))){
                    int contador = Integer.parseInt(arquivosDePreferencia.retornoVersao("contQuiz"));
                    if (contador != 0) {
                        for (int i = contador; i >= 1; i--) {
                            String id = String.valueOf(i);
                            bd.delete("quiz", "_id = ?", new String[]{id});
                        }
                    }
                    pegarDadosBD2();
                    arquivosDePreferencia.salvarVersaoQuiz(versaoDados.getJogo().toString(),"verQuiz");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void pegarDadosBD2() {
        databaseReference.child("quiz").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContentValues contentValues = new ContentValues();
                int cont = 0;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    cont++;
                    Quiz quiz = d.getValue(Quiz.class);
                    contentValues.put("pergunta", quiz.getPergunta());
                    contentValues.put("respostaA", quiz.getRespostaA());
                    contentValues.put("respostaB", quiz.getRespostaB());
                    contentValues.put("respostaC", quiz.getRespostaC());
                    contentValues.put("respostaD", quiz.getRespostaD());
                    contentValues.put("respostaE", quiz.getRespostaE());
                    contentValues.put("altCorreta", quiz.getAltCorreta());
                    contentValues.put("_id", cont + "");
                    bd.insert("quiz", null, contentValues);
                }
                Preferencias arquivosDePreferencia = new Preferencias(context);
                arquivosDePreferencia.salvarVersaoQuiz(new String(cont + ""), "cont");
                bd.close();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    public ArrayList<Quiz> listarPerguntas() {

        ArrayList<Quiz> quizzes = new ArrayList<>();

        String[] colunas = {"_id", "pergunta", "respostaA", "respostaB", "respostaC", "respostaD", "respostaE", "altCorreta"};
        Cursor cursor = bd.query("quiz", colunas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Quiz quiz = new Quiz();
                quiz.setId(cursor.getString(0));
                quiz.setPergunta(cursor.getString(1));
                quiz.setRespostaA(cursor.getString(2));
                quiz.setRespostaB(cursor.getString(3));
                quiz.setRespostaC(cursor.getString(4));
                quiz.setRespostaD(cursor.getString(5));
                quiz.setRespostaE(cursor.getString(6));
                quiz.setAltCorreta(cursor.getString(7));
                quizzes.add(quiz);
            } while (cursor.moveToNext());

        }
        bd.close();
        return quizzes;
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
