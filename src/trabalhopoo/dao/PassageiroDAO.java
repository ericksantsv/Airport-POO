package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Passageiro;

public class PassageiroDAO {

    public static void crudPassageiro(Passageiro[] passageiros, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Passageiro ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    cadastrar(passageiros, scan);
                    break;

                case 2:
                    listar(passageiros);
                    break;

                case 3:
                    editar(passageiros, scan);
                    break;

                case 4:
                    deletar(passageiros, scan);
                    break;

                case 5:
                    menu = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public static void cadastrar(Passageiro[] passageiros, Scanner scan) {
        for (int i = 0; i < passageiros.length; i++) {
            if (passageiros[i] == null) {
                System.out.print("Nome: ");
                String nome = scan.nextLine();

                System.out.print("Nascimento (AAAA-MM-DD): ");
                LocalDate nasc = LocalDate.parse(scan.nextLine());

                System.out.print("Documento: ");
                String doc = scan.nextLine();

                passageiros[i] = new Passageiro(i, nome, nasc, doc, nasc, nasc);
                System.out.println("Passageiro cadastrado!");
                break;
            }
        }
    }

    public static void listar(Passageiro[] passageiros) {
        System.out.println("\n--- Lista de Passageiros ---");
        for (Passageiro p : passageiros) {
            if (p != null) {
                System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getDocumento());
            }
        }
    }

    public static void editar(Passageiro[] passageiros, Scanner scan) {
        System.out.print("Informe o ID do passageiro para editar: ");
        int idEdit = scan.nextInt();
        scan.nextLine();

        Passageiro pEdit = null;
        for (Passageiro p : passageiros) {
            if (p != null && p.getId() == idEdit) {
                pEdit = p;
                break;
            }
        }

        if (pEdit != null) {
            System.out.print("Novo nome: ");
            pEdit.setNome(scan.nextLine());

            System.out.print("Novo documento: ");
            pEdit.setDocumento(scan.nextLine());

            pEdit.setData_modificacao(LocalDate.now());

            System.out.println("Passageiro atualizado!");
        } else {
            System.out.println("Passageiro não encontrado!");
        }
    }

    public static void deletar(Passageiro[] passageiros, Scanner scan) {
        System.out.print("Informe o ID do passageiro para deletar: ");
        int idDel = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < passageiros.length; i++) {
            if (passageiros[i] != null && passageiros[i].getId() == idDel) {
                passageiros[i] = null;
                System.out.println("Passageiro deletado!");
                break;
            }
        }
    }
}
