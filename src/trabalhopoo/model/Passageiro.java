/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CRUD de passageiro. 
 * Informações importantes: id, nome, nascimento, documento
 * login, senha, data_criacao, data_modificacao
 * 
 * @author erick
 */
public class Passageiro {
    int id;
    String nome;
    LocalDate nascimento;
    String documento;
    String login;
    String senha;
    LocalDate data_criacao;
    LocalDate data_modificacao;
    
    public Passageiro() {
    
    }
    
    public Passageiro(int id, String nome, LocalDate nascimento, String documento, String login, String senha, LocalDate data_criacao, LocalDate data_modificacao) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.documento = documento;
        this.login = login;
        this.senha = senha;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDate getData_modificacao() {
        return data_modificacao;
    }

    public void setData_modificacao(LocalDate data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.documento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Passageiro other = (Passageiro) obj;
        return Objects.equals(this.documento, other.documento);
    }
    
  
    
}
