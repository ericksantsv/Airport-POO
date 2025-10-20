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

    // 1. Filtra tickets que ainda não possuem check-in
    boolean temTicketDisponivel = false;
    System.out.println("\n--- Tickets disponíveis para check-in ---");
    for (Ticket t : tickets) {
        if (t != null) {
            boolean jaSolicitado = false;
            for (CheckIn c : checkIns) {
                if (c != null && c.getTicket() == t) {
                    jaSolicitado = true;
                    break;
                }
            }
            if (!jaSolicitado) {
                System.out.println("Numero: " + t.getId()
                        + " | Codigo: " + t.getCodigo()
                        + " | Voo: " + t.getVoo().getOrigem() + " -> " + t.getVoo().getDestino());
                temTicketDisponivel = true;
            }
        }
    }

    if (!temTicketDisponivel) {
        System.out.println("Você não possui tickets disponíveis para solicitar check-in.");
        return; // sai do método
    }

    // 2. Solicita o número do ticket
    System.out.print("Digite o número do ticket que deseja fazer check-in: ");
    int idTicket = scan.nextInt();
    scan.nextLine();

    // 3. Procura o ticket escolhido
    Ticket ticketEscolhido = null;
    for (Ticket t : tickets) {
        if (t != null && t.getId() == idTicket) {
            // verifica se já existe check-in
            boolean jaSolicitado = false;
            for (CheckIn c : checkIns) {
                if (c != null && c.getTicket() == t) {
                    jaSolicitado = true;
                    break;
                }
            }
            if (!jaSolicitado) {
                ticketEscolhido = t;
                break;
            }
        }
    }

    if (ticketEscolhido == null) {
        System.out.println("Ticket não encontrado ou já possui solicitação de check-in.");
        return;
    }

    // 4. Registra o check-in no primeiro espaço disponível
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
                System.out.println("Numero: " + c.getId() + " | Codigo: " + t.getCodigo() + " | Passageiro: " + t.getPassageiro().getNome()
                        + " | Voo: " + v.getOrigem() + " -> " + v.getDestino() + " | Data: " + v.getData());
            }
        }
    }

    public static void aprovarCheckIn(CheckIn[] checkIns, BoardingPass[] boardingPasses, Scanner scan) {
        // 1. Verifica se existem check-ins pendentes
        boolean temPendentes = false;
        for (CheckIn c : checkIns) {
            if (c != null && !c.isAprovado()) {
                temPendentes = true;
                break;
            }
        }

        if (!temPendentes) {
            System.out.println("Não há check-ins pendentes para aprovar.");
            return; // sai do método
        }

        // 2. Lista os check-ins pendentes
        listarPendentes(checkIns);

        // 3. Solicita o ID do check-in a aprovar
        System.out.print("Digite o ID do check-in para aprovar: ");
        int id = scan.nextInt();
        scan.nextLine();

        // 4. Procura o check-in e aprova
        for (int i = 0; i < checkIns.length; i++) {
            CheckIn c = checkIns[i];
            if (c != null && c.getId() == id && !c.isAprovado()) {
                c.setAprovado(true);
                c.setDataModificacao(LocalDate.now());

                Ticket t = c.getTicket();
                Voo v = t.getVoo();

                // Gera boarding pass
                for (int j = 0; j < boardingPasses.length; j++) {
                    if (boardingPasses[j] == null) {
                        boardingPasses[j] = new BoardingPass(j + 1, t.getPassageiro(), v, "A" + (j + 1));
                        System.out.println("Check-in aprovado! Boarding pass emitido para " + t.getPassageiro().getNome());
                        return;
                    }
                }
            }
        }

        System.out.println("Check-in não encontrado ou já aprovado.");
    }

}
