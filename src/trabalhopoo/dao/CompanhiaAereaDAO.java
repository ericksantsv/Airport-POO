package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.CompanhiaAerea;

public class CompanhiaAereaDAO {

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
                System.out.print("Novo nome: ");
                c.setNome(scan.nextLine());
                System.out.print("Nova abreviacao: ");
                c.setAbreviacao(scan.nextLine());
                c.setData_modificacao(LocalDate.now());
                System.out.println("Companhia atualizada!");
                return;
            }
        }
        System.out.println("Companhia não encontrada!");
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
