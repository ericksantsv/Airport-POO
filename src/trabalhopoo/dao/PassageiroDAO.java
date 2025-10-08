package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Voo;

public class PassageiroDAO {

    // ---------------- Compra de passagem ----------------
    public static void comprarPassagem(Scanner scan, Passageiro[] passageiros, Voo[] voos) {
        System.out.println("\n--- Compra de Passagem ---");
        System.out.print("Digite seu nome: ");
        String nome = scan.nextLine();
        System.out.print("Digite seu documento: ");
        String doc = scan.nextLine();

        // Procura passageiro
        Passageiro p = null;
        for (Passageiro pass : passageiros) {
            if (pass != null && pass.getDocumento().equals(doc)) {
                p = pass;
                break;
            }
        }

        if (p == null) {
            System.out.println("Passageiro não encontrado. Cadastrando novo...");
            for (int i = 0; i < passageiros.length; i++) {
                if (passageiros[i] == null) {
                    p = new Passageiro(i + 1, nome, LocalDate.MIN, doc, LocalDate.MIN, LocalDate.MIN);
                    passageiros[i] = p;
                    break;
                }
            }
        }

        // Lista voos disponíveis
        VooDAO.listar(voos);
        System.out.print("Escolha o ID do voo para comprar: ");
        int idVoo = scan.nextInt();
        scan.nextLine();
        Voo vooEscolhido = null;
        for (Voo v : voos) {
            if (v != null && v.getId() == idVoo) {
                vooEscolhido = v;
                break;
            }
        }

        if (vooEscolhido != null) {
            System.out.println("Passagem comprada com sucesso para " + vooEscolhido.getDestino() + "!");
        } else {
            System.out.println("Voo inválido!");
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

                passageiros[i] = new Passageiro(i + 1, nome, nasc, doc, nasc, nasc);
                System.out.println("Passageiro cadastrado!");
                break;
            }
        }
    }
    
    public static void cadastrarSemLogin(Passageiro[] passageiros, Voo[] voos, Scanner scan) {
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
                
                TicketDAO.criar(passageiros[i].getTicket(), passageiros[i], voos, scan);
                
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

        Passageiro pEdit = buscarPorId(passageiros, idEdit);

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

    public static Passageiro buscarPorId(Passageiro[] passageiros, int id) {
        for (Passageiro p : passageiros) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
