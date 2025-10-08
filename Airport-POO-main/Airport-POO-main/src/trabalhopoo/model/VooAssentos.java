/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDate;

public class VooAssentos {
    int id;
    Voo voo;
    String codigoAssento;
    Passageiro passageiro;
    LocalDate dataCriacao;
    LocalDate dataModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public String getCodigoAssento() {
        return codigoAssento;
    }

    public void setCodigoAssento(String codigoAssento) {
        this.codigoAssento = codigoAssento;
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

    public VooAssentos() {
    }

    
    public VooAssentos(int id, Voo voo, String codigoAssento, Passageiro passageiro, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.voo = voo;
        this.codigoAssento = codigoAssento;
        this.passageiro = passageiro;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    } 
}