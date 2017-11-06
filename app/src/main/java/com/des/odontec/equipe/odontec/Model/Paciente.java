package com.des.odontec.equipe.odontec.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 05/11/2017.
 */

public class Paciente {
    private String nome;
    private int idade;
    private double peso;
    private String sexo;
    private String dataDeAtendimento;
    private String alteracao;
    private String anestesico;
    private double qtdTubetes;
    private int id;

    public Paciente(){

    }

    public Map<String,Object> dados(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("nome",getNome());
        hashMap.put("idade",getIdade());
        hashMap.put("peso",getPeso());
        hashMap.put("sexo",getSexo());
        hashMap.put("dataDeAtendimento",getDataDeAtendimento());
        hashMap.put("alteracao",getAlteracao());
        hashMap.put("anestesico",getAnestesico());
        hashMap.put("qtdTubetes",getQtdTubetes());
        return hashMap;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataDeAtendimento() {
        return dataDeAtendimento;
    }

    public void setDataDeAtendimento(String dataDeAtendimento) {
        this.dataDeAtendimento = dataDeAtendimento;
    }

    public String getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }

    public String getAnestesico() {
        return anestesico;
    }

    public void setAnestesico(String anestesico) {
        this.anestesico = anestesico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQtdTubetes() {
        return qtdTubetes;
    }

    public void setQtdTubetes(double qtdTubetes) {
        this.qtdTubetes = qtdTubetes;
    }
}
