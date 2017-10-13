package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.AlteracaoDao;
import com.des.odontec.equipe.odontec.Model.Alteracao;

import java.util.ArrayList;

/**
 * Created by Antonio on 25/09/2017.
 */

public class AlteracaoController {
    AlteracaoDao alteracaoDao;
    private int i = 0;

    public AlteracaoController(Context context) {
        alteracaoDao = new AlteracaoDao(context);
    }

    public void pegarDadosBD() {
        alteracaoDao.pegarDadosBD();
    }

    public String[] listarAlteracoes() {
        String[] alter;
        ArrayList<Alteracao> alteracaos = alteracaoDao.listarAlteracoes();
        alter = new String[alteracaos.size()];

        for (Alteracao alt : alteracaos) {
            alter[i] = alt.getTipoAlteracao();
            i++;
        }

        return alter;
    }
}
