/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;

/**
 *
 * @author erick
 */
public class CheckInDAO {

    public static void solicitarCheckIn(Usuario usuario, CheckIn[] checkIns, Scanner scan) {
        Passageiro passageiro = usuario.getPassageiro();
        Ticket[] tickets = passageiro.getTicket();

        boolean temTicket = false;
        for (Ticket t : tickets) {
            if (t != null) {
                temTicket = true;
                System.out.println("Ticket: " + t.getId() + " | Voo: " + t.getVoo().getOrigem() + " -> " + t.getVoo().getDestino());
            }
        }

        if (!temTicket) {
            System.out.println("Você não possui passagens para fazer check-in.");
            return;
        }

        System.out.print("Digite o número do ticket que deseja fazer check-in: ");
        int idTicket = scan.nextInt();
        scan.nextLine();

        Ticket ticketEscolhido = null;
        for (Ticket t : tickets) {
            if (t != null && t.getId() == idTicket) {
                ticketEscolhido = t;
                break;
            }
        }

        if (ticketEscolhido == null) {
            System.out.println("Ticket não encontrado.");
            return;
        }

        for (int i = 0; i < checkIns.length; i++) {
            if (checkIns[i] == null) {
                checkIns[i] = new CheckIn(i + 1, ticketEscolhido, passageiro.getDocumento());
                System.out.println("Solicitação de check-in enviada. Aguarde aprovação.");
                return;
            }
        }

        System.out.println("Erro: capacidade máxima de check-ins atingida.");
    }

    public static void listarPendentes(CheckIn[] checkIns) {
        System.out.println("\n--- Check-ins pendentes ---");
        for (CheckIn c : checkIns) {
            if (c != null && !c.isAprovado()) {
                Ticket t = c.getTicket();
                Voo v = t.getVoo();
                System.out.println("ID: " + c.getId() + " | Passageiro: " + t.getPassageiro().getNome()
                        + " | Voo: " + v.getOrigem() + " -> " + v.getDestino() + " | Data: " + v.getData());
            }
        }
    }

    public static void aprovarCheckIn(CheckIn[] checkIns, BoardingPass[] boardingPasses, Scanner scan) {
        listarPendentes(checkIns);
        System.out.print("Digite o ID do check-in para aprovar: ");
        int id = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < checkIns.length; i++) {
            CheckIn c = checkIns[i];
            if (c != null && c.getId() == id && !c.isAprovado()) {
                c.setAprovado(true);
                c.setDataModificacao(LocalDate.now());

                Ticket t = c.getTicket();
                Voo v = t.getVoo();

                // gera boarding pass
                for (int j = 0; j < boardingPasses.length; j++) {
                    if (boardingPasses[j] == null) {
                        boardingPasses[j] = new BoardingPass(j + 1, t.getPassageiro(), v, "A" + (j + 1));
                        System.out.println("Check-in aprovado! Boarding pass emitido para " + t.getPassageiro().getNome());
                        return;
                    }
                }
            }
        }

        System.out.println("❌ Check-in não encontrado ou já aprovado.");
    }
}
