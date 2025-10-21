package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.CompanhiaAerea;

public class CompanhiaAereaDAO {

    //Incializa vetor com informações
    public static CompanhiaAerea[] inicializarCompanhias() {
        CompanhiaAerea[] companhias = new CompanhiaAerea[10];

        companhias[0] = new CompanhiaAerea(1, "AzulRio", "AZR", LocalDate.now(), LocalDate.now());
        companhias[1] = new CompanhiaAerea(2, "LatamAir", "LTA", LocalDate.now(), LocalDate.now());
        companhias[2] = new CompanhiaAerea(3, "SolAereo", "SLA", LocalDate.now(), LocalDate.now());
        companhias[3] = new CompanhiaAerea(4, "VentoLeste", "VLE", LocalDate.now(), LocalDate.now());
        companhias[4] = new CompanhiaAerea(5, "NorteSky", "NSK", LocalDate.now(), LocalDate.now());

        return companhias;
    }
    
    public static void cadastrar(CompanhiaAerea[] companhias, Scanner scan) {
        for (int i = 0; i < companhias.length; i++) {
            if (companhias[i] == null) {
                System.out.print("Nome da Companhia: ");
                String nome = scan.nextLine();
                System.out.print("Abreviacao: ");
                String abreviacao = scan.nextLine();
                companhias[i] = new CompanhiaAerea(i + 1, nome, abreviacao, LocalDate.now(), LocalDate.now());
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
    System.out.print("ID da companhia para editar: ");
    int id = scan.nextInt();
    scan.nextLine();

    for (CompanhiaAerea c : companhias) {
        if (c != null && c.getId() == id) {
            String novoNome;
            while (true) {
                System.out.print("Novo nome: ");
                novoNome = scan.nextLine().trim();
                if (novoNome.isEmpty()) {
                    System.out.println("O nome nao pode ser vazio. Digite novamente.");
                } else {
                    break;
                }
            }

            String novaAbrev;
            while (true) {
                System.out.print("Nova abreviacao: ");
                novaAbrev = scan.nextLine().trim();
                if (novaAbrev.isEmpty()) {
                    System.out.println("A abreviacao nao pode ser vazia. Digite novamente.");
                } else {
                    break;
                }
            }

            c.setNome(novoNome);
            c.setAbreviacao(novaAbrev);
            c.setData_modificacao(LocalDate.now());
            System.out.println("Companhia atualizada!");
            return;
        }
    }

    System.out.println("Companhia nao encontrada!");
}



    public static void deletar(CompanhiaAerea[] companhias, Scanner scan) {
        System.out.print("ID da companhia para deletar: ");
        int id = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < companhias.length; i++) {
            if (companhias[i] != null && companhias[i].getId() == id) {
                companhias[i] = null;
                System.out.println("Companhia deletada!");
                return;
            }
        }
        System.out.println("Companhia nao encontrada!");
    }
}
