/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.dao.TicketDAO;

/**
 *
 *
 * @author Gonçalves
 */
public class Ticket {

    private int id;
    private double valor;
    private Voo voo;
    private Passageiro passageiro;
    private String codigo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Ticket(int id, double valor, Voo voo, Passageiro passageiro) {
        this.id = id;
        this.valor = valor;
        this.voo = voo;
        this.passageiro = passageiro;
        this.codigo = gerarCodigo();
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static void crudTickets(Ticket[] tickets, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boardingPasses, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Tickets ---");
            System.out.println("1 - Listar Tickets");
            System.out.println("2 - Editar Ticket");
            System.out.println("3 - Deletar Ticket");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    TicketDAO.listar(tickets);
                    break;
                case 2:
                    TicketDAO.editar(tickets, scan);
                    break;

                case 3:
                    TicketDAO.deletar(tickets, checkIns, bagagens, boardingPasses, scan);
                    break;
                case 4:
                    // Voltar ao menu anterior
                    menu = false;
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

}
