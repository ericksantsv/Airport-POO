/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Voo;

/**
 *
 * int id; double valor; Voo voo; Passageiro passageiro; //Codigo id + voo
 * LocalDate dataCriacao; LocalDate dataModificacao;
 *
 * @author erick
 */
public class TicketDAO {

    public static void criar(Ticket[] ticket, Passageiro passageiro, Voo[] voos, Scanner scan) {
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i] == null) {
                //Arrumar valor
                double valor = 10;
                Voo voo = VooDAO.escolherVoo(voos, scan);

                ticket[i] = new Ticket(i + 1, valor, voo, passageiro, LocalDate.now(), LocalDate.now());
                passageiro.setTicket(ticket);

                break;
            }
        }
    }

    public static Voo criarRetornandoVoo(Ticket[] ticket, Passageiro passageiro, Voo[] voos, Scanner scan) {
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i] == null) {
                double valor = 10;

                // Aqui o passageiro escolhe o voo
                Voo vooEscolhido = VooDAO.escolherVoo(voos, scan);

                // Cria o ticket
                ticket[i] = new Ticket(i + 1, valor, vooEscolhido, passageiro, LocalDate.now(), LocalDate.now());

                // Vincula o ticket ao passageiro
                passageiro.setTicket(ticket);

                System.out.println("Ticket criado com sucesso para o voo " + vooEscolhido.getId() + "!");

                // Retorna o voo que foi escolhido
                return vooEscolhido;
            }
        }

        System.out.println("Erro: nao ha espaço disponível para criar novo ticket.");
        return null;
    }

}
