/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 
@author Gonçalves*/
public class DespachoBagagem {
    
    //atributos
    int id;
    Ticket ticket;
    String documento;
    LocalDate dataCriacao;
    LocalDate dataModificacao;
    
    public DespachoBagagem() {
    }

    public DespachoBagagem(int id, Ticket ticket, String documento, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.ticket = ticket;
        this.documento = documento;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public DespachoBagagem(Ticket ticket, String documento, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.ticket = ticket;
        this.documento = documento;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
    
    
    //usamos o ticket e documento se caso nao tivemos o ID aainda
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.ticket);
        hash = 47 * hash + Objects.hashCode(this.documento);
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
        final DespachoBagagem other = (DespachoBagagem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        return Objects.equals(this.ticket, other.ticket);
    }

    
}