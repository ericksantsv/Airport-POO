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

    private int id;
    private Ticket ticket;
    private String documento;
    private boolean aprovado; // novo: indica se o funcionário aprovou
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public CheckIn(int id, Ticket ticket, String documento) {
        this.id = id;
        this.ticket = ticket;
        this.documento = documento;
        this.aprovado = false;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }

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

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
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
