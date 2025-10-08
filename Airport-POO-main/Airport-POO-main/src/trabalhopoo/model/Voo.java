/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import static trabalhopoo.dao.VooDAO.cadastrar;
import static trabalhopoo.dao.VooDAO.deletar;
import static trabalhopoo.dao.VooDAO.editar;
import static trabalhopoo.dao.VooDAO.listar;

/**
 * =>CRUD de voo. Informações importantes: id, origem, destino, data, duração,
 * companhia aérea, capacidade, estado (programado, embarque, decolado,
 * atrasado, cancelado) , data_criacao, data_modificacao. Coloque a capacidade
 * pequena para ilustrar o cenário de voo cheio.
 *
 * @author erick
 */
public class Voo {

    int id;
    String origem;
    String destino;
    LocalDate data;
    double duracao;
    CompanhiaAerea companhiaAerea;
    int capacidade;
    String estado;
    LocalDate dataCriacao;
    LocalDate dataModificacao;

    public Voo() {

    }

    public Voo(int id, String origem, String destino, LocalDate data, double duracao, CompanhiaAerea companhiaAerea, int capacidade, String estado, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.duracao = duracao;
        this.companhiaAerea = companhiaAerea;
        this.capacidade = capacidade;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(CompanhiaAerea companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.origem);
        hash = 13 * hash + Objects.hashCode(this.destino);
        hash = 13 * hash + Objects.hashCode(this.data);
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
        final Voo other = (Voo) obj;
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        return Objects.equals(this.data, other.data);
    }
    
    public static void crudVoo(Voo[] voos, CompanhiaAerea[] companhias, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Voo ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    cadastrar(voos, companhias, scan);
                    break;

                case 2:
                    listar(voos);
                    break;

                case 3:
                    editar(voos, scan);
                    break;

                case 4:
                    deletar(voos, scan);
                    break;

                case 5:
                    menu = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

}
