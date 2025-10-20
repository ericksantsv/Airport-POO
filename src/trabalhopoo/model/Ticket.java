/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDate;

/**
 *
 
@author Gonçalves*/
    public class Ticket {
    private int id;
    private double valor;
    private Voo voo;
    private Passageiro passageiro;
    private String codigo; // <--- novo campo
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public Ticket(int id, double valor, Voo voo, Passageiro passageiro) {
        this.id = id;
        this.valor = valor;
        this.voo = voo;
        this.passageiro = passageiro;
        this.codigo = gerarCodigo();
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }

    private String gerarCodigo() {
        return "TK" + id + "-" + voo.getOrigem().substring(0, 3).toUpperCase() + voo.getDestino().substring(0, 3).toUpperCase();
    }

    public String getCodigo() {
        return codigo;
    }

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

}