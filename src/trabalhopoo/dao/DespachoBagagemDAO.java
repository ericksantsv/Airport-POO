/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Ticket;

/**
 *
 * @author erick
 */
public class DespachoBagagemDAO {

    public static void inicializarBagagens(Ticket[] tickets, CheckIn[] checkIns, DespachoBagagem[] bagagens) {
    int bagIndex = 0;
    for (int i = 0; i < tickets.length && bagIndex < bagagens.length; i++) {
        Ticket t = tickets[i];
        if (t == null) continue;

        // Procura o check-in correspondente ao ticket
        CheckIn checkInRelacionado = null;
        for (int j = 0; j < checkIns.length; j++) {
            if (checkIns[j] != null && checkIns[j].getTicket() == t) {
                checkInRelacionado = checkIns[j];
                break;
            }
        }

        // Se o passageiro tem ticket, cria bagagem mesmo se o check-in ainda não estiver aprovado
        if (checkInRelacionado == null || checkInRelacionado.isAprovado()) {
            bagagens[bagIndex] = new DespachoBagagem(bagIndex + 1, t, t.getPassageiro().getDocumento(), t.getVoo().getData(), t.getVoo().getData());
            bagIndex++;
        }
    }
}


    public static void despacharBagagem(CheckIn[] checkIns, DespachoBagagem[] bagagens, Scanner scan) {
        System.out.println("\n--- Check-ins aprovados para despacho ---");
        boolean temAprovado = false;

        for (CheckIn c : checkIns) {
            if (c != null && c.isAprovado()) {
                boolean jaDespachada = false;

                // Verifica se esse ticket já tem despacho
                for (DespachoBagagem d : bagagens) {
                    if (d != null && d.getTicket() == c.getTicket()) {
                        jaDespachada = true;
                        break;
                    }
                }

                if (!jaDespachada) {
                    Ticket t = c.getTicket();
                    System.out.println("ID: " + t.getId()
                            + " | Passageiro: " + t.getPassageiro().getNome()
                            + " | Voo: " + t.getVoo().getOrigem() + " -> " + t.getVoo().getDestino());
                    temAprovado = true;
                }
            }
        }

        if (!temAprovado) {
            System.out.println("Nenhum check-in aprovado para despacho encontrado.");
            return;
        }

        System.out.print("Digite o ID do ticket para despachar: ");
        int idEscolhido = scan.nextInt();
        scan.nextLine();

        Ticket ticketSelecionado = null;

        for (CheckIn c : checkIns) {
            if (c != null && c.isAprovado() && c.getTicket().getId() == idEscolhido) {
                boolean jaDespachada = false;

                for (DespachoBagagem d : bagagens) {
                    if (d != null && d.getTicket() == c.getTicket()) {
                        jaDespachada = true;
                        break;
                    }
                }

                if (!jaDespachada) {
                    ticketSelecionado = c.getTicket();
                    break;
                }
            }
        }

        if (ticketSelecionado == null) {
            System.out.println("Ticket nao encontrado ou ja despachado.");
            return;
        }

        for (int i = 0; i < bagagens.length; i++) {
            if (bagagens[i] == null) {
                bagagens[i] = new DespachoBagagem(
                        i + 1,
                        ticketSelecionado,
                        ticketSelecionado.getPassageiro().getDocumento(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );

                System.out.println("\nBagagem despachada com sucesso!");
                return;
            }
        }

        System.out.println("Erro: limite maximo de bagagens atingido.");
    }

}
