package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 31/08/2017.
 */

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String sexo;
    private String idade;
    private String estado;

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> dadosSalvar=new HashMap<>();
        dadosSalvar.put("nome",getNome());
        dadosSalvar.put("email",getEmail());
        dadosSalvar.put("senha",getSenha());
        dadosSalvar.put("sexo",getSexo());
        dadosSalvar.put("estado",getEstado());
        dadosSalvar.put("cidade",getCidade());
        return dadosSalvar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    private String cidade;

    public Usuario() {
    }
}
