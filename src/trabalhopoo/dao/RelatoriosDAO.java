package trabalhopoo.dao;

import java.util.Scanner;
import trabalhopoo.model.*;

public class RelatoriosDAO {

    public static void gerarRelatorios(Passageiro[] passageiros, Voo[] voos, Ticket[] tickets, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- RELATÓRIOS GERENCIAIS ---");
            System.out.println("1 - Passageiros cadastrados");
            System.out.println("2 - Voos disponíveis");
            System.out.println("3 - Tickets vendidos");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n--- Passageiros ---");
                    for (Passageiro p : passageiros) {
                        if (p != null) {
                            System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getDocumento());
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- Voos ---");
                    VooDAO.listar(voos);
                    break;
                case 3:
                    System.out.println("\n--- Tickets ---");
                    for (Ticket t : tickets) {
                        if (t != null) {
                            System.out.println("Ticket " + t.getId() + " - Passageiro: " 
                                + t.getPassageiro().getNome() + " - Voo: " + t.getVoo().getDestino());
                        }
                    }
                    break;
                case 4:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
