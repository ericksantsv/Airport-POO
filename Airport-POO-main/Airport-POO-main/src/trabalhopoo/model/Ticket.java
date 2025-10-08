/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDate;

/**
 *
 
@author Gonçalves*/
public class Ticket {
    int id;
    double valor;
    Voo voo;
    Passageiro passageiro;
    //Codigo    id + voo
    LocalDate dataCriacao;
    LocalDate dataModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Ticket(int id, double valor, Voo voo, Passageiro passageiro, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.valor = valor;
        this.voo = voo;
        this.passageiro = passageiro;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }  
}