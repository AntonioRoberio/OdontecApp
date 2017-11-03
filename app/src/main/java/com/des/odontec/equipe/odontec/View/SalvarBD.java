package com.des.odontec.equipe.odontec.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Dao.QuizDao;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Quiz;
import com.des.odontec.equipe.odontec.R;

public class SalvarBD extends AppCompatActivity {
    private EditText pergunta;
    private EditText respostaA;
    private EditText respostaB;
    private EditText respostaC;
    private EditText respostaD;
    private EditText respostaE;
    private EditText altCorreta;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_bd);
        pergunta = (EditText) findViewById(R.id.pergunta);
        respostaA = (EditText) findViewById(R.id.respostaA);
        respostaB = (EditText) findViewById(R.id.respostaB);
        respostaC = (EditText) findViewById(R.id.respostaC);
        respostaD = (EditText) findViewById(R.id.respostaD);
        respostaE = (EditText) findViewById(R.id.respostaE);
        altCorreta = (EditText) findViewById(R.id.rtCorreta);
        button = (Button) findViewById(R.id.salvarQuiz);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(pergunta.getText().toString().isEmpty() || respostaA.getText().toString().isEmpty() || respostaB.getText().toString().isEmpty() || respostaC.getText().toString().isEmpty() ||
                        respostaD.getText().toString().isEmpty() || respostaE.getText().toString().isEmpty() || altCorreta.getText().toString().isEmpty())){
                    Quiz quiz=new Quiz();
                    quiz.setPergunta(pergunta.getText().toString().trim());
                    quiz.setRespostaA(respostaA.getText().toString().trim());
                    quiz.setRespostaB(respostaB.getText().toString().trim());
                    quiz.setRespostaC(respostaC.getText().toString().trim());
                    quiz.setRespostaD(respostaD.getText().toString().trim());
                    quiz.setRespostaE(respostaE.getText().toString().trim());
                    quiz.setAltCorreta(altCorreta.getText().toString().trim());
                    quiz.setId(Criptografia.md5(pergunta.getText().toString().trim()));
                    //QuizDao quizDao=new QuizDao();
                    //quizDao.salvarDados(quiz,SalvarBD.this);
                }else {
                    Toast.makeText(SalvarBD.this,"Campo em branco",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void salvarQuiz(String resultado){
        if(resultado.equals("sucesso")){
            Toast.makeText(SalvarBD.this,"sucesso",Toast.LENGTH_SHORT).show();
            pergunta.setText("");
            respostaA.setText("");
            respostaB.setText("");
            respostaC.setText("");
            respostaD.setText("");
            respostaE.setText("");
            altCorreta.setText("");
        }else{
            Toast.makeText(SalvarBD.this,"falha",Toast.LENGTH_SHORT).show();
        }
    }

}
