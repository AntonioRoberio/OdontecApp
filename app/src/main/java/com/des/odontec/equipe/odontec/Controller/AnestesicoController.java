package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.AnestesicoDao;
import com.des.odontec.equipe.odontec.Model.Anestesico;
import com.des.odontec.equipe.odontec.View.TipoAnestesico;

import java.util.ArrayList;

/**
 * Created by Antonio on 21/09/2017.
 */

public class AnestesicoController {

    AnestesicoDao anestesicoDao;

    public AnestesicoController(Context context) {
        anestesicoDao = new AnestesicoDao(context);
    }


    private String[] listarAnestesicos() {
        int i = 0;
        ArrayList<Anestesico> listaAnestesicos = anestesicoDao.listarAnestesicos();
        String[] anestesicos = new String[listaAnestesicos.size()];

        for (Anestesico an : listaAnestesicos) {
            anestesicos[i] = an.getTipoAnestesico();
            i++;
        }
        return anestesicos;
    }


    public String[] listaAnestesico(String tipoPa, String tipoAlt) {

        String[] anestesicos = listarAnestesicos();

        ArrayList<String> listaAnes = new ArrayList<>();

        if (tipoPa.equals("Criança") && tipoAlt.equals("Norma Sistêmica ")) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[7]);
            listaAnes.add(anestesicos[4]);
        } else if ((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && (tipoAlt.equals("Diabete ") || tipoAlt.equals("Hipertensão "))) {
            listaAnes.add(anestesicos[7]);
        } else if ((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && tipoAlt.equals("Hipertireoidismo ")) {
            listaAnes.add(anestesicos[7]);
            listaAnes.add(anestesicos[6]);

        } else if ((tipoPa.equals("Criança") || tipoPa.equals("Adulto") || tipoPa.equals("Idoso")) && (tipoAlt.equals("Anemia") || tipoAlt.equals("Asma"))) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[7]);
        } else if (tipoPa.equals("Adulto") && tipoAlt.equals("Norma Sistêmica ")) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[3]);
            listaAnes.add(anestesicos[7]);
            listaAnes.add(anestesicos[8]);
            listaAnes.add(anestesicos[5]);
            listaAnes.add(anestesicos[6]);
            listaAnes.add(anestesicos[1]);
            listaAnes.add(anestesicos[0]);
        } else if (tipoPa.equals("Idoso") && tipoAlt.equals("Norma Sistêmica ")) {
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[4]);
        }

        String[] valores = new String[listaAnes.size()];
        int i = 0;
        for (String lista : listaAnes) {
            valores[i] = lista;
            i++;
        }
        return valores;
    }


    public void pegarDadosBD() {
        anestesicoDao.pegarDadosBD();
    }
}
