package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.PacienteDao;
import com.des.odontec.equipe.odontec.Model.Paciente;

import java.util.ArrayList;

/**
 * Created by Antonio on 06/11/2017.
 */

public class PacienteController {
    private PacienteDao pacienteDao;
    Context context;

    public PacienteController(Context context) {
        this.context = context;
        pacienteDao = new PacienteDao(context);
    }

    public ArrayList<Paciente> listaPacientes() {

        ArrayList<Paciente> pcnts = pacienteDao.exibirDadosPacientes();
        return pcnts;
    }

    public void deletar(int id){
        pacienteDao.deletarPaciente(id);
    }
}
