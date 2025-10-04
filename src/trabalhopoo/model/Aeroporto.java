/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;

public class Aeroporto {
int id;
    String nome;
    String abreviacao;
    String cidade;
    LocalDate data_criacao;
    LocalDate data_modificacao;

    //construtor vazio para criar o objeto primeiro
    public Aeroporto() {
    }

    //construtor completo para criar o objeto quando tiver dados
    public Aeroporto(int id, String nome, String abreviacao, String cidade, LocalDate data_criacao, LocalDate data_modificacao) {
        this.id = id;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.cidade = cidade;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    //construtor para criar um novo aeroporto
    public Aeroporto(String nome, String abreviacao, String cidade, LocalDate data_criacao, LocalDate data_modificacao) {
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.cidade = cidade;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    //getters e setters
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

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    //tostring para mostrar as informações do aeroporto
    @Override
    public String toString() {
        return "ID: " + id + 
                "\nNome: " + nome +
                "\nAbreviação: " + abreviacao +
               "\nCidade: " + cidade + "\n";
    }

     @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.abreviacao);
        return hash;
    }

    //atributos
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
        final Aeroporto other = (Aeroporto) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.abreviacao, other.abreviacao);
    }
    

}
