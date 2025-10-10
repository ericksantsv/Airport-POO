package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;

public class PassageiroDAO {

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

    public static void cadastrarSemLogin(Passageiro[] passageiros, Voo[] voos, Usuario[] usuarios, Scanner scan) {
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

                Voo vooEscolhido = TicketDAO.criarRetornandoVoo(passageiros[i].getTicket(), passageiros[i], voos, scan);

                if (vooEscolhido != null) {
                    // Agora chama a reserva de assento passando o voo selecionado
                    VooAssentosDAO.reservarAssentoSemLogin(vooEscolhido, passageiros[i], scan);
                }

                System.out.println("\nQuase finalizado! Vamos registrar o seu usuario agora\n");
                Usuario usuarioCriado = UsuarioDAO.criaUsuario(scan, usuarios);
                if (usuarioCriado != null) {
                    passageiros[i].setUsuario(usuarioCriado);
                }

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
            System.out.println("Passageiro nao encontrado!");
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
    
    //alterando aqui pra baixo
 public static void fazerCheckIn(Passageiro[] passageiros, Scanner scan) {
    System.out.print("Digite o nome do passageiro: ");
    String nome = scan.nextLine();

    for (Passageiro p : passageiros) {
        if (p != null && p.getNome().equalsIgnoreCase(nome)) {
           // p.setCheckIn(true);
            System.out.println(" Check-in realizado para " + p.getNome());
            return;
        }
    }
    System.out.println("Passageiro não encontrado!");
}

public static void despacharBagagem(Passageiro[] passageiros, Scanner scan) {
    System.out.print("Digite o nome do passageiro: ");
    String nome = scan.nextLine();

    for (Passageiro p : passageiros) {
        if (p != null && p.getNome().equalsIgnoreCase(nome)) {
            //p.setBagagemDespachada(true);
            System.out.println(" Bagagem despachada para " + p.getNome());
            return;
        }
    }
    System.out.println("Passageiro não encontrado!");
}

}
