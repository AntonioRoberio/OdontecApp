package com.des.odontec.equipe.odontec.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.des.odontec.equipe.odontec.R;

public class AlteracaoSistemica extends AppCompatActivity {
    private CheckBox anemia;
    private CheckBox diabete;
    private CheckBox asma;
    private CheckBox gestante;
    private CheckBox hipertensao;
    private CheckBox hiperterio;
    private CheckBox norma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteraca_sistemica);
        anemia=(CheckBox) findViewById(R.id.cbxAnemia);
        diabete=(CheckBox) findViewById(R.id.cbxDiabete);
        asma=(CheckBox) findViewById(R.id.cbxAsma);
        gestante=(CheckBox) findViewById(R.id.cbxGestante);
        hipertensao=(CheckBox) findViewById(R.id.cbxHipertensao);
        hiperterio=(CheckBox) findViewById(R.id.cbxHipertireodismo);
        norma=(CheckBox) findViewById(R.id.cbxNS);
    }
}
