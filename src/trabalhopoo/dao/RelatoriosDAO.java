package trabalhopoo.dao;

import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Voo;

public class RelatoriosDAO {

    public static void passageirosQueDeixaramCidade(Voo[] voos, Ticket[] tickets, Scanner scan) {
        System.out.println("\n===== Cidades de Origem Disponíveis =====");
        String[] origens = new String[voos.length];
        int count = 0;

        // Coleta origens únicas manualmente
        for (Voo v : voos) {
            if (v != null && v.getOrigem() != null) {
                boolean repetido = false;
                for (int i = 0; i < count; i++) {
                    if (origens[i].equalsIgnoreCase(v.getOrigem())) {
                        repetido = true;
                        break;
                    }
                }
                if (!repetido) {
                    origens[count] = v.getOrigem();
                    count++;
                }
            }
        }

        // Exibe as origens sem repetir
        for (int i = 0; i < count; i++) {
            System.out.println("- " + origens[i]);
        }

        System.out.print("\nDigite a cidade de origem desejada: ");
        String origemEscolhida = scan.nextLine().trim();

        boolean encontrou = false;
        int contador = 0;

        System.out.println("\n===== Passageiros que partiram de " + origemEscolhida + " =====");

        for (Ticket t : tickets) {
            if (t != null) {
                Voo v = t.getVoo();
                if (v != null
                        && v.getOrigem().equalsIgnoreCase(origemEscolhida)
                        && v.getEstado().equalsIgnoreCase("Concluido")) {

                    Passageiro p = t.getPassageiro();
                    System.out.println("• " + p.getNome() + " (CPF: " + p.getDocumento() + ")");
                    contador++;
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum passageiro encontrado que tenha partido de " + origemEscolhida + ".");
        } else {
            System.out.println("\nTotal de passageiros que deixaram " + origemEscolhida + ": " + contador);
        }
    }

}
