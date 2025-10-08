package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.*;

public class VooAssentosDAO {

    public static void crudAssentos(VooAssentos[] assentos, Voo[] voos, Passageiro[] passageiros, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Assentos de Voo ---");
            System.out.println("1 - Reservar Assento");
            System.out.println("2 - Listar Assentos");
            System.out.println("3 - Deletar Assento");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    reservarAssento(assentos, voos, passageiros, scan);
                    break;
                case 2:
                    listarAssentos(assentos);
                    break;
                case 3:
                    deletarAssento(assentos, scan);
                    break;
                case 4:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void reservarAssento(VooAssentos[] assentos, Voo[] voos, Passageiro[] passageiros, Scanner scan) {
        for (int i = 0; i < assentos.length; i++) {
            if (assentos[i] == null) {
                System.out.print("ID do voo: ");
                int idVoo = scan.nextInt();
                scan.nextLine();
                Voo v = null;
                for (Voo voo : voos) {
                    if (voo != null && voo.getId() == idVoo) {
                        v = voo;
                        break;
                    }
                }
                if (v == null) {
                    System.out.println("Voo não encontrado!");
                    return;
                }
                System.out.print("ID do passageiro: ");
                int idPass = scan.nextInt();
                scan.nextLine();
                Passageiro p = null;
                for (Passageiro pass : passageiros) {
                    if (pass != null && pass.getId() == idPass) {
                        p = pass;
                        break;
                    }
                }
                if (p == null) {
                    System.out.println("Passageiro não encontrado!");
                    return;
                }
                //tratar esse erro
                assentos[i] = new VooAssentos(i, v, null, p, LocalDate.MIN, LocalDate.MIN);
                System.out.println("Assento reservado!");
                return;
            }
        }
        System.out.println("Não há espaço para novos assentos!");
    }

    public static void listarAssentos(VooAssentos[] assentos) {
        System.out.println("\n--- Lista de Assentos ---");
        for (VooAssentos a : assentos) {
            if (a != null) {
                System.out.println("ID: " + a.getId() + " | Voo: " + a.getVoo().getOrigem() + "->" + a.getVoo().getDestino() +
                        " | Passageiro: " + a.getPassageiro().getNome());
            }
        }
    }

    public static void deletarAssento(VooAssentos[] assentos, Scanner scan) {
        System.out.print("ID do assento para deletar: ");
        int id = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < assentos.length; i++) {
            if (assentos[i] != null && assentos[i].getId() == id) {
                assentos[i] = null;
                System.out.println("Assento deletado!");
                return;
            }
        }
        System.out.println("Assento não encontrado!");
    }
}
