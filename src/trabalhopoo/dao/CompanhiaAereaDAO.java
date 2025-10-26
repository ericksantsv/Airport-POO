package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Voo;
import trabalhopoo.model.VooAssentos;

public class CompanhiaAereaDAO {

    //Incializa vetor com informações
    public static CompanhiaAerea[] inicializarCompanhias() {
        CompanhiaAerea[] companhias = new CompanhiaAerea[10];

        companhias[0] = new CompanhiaAerea(1, "AzulRio", "AZR", LocalDateTime.now(), LocalDateTime.now());
        companhias[1] = new CompanhiaAerea(2, "LatamAir", "LTA", LocalDateTime.now(), LocalDateTime.now());
        companhias[2] = new CompanhiaAerea(3, "SolAereo", "SLA", LocalDateTime.now(), LocalDateTime.now());
        companhias[3] = new CompanhiaAerea(4, "VentoLeste", "VLE", LocalDateTime.now(), LocalDateTime.now());
        companhias[4] = new CompanhiaAerea(5, "NorteSky", "NSK", LocalDateTime.now(), LocalDateTime.now());

        return companhias;
    }

    public static void cadastrar(CompanhiaAerea[] companhias, Scanner scan) {
        for (int i = 0; i < companhias.length; i++) {
            if (companhias[i] == null) {
                System.out.print("Nome da Companhia: ");
                String nome = scan.nextLine();
                System.out.print("Abreviacao: ");
                String abreviacao = scan.nextLine();
                companhias[i] = new CompanhiaAerea(i + 1, nome, abreviacao, LocalDateTime.now(), LocalDateTime.now());
                System.out.println("Companhia cadastrada!");
                break;
            }
        }
    }

    public static void listar(CompanhiaAerea[] companhias) {
        System.out.println("\n--- Lista de Companhias ---");
        for (CompanhiaAerea c : companhias) {
            if (c != null) {
                System.out.println(c.getId() + " - " + c.getNome() + " (" + c.getAbreviacao() + ")");
            }
        }
    }

    public static void editar(CompanhiaAerea[] companhias, Scanner scan) {

        CompanhiaAereaDAO.listar(companhias);
        System.out.print("ID da companhia para editar: ");
        int id = scan.nextInt();
        scan.nextLine();
        for (CompanhiaAerea c : companhias) {
            if (c != null && c.getId() == id) {
                System.out.print("Novo nome: ");
                c.setNome(scan.nextLine());
                System.out.print("Nova abreviacao: ");
                c.setAbreviacao(scan.nextLine());
                c.setData_modificacao(LocalDateTime.now());
                System.out.println("Companhia atualizada!");
                return;
            }
        }
        System.out.println("Companhia nao encontrada!");
    }

    public static void deletar(CompanhiaAerea[] companhias, Voo[] voos, Ticket[] tickets, VooAssentos[] assentos, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boardingPasses, Scanner scan) {

        CompanhiaAereaDAO.listar(companhias);
        System.out.print("ID da companhia para deletar: ");
        int id = scan.nextInt();
        scan.nextLine();

        CompanhiaAerea companhiaDeletar = null;

        // Encontrar a companhia
        for (int i = 0; i < companhias.length; i++) {
            if (companhias[i] != null && companhias[i].getId() == id) {
                companhiaDeletar = companhias[i];
                break;
            }
        }

        if (companhiaDeletar == null) {
            System.out.println("Companhia nao encontrada!");
            return;
        }

        // Confirmação
        System.out.print("Certeza? Isso ira deletar todos os dados associados a esta companhia (voos, tickets, assentos, check-ins, bagagens, boarding passes) [S/N]: ");
        String confirm = scan.nextLine();
        if (!confirm.equalsIgnoreCase("S")) {
            System.out.println("Operacao cancelada.");
            return;
        }

        // Deletar a companhia
        for (int i = 0; i < companhias.length; i++) {
            if (companhias[i] == companhiaDeletar) {
                companhias[i] = null;
                System.out.println("Companhia deletada!");
                break;
            }
        }

        // Remover voos associados
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] != null && voos[i].getCompanhiaAerea() == companhiaDeletar) {

                Voo voo = voos[i];

                // Remover tickets do voo
                for (int j = 0; j < tickets.length; j++) {
                    if (tickets[j] != null && tickets[j].getVoo() == voo) {
                        tickets[j] = null;
                    }
                }

                // Liberar assentos do voo
                if (voo.getVooAssentos() != null) {
                    for (VooAssentos a : voo.getVooAssentos()) {
                        if (a != null) {
                            a.setPassageiro(null);
                        }
                    }
                }

                // Remover check-ins
                for (int j = 0; j < checkIns.length; j++) {
                    if (checkIns[j] != null && checkIns[j].getTicket().getVoo() == voo) {
                        checkIns[j] = null;
                    }
                }

                // Remover bagagens
                for (int j = 0; j < bagagens.length; j++) {
                    if (bagagens[j] != null && bagagens[j].getTicket().getVoo() == voo) {
                        bagagens[j] = null;
                    }
                }

                // Remover boarding passes
                for (int j = 0; j < boardingPasses.length; j++) {
                    if (boardingPasses[j] != null && boardingPasses[j].getVoo() == voo) {
                        boardingPasses[j] = null;
                    }
                }

                // Deletar o voo
                voos[i] = null;
            }
        }

        System.out.println("Todos os registros associados a companhia foram removidos.");
    }

}
