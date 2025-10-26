package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.model.CompanhiaAerea;

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

    public static void deletar(CompanhiaAerea[] companhias, Scanner scan) {
        
        CompanhiaAereaDAO.listar(companhias);
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
