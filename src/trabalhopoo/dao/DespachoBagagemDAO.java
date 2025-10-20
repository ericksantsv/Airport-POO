/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Ticket;

/**
 *
 * @author erick
 */
public class DespachoBagagemDAO {

    public static void despacharBagagem(CheckIn[] checkIns, DespachoBagagem[] bagagens, Scanner scan) {
        System.out.println("\n--- Check-ins aprovados para despacho ---");
        boolean temAprovado = false;

        // 1. Listar apenas check-ins aprovados que ainda não tiveram bagagem despachada
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

        // 2. Procurar o ticket com check-in aprovado que ainda não foi despachado
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
            System.out.println("Ticket não encontrado ou já despachado.");
            return;
        }

        // 3. Registrar o despacho da bagagem
        for (int i = 0; i < bagagens.length; i++) {
            if (bagagens[i] == null) {
                bagagens[i] = new DespachoBagagem(
                        i + 1,
                        ticketSelecionado,
                        ticketSelecionado.getPassageiro().getDocumento(),
                        LocalDate.now(),
                        LocalDate.now()
                );

                System.out.println("\nBagagem despachada com sucesso!");
                System.out.println("-------------------------------------------");
                System.out.println("ID do Despacho: " + bagagens[i].getId());
                System.out.println("Passageiro: " + ticketSelecionado.getPassageiro().getNome());
                System.out.println("Documento: " + ticketSelecionado.getPassageiro().getDocumento());
                System.out.println("Voo: " + ticketSelecionado.getVoo().getOrigem() + " → " + ticketSelecionado.getVoo().getDestino());
                System.out.println("Data de Criação: " + bagagens[i].getDataCriacao());
                System.out.println("-------------------------------------------");

                return;
            }
        }

        System.out.println("Erro: limite máximo de bagagens atingido.");
    }

}
