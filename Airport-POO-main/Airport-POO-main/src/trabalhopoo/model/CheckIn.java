/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CRUD Check-in. Informações importantes: id, ticket, documento, data_criacao,
 * data_modificacao
 *
 * @author erick
 */

/*
     *   O check-in só pode ser feito 24hs antes do voo.
     *   Ao final do check-in é gerado boarding pass.
 */
public class CheckIn {

    //atributos 
    int id;
    Ticket ticket;
    String documento;
    LocalDate dataCriacao;
    LocalDate dataModificacao;

    //getters e setters
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

    @Override
    public String toString() {
        return "CheckIn:\n"
                + "ID: " + id + "\n"
                + "Ticket: " + ticket + "\n"
                + "Documento: " + documento + "\n"
                + "Data de Criação: " + dataCriacao + "\n"
                + "Data de Modificação: " + dataModificacao + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.documento);
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
        final CheckIn other = (CheckIn) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.documento, other.documento);
    }

}
