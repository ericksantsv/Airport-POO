package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Voo;
import trabalhopoo.model.CompanhiaAerea;

public class VooDAO {

    public static void cadastrar(Voo[] voos, CompanhiaAerea[] companhias, Scanner scan) {
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] == null) {
                System.out.print("Origem: ");
                String origem = scan.nextLine();

                System.out.print("Destino: ");
                String destino = scan.nextLine();

                System.out.print("Data (AAAA-MM-DD): ");
                LocalDate data = LocalDate.parse(scan.nextLine());

                System.out.print("Duração (horas): ");
                double dur = scan.nextDouble();
                scan.nextLine();

                System.out.println("Escolha a companhia aérea:");
                for (int j = 0; j < companhias.length; j++) {
                    if (companhias[j] != null) {
                        System.out.println(j + " - " + companhias[j].getNome());
                    }
                }

                int c = scan.nextInt();
                scan.nextLine();

                System.out.print("Capacidade: ");
                int cap = scan.nextInt();
                scan.nextLine();

                voos[i] = new Voo(i + 1, origem, destino, data, dur, companhias[c], cap, "Programado", LocalDate.now(), LocalDate.now());

                System.out.println("Voo cadastrado!");
                break;
            }
        }
    }

    public static void editar(Voo[] voos, Scanner scan) {
        System.out.print("Informe o ID do voo para editar: ");
        int idEdit = scan.nextInt();
        scan.nextLine();

        Voo vEdit = null;
        for (Voo v : voos) {
            if (v != null && v.getId() == idEdit) {
                vEdit = v;
                break;
            }
        }

        if (vEdit != null) {
            System.out.print("Nova origem: ");
            vEdit.setOrigem(scan.nextLine());

            System.out.print("Novo destino: ");
            vEdit.setDestino(scan.nextLine());

            vEdit.setDataModificacao(LocalDate.now());

            System.out.println("Voo atualizado!");
        } else {
            System.out.println("Voo não encontrado!");
        }
    }

    public static void deletar(Voo[] voos, Scanner scan) {
        System.out.print("Informe o ID do voo para deletar: ");
        int idDel = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < voos.length; i++) {
            if (voos[i] != null && voos[i].getId() == idDel) {
                voos[i] = null;
                System.out.println("Voo deletado!");
                break;
            }
        }
    }

    public static void listar(Voo[] voos) {
        System.out.println("\n ===== Voos =====");
        for (Voo v : voos) {
            if (v != null) {
                System.out.println("\n| Numero: " + v.getId()
                        + "\n| Origem: " + v.getOrigem()
                        + "\n| Destino: " + v.getDestino()
                        + "\n| Data: " + v.getData()
                        + "\n| Duracao: " + v.getDuracao()
                        + "\n| Companhia: " + v.getCompanhiaAerea().getNome()
                        + "\n| Capacidade: " + v.getCapacidade()
                        + "\n| Estado: " + v.getEstado()
                );
            }
        }
    }
}
