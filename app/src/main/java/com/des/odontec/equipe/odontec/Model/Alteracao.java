package com.des.odontec.equipe.odontec.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 18/09/2017.
 */

public class Alteracao {
    private String tipoAlteracao;
    private String id;

    public Alteracao(){

    }

    public String getTipoAlteracao() {
        return tipoAlteracao;
    }

    public void setTipoAlteracao(String tipoAlteracao) {
        this.tipoAlteracao = tipoAlteracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> dadosSalvar() {
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("tipoAlteracao", getTipoAlteracao());
        return dados;
    }




}
